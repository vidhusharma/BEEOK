package com.unleashed.android.beeokunleashed.utils;

import android.os.Environment;
import android.util.Log;

import com.unleashed.android.beeokunleashed.constants.Constants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by gupta on 8/12/2015.
 */
public class FileHandling {

    private File mFileObj;

    public enum StorageLocation{
        Internal,
        External
    };

    public FileHandling(){        }


    public String CreateFile(String DirectoryName, String FileName, StorageLocation storageLocation){

        String completeFilePath = "";

            if(storageLocation == StorageLocation.Internal){        // Store on internal flash
                Log.i(Constants.APP_NAME_TAG, "FileHandling.java:CreateFile() ... Internal Storage.");

                try{

                    File file_InternalBaseDir = Environment.getDataDirectory();
                    String InternalBaseDir = file_InternalBaseDir.getAbsolutePath();
                    InternalBaseDir = InternalBaseDir +  "/" + DirectoryName + "/" + FileName;

                    File directory = new File(file_InternalBaseDir.getAbsolutePath() + "/" + DirectoryName);
                    if(!directory.exists()){
                        // Create the directory if it does not exist
                        directory.mkdirs();
                    }

                    completeFilePath = InternalBaseDir;
                    Log.i(Constants.APP_NAME_TAG, "FileHandling.java:CreateFile() ... mFileObj Path = " + InternalBaseDir);


                    FileOutputStream fOut = new FileOutputStream(InternalBaseDir);
                    fOut.close();
                }catch (Exception ex){
                    Log.e(Constants.APP_NAME_TAG, "FileHandling.java:CreateFile() ... Caught Exception in Internal Storage block.");
                    ex.printStackTrace();
                }


            }

            if(storageLocation == StorageLocation.External){        // Store externally on SDCARD
                Log.i(Constants.APP_NAME_TAG, "FileHandling.java:CreateFile() ... External Storage.");

                String reserve_state = Environment.getExternalStorageState();
                    if(Environment.MEDIA_MOUNTED.equals(reserve_state)){
                        File sdCard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                        File directory = new File(sdCard.getAbsolutePath() + "/" + DirectoryName);
                        if(!directory.exists()){
                            // Create the directory if it does not exist
                            directory.mkdirs();
                        }

                        completeFilePath = sdCard.getAbsolutePath() + "/" + DirectoryName + "/" + FileName;
                        Log.i(Constants.APP_NAME_TAG, "FileHandling.java:CreateFile() ... mFileObj Path = " + completeFilePath);

                        try{


                            // Create the file object
                            mFileObj = new File(directory, FileName);

                            FileOutputStream fOut = new FileOutputStream(mFileObj);
                            OutputStreamWriter osw = new OutputStreamWriter(fOut);
                            osw.close();
                        }catch (Exception ex){
                            Log.e(Constants.APP_NAME_TAG, "FileHandling.java:CreateFile() caught exception.");
                            ex.printStackTrace();
                        }
                    }
            }

        return completeFilePath;
    }
}
