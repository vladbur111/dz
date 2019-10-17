package com.example.fragments;

import com.example.fragments.Fragment1.OnSomeEventListener;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnSomeEventListener{
    Fragment frag;
    FragmentTransaction ft;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        frag = new Fragment1();
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment, frag);
        ft.commit();

    }


    public void onClick(View v) {
        Fragment frag1 = getSupportFragmentManager().findFragmentById(R.id.fragment);
        ((TextView) frag1.getView().findViewById(R.id.textView))
                .setText("Access to Fragment 1 from Activity");


    }



    @Override
    public void someEvent(DataSource.MyData data) {
        frag = new Fragment2();

        final Bundle bundle = new Bundle();
        String num = data.mTitle;
        int color = data.mColor;
        bundle.putString("tagNum", num);
        bundle.putInt("tagColor", color);
        frag.setArguments(bundle);

        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment, frag);
        ft.addToBackStack(null);
        ft.commit();


    //    Fragment frag2 = getSupportFragmentManager().findFragmentById(R.id.fragment);
    //    ((TextView)frag2.getView().findViewById(R.id.textView)).setText("Text from Fragment 2:" + s);

    }

}
