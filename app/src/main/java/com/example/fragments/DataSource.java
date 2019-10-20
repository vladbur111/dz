package com.example.fragments;

import android.graphics.Color;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
    private final List<MyData> listData;
    private static DataSource dataInstance;
    private int dataPosition = 0;

    public DataSource() {
        listData = new ArrayList<>();
    }

    public List<MyData> getData() {
        return listData;
    }

    public int pushData(int num) {
        if (num <= 100) {
            if (num % 2 == 0)
                listData.add(new MyData(String.valueOf(num), Color.RED));
            else
                listData.add(new MyData(String.valueOf(num), Color.BLUE));
        }

        dataPosition = num-1;
        return dataPosition;
    }


    public static class MyData {
        String mTitle;
        int mColor;

        public MyData(String title, int color) {
            mTitle = title;
            mColor = color;
        }
    }

    public synchronized static DataSource getInstance() {
        if (dataInstance == null) {
            dataInstance = new DataSource();
        }
        return dataInstance;
    }

}