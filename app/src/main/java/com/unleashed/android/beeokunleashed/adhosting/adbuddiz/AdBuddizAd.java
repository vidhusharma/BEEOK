package com.unleashed.android.beeokunleashed.adhosting.adbuddiz;

import android.app.Activity;
import android.content.Context;

import com.appnext.appnextinterstitial.InterstitialManager;
import com.purplebrain.adbuddiz.sdk.AdBuddiz;
import com.unleashed.android.beeokunleashed.R;

/**
 * Created by sudhanshu on 22/08/15.
 */
public class AdBuddizAd {

    public static void init_adbuddiz_ads(final Context main_activity_context, final Activity activity_context, final boolean repeat){

        // Ad Buddiz SDK initialization
        AdBuddiz.setPublisherKey(activity_context.getResources().getString(R.string.adbuddiz_appkey));
        //AdBuddiz.setPublisherKey("e537af4a-babc-4c39-82fd-1567664e99e6");
        AdBuddiz.cacheAds(activity_context); // this = current Activity

        Thread thrAdThread = new Thread() {
            @Override
            public void run() {
                super.run();

                int timer = main_activity_context.getResources().getInteger(R.integer.timer_adbuddiz_appkey);


                while (true) {
                    try{

                        sleep(timer * 60 * 1000);  // wait for x mins before displaying first ad


                        if(repeat == false) {
                            // means no periodic ads, display only once.
                            // Display ad immediately and return.
                            if (AdBuddiz.isReadyToShowAd(activity_context)) { // this = current Activity
                                AdBuddiz.showAd(activity_context);
                            }
                            break;
                        }


                        if (AdBuddiz.isReadyToShowAd(activity_context)) { // this = current Activity
                            AdBuddiz.showAd(activity_context);
                        }
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thrAdThread.start();


    }


}
