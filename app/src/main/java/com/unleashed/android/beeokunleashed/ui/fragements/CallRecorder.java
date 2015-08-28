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
public class CallRecorder extends Fragment {


    public CallRecorder() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_call_recorder, container, false);

        if (getResources().getInteger(R.integer.host_ads) == 1) {
            // Initialize the google ads via common api.
            GoogleAdMob.init_google_ads(rootView, R.id.adView_call_recorder);
            GoogleAdMob.init_google_ads(rootView, R.id.adView_call_recorder2);
        }


        invoke_call_recording(rootView);

        return rootView;
    }

    private void invoke_call_recording(View rv) {

        TextView tvCallRecorder = (TextView)rv.findViewById(R.id.textView_call_recorder);
        tvCallRecorder.setText(R.string.call_recorder_explaination);


    }

}
