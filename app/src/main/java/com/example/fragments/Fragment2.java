package com.example.fragments;

import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class Fragment2 extends Fragment {
    final String LOG_TAG = "myLogs";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment2, null);


        Button button = (Button) v.findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Log.d(LOG_TAG, "Fragment2");
            }
        });


        Bundle arg = getArguments();
        if(arg != null) {
            final String num = arg.getString("tagNum");
            final int color = arg.getInt("tagColor");
            Log.d(LOG_TAG, num);
            ((TextView)v.findViewById(R.id.textView)).setText(num);
            ((TextView)v.findViewById(R.id.textView)).setTextColor(color);
        }

        return v;
    }
}
