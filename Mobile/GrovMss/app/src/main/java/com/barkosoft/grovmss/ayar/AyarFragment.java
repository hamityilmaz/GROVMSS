package com.barkosoft.grovmss.ayar;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.barkosoft.grovmss.R;

/**
 * Created by Hamit on 19/02/2015.
 */
public class AyarFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        // Make sure default values are applied.  In a real app, you would
        // want this in a shared function that is used to retrieve the
        // SharedPreferences wherever they are needed.
        //PreferenceManager.setDefaultValues(getActivity(),
          //      R.xml.preferences, false);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.ayarlar);
    }
}
