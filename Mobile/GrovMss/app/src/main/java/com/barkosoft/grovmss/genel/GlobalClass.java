package com.barkosoft.grovmss.genel;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.security.PublicKey;

/**
 * Created by Hamit on 16/02/2015.
 */
public class GlobalClass extends Application {

    public static final String PROFILE_PHOTO_FILE_NAME = "ProfilFotografim.jpg";

    public static final String WEB_SERVIS_BASE_URL = "http://10.0.2.2/kimnerede/";
    public static final String WEB_SERVIS_PROFIL_KAYDET_URL = WEB_SERVIS_BASE_URL + "profil_kaydet.php";




    // AYARLAR Değişkenleri


    public String getPREF_WEBSERVISI_URL() {
        return PREF_WEBSERVISI_URL;
    }

    public void setPREF_WEBSERVISI_URL(String PREF_WEBSERVISI_URL) {
        this.PREF_WEBSERVISI_URL = PREF_WEBSERVISI_URL;
    }

    public String getPREF_WEBSERVISI_PORT_NO() {
        return PREF_WEBSERVISI_PORT_NO;
    }

    public void setPREF_WEBSERVISI_PORT_NO(String PREF_WEBSERVISI_PORT_NO) {
        this.PREF_WEBSERVISI_PORT_NO = PREF_WEBSERVISI_PORT_NO;
    }

    public boolean isPREF_ONLINE() {
        return PREF_ONLINE;
    }

    public void setPREF_ONLINE(boolean PREF_ONLINE) {
        this.PREF_ONLINE = PREF_ONLINE;
    }

    public String getPREF_YAZICI_COM_PORT() {
        return PREF_YAZICI_COM_PORT;
    }

    public void setPREF_YAZICI_COM_PORT(String PREF_YAZICI_COM_PORT) {
        this.PREF_YAZICI_COM_PORT = PREF_YAZICI_COM_PORT;
    }

    public String getLOGIN_KULLANICI_KODU() {
        return LOGIN_KULLANICI_KODU;
    }

    public void setLOGIN_KULLANICI_KODU(String LOGIN_KULLANICI_KODU) {
        this.LOGIN_KULLANICI_KODU = LOGIN_KULLANICI_KODU;
    }

    public String getLOGIN_KULLANICI_ADI() {
        return LOGIN_KULLANICI_ADI;
    }

    public void setLOGIN_KULLANICI_ADI(String LOGIN_KULLANICI_ADI) {
        this.LOGIN_KULLANICI_ADI = LOGIN_KULLANICI_ADI;
    }

    public boolean isLOGIN_OK() {
        return LOGIN_OK;
    }

    public void setLOGIN_OK(boolean LOGIN_OK) {
        this.LOGIN_OK = LOGIN_OK;
    }

    private  String PREF_WEBSERVISI_URL;
    private  String PREF_WEBSERVISI_PORT_NO;
    private  boolean PREF_ONLINE;
    private  String PREF_YAZICI_COM_PORT;
    private  String LOGIN_KULLANICI_KODU;
    private  String LOGIN_KULLANICI_ADI;
    private  boolean LOGIN_OK;



    public  void SetGlobalsFromloadPrefences(){
        SharedPreferences Ayarlar = PreferenceManager.getDefaultSharedPreferences(this);
        this.setPREF_WEBSERVISI_URL(Ayarlar.getString("PREF_WEBSERVISI_URL", ""));
        this.setPREF_WEBSERVISI_PORT_NO(Ayarlar.getString("PREF_WEBSERVISI_PORT_NO", ""));
        this.setPREF_ONLINE(Ayarlar.getBoolean("PREF_ONLINE", false));
        this.setPREF_YAZICI_COM_PORT(Ayarlar.getString("PREF_YAZICI_COM_PORT", "COM1"));
        this.setLOGIN_OK(Ayarlar.getBoolean("LOGIN_OK",false));
    }

}


