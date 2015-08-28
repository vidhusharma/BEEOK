package com.unleashed.android.beeokunleashed.adhosting.googleadmob;


import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.unleashed.android.beeokunleashed.R;

/**
 * Created by sudhanshu on 20/08/15.
 */
public class GoogleAdMob {


    /*
    * Remember to add dependency in App's build.gradle file  like below:
    *
    *       compile 'com.google.android.gms:play-services-ads:7.5.0'
    * */

    public static void init_google_ads(View localView, int ResourceIdOfAd) {

            // Invoke AdView
            AdView mAdView = (AdView) localView.findViewById(ResourceIdOfAd);   //R.id.adView_jobs_sms_tab
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);

    }
}
