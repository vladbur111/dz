package com.example.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.List;


public class Fragment1 extends Fragment {
    List<DataSource.MyData> mData;
    String dataq = "k";
    DataSource.MyData data;

    int nums = 0;


    public interface OnSomeEventListener {
        public void someEvent(DataSource.MyData data);
    }

    OnSomeEventListener someEventListener;


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("tagNum", nums);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            someEventListener = (OnSomeEventListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement onSomeEventListener");
        }
    }


    final String LOG_TAG = "myLogs";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.list);
        final MyDataAdapter mAdapter= new MyDataAdapter(DataSource.getInstance().getData());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(mAdapter);

        if(savedInstanceState == null) {
            nums = (Integer) savedInstanceState.getInt("tagNum");
            nums++;

            mAdapter.notifyItemChanged(mAdapter.d.pushData(nums));
        }
        else {
            nums = (Integer) savedInstanceState.getInt("tagNum");
            nums++;

            mAdapter.notifyItemChanged(mAdapter.d.pushData(nums));
        }
        Button button = (Button) view.findViewById(R.id.button);

        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                nums++;
                mAdapter.notifyItemChanged(mAdapter.d.pushData(nums));

                //someEventListener.someEvent("Test text to Fragment1");
                Log.d(LOG_TAG, "Button click in Fragment1");
            }
        });
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment1, null);


        return v;
    }


    class MyDataAdapter extends RecyclerView.Adapter<MyViewHolder> {
        final int TYPE_FIRST = 0;
        final int TYPE_SECOND = 1;

        public DataSource d;

        public MyDataAdapter(List<DataSource.MyData> data) {
            d = new DataSource();
//            d.pushData("1");
//            d.pushData("2");
            mData = d.getData();
            //mData = data;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Log.d("ListActivity", "onCreateViewHolder " + viewType);
            View view1 = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);

            return new MyViewHolder(view1);

        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
            data = mData.get(position);
            holder.mTitle.setText(data.mTitle);
            holder.mTitle.setTextColor(data.mColor);
            //final String s = data.mTitle;
            holder.itemView.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    someEventListener.someEvent(mData.get(position));
                    Log.d(LOG_TAG, String.valueOf(position));

                }
            });

            Log.d("ListActivity", "onBindViewHolder " + position);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        @Override
        public int getItemViewType(int position) {
            return (position % 2 == 0) ? TYPE_FIRST : TYPE_SECOND;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.title);


        }
    }

}