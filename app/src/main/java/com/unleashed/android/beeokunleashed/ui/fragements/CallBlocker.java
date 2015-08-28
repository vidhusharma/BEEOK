package com.unleashed.android.beeokunleashed.ui.fragements;


import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unleashed.android.beeokunleashed.R;
import com.unleashed.android.beeokunleashed.adhosting.googleadmob.GoogleAdMob;
import com.unleashed.android.beeokunleashed.constants.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class CallBlocker extends Fragment {


    public CallBlocker() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_call_blocker, container, false);


        if (getResources().getInteger(R.integer.host_ads) == 1) {
            // Initialize the google ads via common api.
            GoogleAdMob.init_google_ads(rootView, R.id.adView_call_blocker);
            GoogleAdMob.init_google_ads(rootView, R.id.adView_call_blocker2);
        }


        invoke_call_blocker(rootView);

        return rootView;
    }

    private void invoke_call_blocker(View rootView) {
        // When creating a Dialog inside a fragment, take the context of Fragment.
        AlertDialog.Builder builder = new AlertDialog.Builder(rootView.getContext());  //getActivity().getApplicationContext()  // MainActivity.this
        builder.setIcon(R.drawable.call_recorder_app_icon);
        builder.setCancelable(true);
        builder.setTitle(R.string.app_name);
        builder.setMessage(R.string.dialog_call_blocker_message);
        builder.setInverseBackgroundForced(true);

        builder.setNegativeButton(R.string.ok_got_it, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Stay in the app, dont have to do anything
                dialogInterface.dismiss();
            }
        });

        try{

            AlertDialog alert = builder.create();
            alert.show();
        }catch (Exception ex){
            Log.e(Constants.APP_NAME_TAG, "CallBlocker.java: invoke_call_blocker() caught exception.");
            ex.printStackTrace();
        }
    }


}
