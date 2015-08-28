package com.unleashed.android.beeokunleashed.services;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.Context;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;

import com.unleashed.android.beeokunleashed.constants.Constants;
import com.unleashed.android.beeokunleashed.recorder.AudioRecorder;
import com.unleashed.android.beeokunleashed.ui.MainActivity;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class RecorderService extends Service {

    private AudioRecorder audioRecorder;
    private static int instance = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        Log.i(Constants.APP_NAME_TAG, "RecorderService.java:onStartCommand()");
        super.onCreate();


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(Constants.APP_NAME_TAG, "RecorderService.java:onStartCommand() ");


        final String filepath = intent.getStringExtra(Constants.INTENT_PARAM_FILE_NAME);
        Log.i(Constants.APP_NAME_TAG, "RecorderService.java:onStartCommand()  filename = " + filepath);



        Thread thrAudioRecorder = new Thread(){
            @Override
            public void run() {
                super.run();

                // Caution: A service runs in the main thread of its hosting process—the service
                // does not create its own thread and does not run in a separate process (unless
                // you specify otherwise). This means that, if your service is going to do any CPU
                // intensive work or blocking operations (such as MP3 playback or networking), you
                // should create a new thread within the service to do that work. By using a
                // separate thread, you will reduce the risk of Application Not Responding (ANR)
                // errors and the application's main thread can remain dedicated to user interaction
                // with your activities.


                Log.i(Constants.APP_NAME_TAG, "RecorderService.java: thrAudioRecorder() " + "instance: " + instance++);


                audioRecorder = new AudioRecorder(filepath, MediaRecorder.AudioSource.VOICE_CALL);

                audioRecorder.AudioRecorder_Start();


            }
        };
        //thrAudioRecorder.setPriority(20);
        thrAudioRecorder.start();



        return super.onStartCommand(intent, flags, startId);
    }


    public RecorderService() {
        super();
    }

    @Override
    public void onDestroy() {

        Log.i(Constants.APP_NAME_TAG, "RecorderService.java:onDestroy()");

        // We got stop service , stop the audio recorder first
        audioRecorder.AudioRecorder_Stop();


        super.onDestroy();


    }
}
