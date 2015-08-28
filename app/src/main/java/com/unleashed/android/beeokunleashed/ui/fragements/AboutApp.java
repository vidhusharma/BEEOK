package com.unleashed.android.beeokunleashed.ui.fragements;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.unleashed.android.beeokunleashed.R;
import com.unleashed.android.beeokunleashed.adhosting.googleadmob.GoogleAdMob;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutApp extends Fragment {


    public AboutApp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_about_app, container, false);

        if (getResources().getInteger(R.integer.host_ads) == 1) {
            // Initialize the google ads via common api.
            GoogleAdMob.init_google_ads(rootView, R.id.adView_about_app);
            GoogleAdMob.init_google_ads(rootView, R.id.adView_about_app2);
        }

        show_about_app(rootView);

        return rootView;
    }

    private void show_about_app(View rv) {

        TextView tvAboutApp = (TextView)rv.findViewById(R.id.textView_about_app);
        tvAboutApp.setText(R.string.app_about);
        tvAboutApp.append("\n\n");
        tvAboutApp.append(getResources().getString(R.string.version_history));

    }


}
