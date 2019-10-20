package com.example.fragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;


public class Fragment1 extends Fragment {
    List<DataSource.MyData> myData;
    RecyclerView recyclerView = null;
    MyDataAdapter mAdapter = new MyDataAdapter();
    final String LOG_TAG = "myLogs";
    final String NUM_TAG = "tagNum";
    private final int PORTRAIT_ORIENT = 3;
    private final int LANDSCAPE_ORIENT = 4;

    OnSomeEventListener someEventListener;

    int num;


    public interface OnSomeEventListener {
        void someEvent(DataSource.MyData data);
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


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(NUM_TAG, num);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment1, null);

        int orientation = v.getContext().getResources().getConfiguration().orientation;
        final int columnsNumber = orientation == Configuration.ORIENTATION_LANDSCAPE ? LANDSCAPE_ORIENT : PORTRAIT_ORIENT;



            //final MyDataAdapter mAdapter= new MyDataAdapter(DataSource.getInstance().getData());
            //mAdapter = new MyDataAdapter();

        recyclerView = v.findViewById(R.id.list);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), columnsNumber));
        recyclerView.setAdapter(mAdapter);

        if(savedInstanceState == null) {
        }
        else {

            num = (Integer) savedInstanceState.get(NUM_TAG);
            for (int i = 1; i <= num; i++) {
                mAdapter.notifyItemChanged(mAdapter.dataSource.pushData(i));
                Log.d(LOG_TAG + "a", String.valueOf(num));
            }
        }


        Button button = v.findViewById(R.id.button);

        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mAdapter.notifyItemChanged(mAdapter.dataSource.pushData(++num));

                Log.d(LOG_TAG + "b", String.valueOf(num));
            }
        });


        return v;
    }

    public void go(int num) {
        mAdapter.notifyItemChanged(mAdapter.dataSource.pushData(++num));
    }


    class MyDataAdapter extends RecyclerView.Adapter<MyViewHolder> {
        public final DataSource dataSource;

        public MyDataAdapter() {
            dataSource = new DataSource();
            myData = dataSource.getData();
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Log.d("ListActivity", "onCreateViewHolder " + viewType);
            View view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);

            return new MyViewHolder(view);

        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
            DataSource.MyData data = myData.get(position);

            holder.mTitle.setText(data.mTitle);
            holder.mTitle.setTextColor(data.mColor);

            holder.itemView.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    someEventListener.someEvent(myData.get(position));
                    notifyDataSetChanged();
                }
            });

        }


        @Override
        public int getItemCount() {
            return myData.size();
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