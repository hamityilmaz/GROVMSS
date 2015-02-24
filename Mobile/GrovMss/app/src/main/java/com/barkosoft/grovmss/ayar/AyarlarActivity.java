package com.barkosoft.grovmss.ayar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.barkosoft.grovmss.genel.GlobalClass;
import com.barkosoft.grovmss.R;

public class AyarlarActivity extends ActionBarActivity implements SharedPreferences.OnSharedPreferenceChangeListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayarlar);



        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.registerOnSharedPreferenceChangeListener(this);

        ((Button)findViewById(R.id.btnAyar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ayar=new Intent(getApplicationContext(),AyarPrefActivity.class);
                startActivity(ayar);
            }
        });

        loadPref();
    }

    private void loadPref(){

        final  GlobalClass globalClassVariables = (GlobalClass) getApplication();

        SharedPreferences Ayarlar = PreferenceManager.getDefaultSharedPreferences(this);
        globalClassVariables.setPREF_WEBSERVISI_URL(Ayarlar.getString("PREF_WEBSERVISI_URL", ""));
        globalClassVariables.setPREF_WEBSERVISI_PORT_NO(Ayarlar.getString("PREF_WEBSERVISI_PORT_NO", ""));
        globalClassVariables.setPREF_ONLINE(Ayarlar.getBoolean("PREF_ONLINE", false));
        globalClassVariables.setPREF_YAZICI_COM_PORT(Ayarlar.getString("PREF_YAZICI_COM_PORT", "COM1"));

        TextView tv= (TextView) findViewById(R.id.txtTercihler);

        tv.setText("Web Ser URL:" + globalClassVariables.getPREF_WEBSERVISI_URL() + "\n" +
                "Web Ser PORT:" + globalClassVariables.getPREF_WEBSERVISI_PORT_NO()  + "\n" +
                "Web Online:" + globalClassVariables.isPREF_ONLINE() + "\n"+
                "Yazıcı Port:" + globalClassVariables.getPREF_YAZICI_COM_PORT());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.ayarlar_menu:
                Intent intent = new Intent(this, AyarPrefActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        final  GlobalClass globalClassVariables = (GlobalClass) getApplication();
        globalClassVariables.setPREF_WEBSERVISI_URL(sharedPreferences.getString("PREF_WEBSERVISI_URL", ""));
        globalClassVariables.setPREF_WEBSERVISI_PORT_NO(sharedPreferences.getString("PREF_WEBSERVISI_PORT_NO", ""));
        globalClassVariables.setPREF_ONLINE(sharedPreferences.getBoolean("PREF_ONLINE", false));
        globalClassVariables.setPREF_YAZICI_COM_PORT(sharedPreferences.getString("PREF_YAZICI_COM_PORT", "COM1"));

        TextView tv= (TextView) findViewById(R.id.txtTercihler);

        tv.setText("Web Ser URL:" + globalClassVariables.getPREF_WEBSERVISI_URL() + "\n" +
                "Web Ser PORT:" + globalClassVariables.getPREF_WEBSERVISI_PORT_NO()  + "\n" +
                "Web Online:" + globalClassVariables.isPREF_ONLINE() + "\n"+
                "Yazıcı Port:" + globalClassVariables.getPREF_YAZICI_COM_PORT());
    }


}
