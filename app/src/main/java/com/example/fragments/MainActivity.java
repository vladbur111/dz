package com.example.fragments;

import com.example.fragments.Fragment1.OnSomeEventListener;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements OnSomeEventListener{
    Fragment frag;
    FragmentTransaction ft;
    final String NUM_TAG = "tagNum";
    final String COLOR_TAG = "tagColor";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        frag = new Fragment1();
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment, frag);
        ft.commit();

    }


    @Override
    public void someEvent(DataSource.MyData data) {
        frag = new Fragment2();

        final Bundle bundle = new Bundle();
        String num = data.mTitle;
        int color = data.mColor;
        bundle.putString(NUM_TAG, num);
        bundle.putInt(COLOR_TAG, color);
        frag.setArguments(bundle);

        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment, frag);
        ft.addToBackStack(null);
        ft.commit();

    }

}
