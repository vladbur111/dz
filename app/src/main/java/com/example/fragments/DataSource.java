package com.example.fragments;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    private final List<MyData> mData;
    private static DataSource sInstance;


    public DataSource() {
        mData = new ArrayList<>();
    /*
        for (int i = 0; i < 100; i++) {
            mStrings[i] = String.valueOf(i+1);
            if (i % 2 == 0)
                mData.add(new MyData(mStrings[i], Color.BLUE));
            else
                mData.add(new MyData(mStrings[i], Color.RED));
        }
    */
    }

    public List<MyData> getData() {
        return mData;
    }

    public int pushData(int nums) {
        if (nums <= 100) {
            if (nums % 2 == 0)
                mData.add(new MyData(String.valueOf(nums), Color.RED));
            else
                mData.add(new MyData(String.valueOf(nums), Color.BLUE));
        }
        return nums-1;
    }


    public synchronized static DataSource getInstance() {
        if (sInstance == null) {
            sInstance = new DataSource();
        }
        return sInstance;
    }

    public static class MyData {
        public MyData(String title, int color) {
            mTitle = title;
            mColor = color;
        }

        String mTitle;
        int mColor;
    }


}
