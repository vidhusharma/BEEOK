package com.unleashed.android.beeokunleashed.broadcastreceivers;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;

import com.unleashed.android.beeokunleashed.R;
import com.unleashed.android.beeokunleashed.adhosting.appnext.AppNextAd;
import com.unleashed.android.beeokunleashed.constants.Constants;
import com.unleashed.android.beeokunleashed.services.RecorderService;
import com.unleashed.android.beeokunleashed.ui.MainActivity;
import com.unleashed.android.beeokunleashed.utils.FileHandling;
import com.unleashed.android.beeokunleashed.utils.Utils;

import java.util.Date;

/**
 * Created by gupta on 8/10/2015.
 */
public class CallReceiver extends com.unleashed.android.beeokunleashed.broadcastreceivers.CallStateReceiver {

    Context mContext;
    Intent mRecorderServiceIntent;

    String mIncomingFilePath;
    String mOutgoingFilePath;

    @Override
    public void onReceive(Context context, Intent intent) {

        // A little housekeeping doesnt harm anyone. :)
        mContext = context;
        mRecorderServiceIntent = new Intent("com.unleashed.android.beeokunleashed.services");
        mRecorderServiceIntent.setClass(mContext, RecorderService.class);

        if(intent.getAction().equals("android.intent.action.PHONE_STATE")
                || intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {

            Log.i(Constants.APP_NAME_TAG, "CallReceiver.java:onReceive() - Phone_State or New Outgoing Call");

            super.onReceive(context, intent);


        }

//        // This following code will run only once at bootup to start the recorder service.
//        if(intent.getAction().equals("android.intent.action.BOOT_COMPLETED")){
//            Log.i(Constants.APP_NAME_TAG, "CallReceiver.java:onReceive() - BOOT_COMPLETED");
//
//                // Start the service on Bootup.
//                Intent i = new Intent("com.unleashed.android.spyrecorderunleashed.services");
//                i.setClass(context, RecorderService.class);
//                context.startService(i);
//
//            Log.i(Constants.APP_NAME_TAG, "CallReceiver.java:onReceive() - Recorder Service Started Successfuly");
//        }

    }

    @Override
    protected void onIncomingCallStarted(Context ctx, String number, Date start) {
        Log.i(Constants.APP_NAME_TAG, "CallReceiver.java:onIncomingCallStarted() - number: " + number);


        String filename =  Utils.populateFileName(ctx, Constants.FILE_PATH_INCOMING_TAG, number, start);


        FileHandling fh = new FileHandling();
        String completeFilePath ;

        completeFilePath = fh.CreateFile(   Constants.APP_ROOT_FOLDER_DATA /* ctx.getPackageName() */ + "/" + Constants.RECORDED_MEDIA_FOLDER_VOICE_CALLS,
                                            filename,
                                            FileHandling.StorageLocation.External);


        mRecorderServiceIntent.putExtra(Constants.INTENT_PARAM_FILE_NAME, completeFilePath);
        mContext.startService(mRecorderServiceIntent);

    }

    @Override
    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end) {
        Log.i(Constants.APP_NAME_TAG, "CallReceiver.java:onIncomingCallEnded() - number: " + number);

        mContext.stopService(mRecorderServiceIntent);
    }

    @Override
    protected void onOutgoingCallStarted(Context ctx, String number, Date start) {
        Log.i(Constants.APP_NAME_TAG, "CallReceiver.java:onOutgoingCallStarted() - number: " + number);

        String filename =  Utils.populateFileName(ctx, Constants.FILE_PATH_OUTGOING_TAG, number, start);

        FileHandling fh = new FileHandling();
        String completeFilePath ;

        completeFilePath = fh.CreateFile(   Constants.APP_ROOT_FOLDER_DATA /* ctx.getPackageName() */ + "/" + Constants.RECORDED_MEDIA_FOLDER_VOICE_CALLS,
                filename,
                FileHandling.StorageLocation.External);


        mRecorderServiceIntent.putExtra(Constants.INTENT_PARAM_FILE_NAME, completeFilePath);
        mContext.startService(mRecorderServiceIntent);

    }



    @Override
    protected void onOutgoingCallEnded(Context ctx, String number, Date start, Date end) {
        Log.i(Constants.APP_NAME_TAG, "CallReceiver.java:onOutgoingCallEnded() - number: " + number);

        mContext.stopService(mRecorderServiceIntent);

    }

    @Override
    protected void onMissedCall(Context ctx, String number, Date start) {
        Log.i(Constants.APP_NAME_TAG, "CallReceiver.java:onMissedCall() - number: " + number);

        mContext.stopService(mRecorderServiceIntent);

    }

}
