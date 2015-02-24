package com.barkosoft.grovmss;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.barkosoft.grovmss.ayar.AyarPrefActivity;
import com.barkosoft.grovmss.genel.GlobalClass;
import com.barkosoft.grovmss.jsonparser.app.AppController;
import com.barkosoft.grovmss.models.UserClass;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends Activity {


    Button btnGiris,btnSettings;
    EditText edtKullanici,edtSifre;
    String kullanici,sifre;
    String hata_mesaji="";

    ProgressDialog pDialog;
    Boolean hata  = false;


    public String donus_hata_mesaji;


    LinearLayout layout;//layout referans
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final  GlobalClass globalClassVariables = (GlobalClass) getApplication();
        globalClassVariables.SetGlobalsFromloadPrefences();

        //Kullanıcı daha önce login olduysa Login ekranını açmadan Menu ekranını aç
        if(globalClassVariables.isLOGIN_OK()){
            Intent intent = new Intent(this, MenuActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        else{
            //Login olmadıysa Login ekranı açılacak
            setContentView(R.layout.activity_login);

            layout = (LinearLayout)findViewById(R.id.lin_lay2);//layout referans�na layout objesini ba�l�yoruz
            StartAnimations();//Animasyonu ba�lat�yoruz


            edtKullanici = (EditText) findViewById(R.id.kullanici_edit);
            edtSifre = (EditText) findViewById(R.id.sifre_edit);
            btnGiris = (Button) findViewById(R.id.btnGiris);
            btnSettings = (Button) findViewById(R.id.btnSetings);

            btnGiris.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //kontroler yapılıyor

                    kullanici = edtKullanici.getText().toString();//edittext değerini alıyoruz
                    sifre = edtSifre.getText().toString();//edittext değerini alıyoruz


                    //Tarih bilgisini almak için
                /*Calendar c = Calendar.getInstance();
                SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
                tarih = format.format(c.getTime());*/

                    //Edittextlerden aldığımız mail ve şifreleri kontrol ediyoruz
                    if(kullanici.matches("")){
                        hata_mesaji += getString(R.string.login_kullanici_bos_olamaz)+"\n";
                        hata = true;
                    }

                    int sifre_karakter = sifre.length();
                    if(sifre_karakter<6 || sifre_karakter==0){
                        hata_mesaji += getString(R.string.login_sifre_az_olamaz)+"\n";
                        hata=true;
                    }

                    if(hata){//hata varsa AlertDialog ile kullanıcıyı uyarıyoruz.
                        AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
                        alertDialog.setTitle(R.string.hata);
                        alertDialog.setMessage(hata_mesaji);
                        alertDialog.setCancelable(false);

                        alertDialog.setButton(RESULT_OK, getString(R.string.Tamam), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                edtSifre.setText("");
                                hata_mesaji = "";
                                hata = false;
                            }
                        });
                        alertDialog.show();
                    }else{//Hata yoksa Asynctask classı çağırıyoruz.İşlemlere orda devam ediyoruz

                        pDialog = new ProgressDialog(LoginActivity.this);
                        pDialog.setMessage(getString(R.string.login_kisi_bilgileri_getiriliyor));
                        pDialog.setIndeterminate(true);
                        pDialog.setCancelable(false); // ProgressDialog u iptal
                        // edilemez
                        // hale getirdik.
                        pDialog.show();
                        getData();


                    }


                }
            });




            btnSettings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent settingsIntent = new Intent(getApplicationContext(),AyarPrefActivity.class );
                    startActivity(settingsIntent);
                    //finish();

                }
            });

        }


     /*   this.requestWindowFeature(Window.FEATURE_NO_TITLE);    // Splash ekrandan basligi kaldirir
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);    // Bilgi cubugunu kaldirir  */



    }


    public void getData() {
        // TODO adding request to POST method and URL
        // Burada issteğimizi oluşturuyoruz, method parametresi olarak post
        // seçiyoruz ve url'imizi const'dan alıyoruz.

        final  GlobalClass globalClassVariables = (GlobalClass) getApplication();

        StringRequest myReq = new StringRequest(Request.Method.POST, globalClassVariables.getPREF_WEBSERVISI_URL(),  new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                // // TODO datalarımız geldi parse işlemi yapılmalı
                // // TODO lets parse it

                //TODO Gson objesi yaratıyoruz
                //TODO Create Gson Object
                Gson gson = new Gson();
                //TODO yarattığımız Gson objesini Modelimize yani PersonClass'a çeviriyoruz,
                //Böylece gelen verilere rahatça ulaşabileceğiz.
                //TODO Create Model Object with PersonClass
                UserClass gsonResponse = gson.fromJson(response,
                       UserClass.class);

                // bu aldığımız değerleri textView lere
                // yazdırıyoruz.

                pDialog.dismiss(); // ProgresDialog u kapatıyoruz.

                if(kullanici.equalsIgnoreCase(gsonResponse.kullanici)){
                    Toast.makeText(getApplicationContext(),"Login Başarılı",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(),MenuActivity.class);
                    startActivity(intent);
                    finish();
                }



            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO there is an error to connecting server or
                // getting data
                // Server'a bağlanırken yada veri çekilirken hata oldu
                System.out.println(error.getMessage());
            }
        }) {
            // TODO let put params to volley request
            // Burada göndereceğimiz request parametrelerini(birden fazla
            // olabilir) set'liyoruz

            protected Map<String, String> getParams()
                    throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                // TODO seçilen kişinin id'sini kisi parametresine ekliyoruz
                params.put("kullanici", kullanici + "");
                return params;
            }
        };
        // Request(İstek)'i Volley'in Requst sırasına atıyoruz
        // Adding request to volley request queue
        AppController.getInstance().addToRequestQueue(myReq);

    }


    private void StartAnimations() {

        //Bu animasyon ekran renginin de�i�mesi ile ilgili
        //A��l��ta parlak ve yo�un olan ekran yavas yavas g�r�n�r oluor
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l=(LinearLayout) findViewById(R.id.lin_lay);//En d��taki layout �zerinden yap�l�yor
        l.clearAnimation();
        l.startAnimation(anim); //animasyon ba�lat�l�yor


        //Bu animasyon logonun yukar� kaymas�n� sa�layan animasyon
        //H�z�n� res/anim alt�ndaki translate.xml dosyas�ndan ayarlayabilirsiniz
        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.logo);
        iv.clearAnimation();

        anim.setAnimationListener(new Animation.AnimationListener() {//animasyona listener ba�l�yoruz .Yukardaki animasyona listener eklemedik

            //3 tane implement metodu var listener�n
            public void onAnimationStart(Animation animation) {//Animasyon ba�lad���nda �al���yor
                // TODO Auto-generated method stub
            }


            public void onAnimationRepeat(Animation animation) {//Animasyon tekrar etti�inde
                // TODO Auto-generated method stub
            }

            public void onAnimationEnd(Animation animation) {//Animasyon bitti�inde �al���yor
                // TODO Auto-generated method stub
                layout.setVisibility(View.VISIBLE);//layoutu g�r�n�r yap�yoruz

            }
        });
        iv.startAnimation(anim);//animasyon ba�l�yor

    }


}
