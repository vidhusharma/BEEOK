package com.unleashed.android.beeokunleashed.adhosting.appszoom;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.appszoom.appszoomsdk.AppsZoom;
import com.purplebrain.adbuddiz.sdk.AdBuddiz;
import com.unleashed.android.beeokunleashed.R;
import com.unleashed.android.beeokunleashed.ui.MainActivity;

/**
 * Created by sudhanshu on 20/08/15.
 */
public class AppsZoomAd {

    public static void init_appszoom_ads(final Context main_activity_context, final Activity activity_context, final boolean repeat){
        //Init the SDK as soon as possible, for example in the onCreate() method of your main activity.
        AppsZoom.start(main_activity_context);
        Thread thrAdThread = new Thread() {
            @Override
            public void run() {
                super.run();


                int timer = main_activity_context.getResources().getInteger(R.integer.timer_az_appkey);


                while (true) {
                    try{

                        sleep(timer * 60 * 1000);  // wait for x mins before displaying first ad

                        if(repeat == false) {
                            // means no periodic ads, display only once.
                            // Display ad immediately and return.
                            AppsZoom.fetchAd(null, new AppsZoom.OnAdFetchedListener() {
                                @Override
                                public void onAdFetched() {
                                    AppsZoom.showAd(activity_context);      // MainActivity.this
                                }
                            });
                            break;
                        }
                        //To show an Ad, call the showAd() method.
                        // You can select the most suitable moment to display interstitials.
                        AppsZoom.fetchAd(null, new AppsZoom.OnAdFetchedListener() {
                            @Override
                            public void onAdFetched() {
                                AppsZoom.showAd(activity_context);      // MainActivity.this
                            }
                        });

                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thrAdThread.start();
    }


}
