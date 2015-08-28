package com.unleashed.android.beeokunleashed.recorder;

import android.media.MediaRecorder;
import android.util.Log;

import com.unleashed.android.beeokunleashed.constants.Constants;
import com.unleashed.android.beeokunleashed.utils.Utils;

import java.io.IOException;

import static java.lang.Thread.sleep;

/**
 * Created by gupta on 8/11/2015.
 */
public class AudioRecorder extends MediaRecorder{

    private MediaRecorder objMediaRecorder;


    public AudioRecorder(String outputfilename, int audioSource) {
        Log.i(Constants.APP_NAME_TAG, "AudioRecorder.java:AudioRecorder()");

        try {



            objMediaRecorder = new MediaRecorder();

            // Possible Options
            /*
                MediaRecorder.AudioSource.VOICE_CALL        // For Phone Voice Call
                MediaRecorder.AudioSource.MIC               // Record Audio from Mic
                MediaRecorder.AudioSource.VOICE_COMMUNICATION   // For Voice Call over VoIP
            */
            objMediaRecorder.setAudioSource(audioSource);
            objMediaRecorder.setOutputFormat(OutputFormat.THREE_GPP);
            objMediaRecorder.setAudioEncoder(OutputFormat.AMR_NB);
            objMediaRecorder.setOutputFile(outputfilename);
            objMediaRecorder.prepare();


        } catch (IOException e) {
            Log.e(Constants.APP_NAME_TAG, "AudioRecorder.java:AudioRecorder() caught exception");
            e.printStackTrace();
        }
    }

    public boolean AudioRecorder_Start(){
        Log.i(Constants.APP_NAME_TAG, "AudioRecorder.java:AudioRecorder_Start()");

        boolean result = false;

        try{

            objMediaRecorder.start();
            result = true;
        }catch (Exception ex){
            Log.e(Constants.APP_NAME_TAG, "AudioRecorder.java:AudioRecorder_Start() caught exception");
             result = false;
            ex.printStackTrace();
        }
        return result;
    }

    public boolean AudioRecorder_Stop(){
        Log.i(Constants.APP_NAME_TAG, "AudioRecorder.java:AudioRecorder_Stop()");

        boolean result = false;
        try{

            objMediaRecorder.stop();
            objMediaRecorder.release();
            result = true;
        }catch (Exception ex){
            Log.e(Constants.APP_NAME_TAG, "AudioRecorder.java:AudioRecorder_Stop() caught exception");
            result = false;
            ex.printStackTrace();
        }


        return result;
    }
}
