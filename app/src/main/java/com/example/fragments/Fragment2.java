package com.example.fragments;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Fragment2 extends Fragment {
    final String LOG_TAG = "myLogs";
    final String NUM_TAG = "tagNum";
    final String COLOR_TAG = "tagColor";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment2, null);

        Bundle arg = getArguments();
        if(arg != null) {
            final String num = arg.getString(NUM_TAG);
            final int color = arg.getInt(COLOR_TAG);
            ((TextView)v.findViewById(R.id.textView)).setText(num);
            ((TextView)v.findViewById(R.id.textView)).setTextColor(color);
        }

        return v;
    }
}
