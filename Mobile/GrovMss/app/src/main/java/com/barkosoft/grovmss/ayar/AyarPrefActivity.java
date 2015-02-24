package com.barkosoft.grovmss.ayar;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Hamit on 19/02/2015.
 */
public class AyarPrefActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new AyarFragment()).commit();

    }

}
