package com.unleashed.android.beeokunleashed.adhosting.appnext;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.appnext.appnextinterstitial.InterstitialManager;
import com.appnext.appnextinterstitial.OnAdLoaded;
import com.appszoom.appszoomsdk.AppsZoom;
import com.unleashed.android.beeokunleashed.R;
import com.unleashed.android.beeokunleashed.constants.Constants;

/**
 * Created by sudhanshu on 22/08/15.
 */
public class AppNextAd {

    public static void init_appsnext_ads(final Context main_activity_context , final Activity activity_context, final boolean repeat){

        //Init the SDK as soon as possible, for example in the onCreate() method of your main activity.

        final String placement_unit_id = main_activity_context.getResources().getString(R.string.appnext_appkey);

        Thread thrAdThread = new Thread() {
            @Override
            public void run() {
                super.run();

                int timer = main_activity_context.getResources().getInteger(R.integer.timer_appnext_appkey);

                while (true) {



                    try{

                        sleep(timer * 60 * 1000);  // wait for x mins before displaying first ad


                        if(repeat == false) {
                            // means no periodic ads, display only once.
                            // Display ad immediately and return.
                            InterstitialManager.showInterstitial(activity_context, placement_unit_id, InterstitialManager.INTERSTITIAL_VIDEO);
                            break;
                        }


                        InterstitialManager.showInterstitial(activity_context, placement_unit_id, InterstitialManager.INTERSTITIAL_VIDEO);



                      //  InterstitialManager.cacheInterstitial(activity_context, placement_unit_id, InterstitialManager.INTERSTITIAL_VIDEO);
//
//                        // To show an Ad, call the showAd() method.
//                        // You can select the most suitable moment to display interstitials.
//                        InterstitialManager.setOnAdLoadedCallback(new OnAdLoaded() {
//                            @Override
//                            public void adLoaded() {
//                                InterstitialManager.showInterstitial(activity_context, placement_unit_id, InterstitialManager.INTERSTITIAL_VIDEO);
//                            }
//                        });

                    }catch (Exception e) {
                        Log.e(Constants.APP_NAME_TAG, "AppNextAd.java: init_appsnext_ads() caught exception.");
                        e.printStackTrace();
                    }
                }
            }
        };
        thrAdThread.start();
    }
}
