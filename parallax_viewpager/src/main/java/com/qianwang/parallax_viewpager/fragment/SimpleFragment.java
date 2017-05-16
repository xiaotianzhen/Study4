package com.qianwang.parallax_viewpager.fragment;


import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by luo on 2017/5/13.
 */

public class SimpleFragment extends Fragment {

    private String mTitle = "helloworld";
    public static SimpleFragment newInstance(String title) {
        
        Bundle bundle = new Bundle();
        bundle.putString("key_title",title);
        SimpleFragment fragment = new SimpleFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle=getArguments().get("key_title").toString();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        TextView tv = new TextView(getActivity());
        tv.setTextSize(20);
        tv.setTextColor(Color.WHITE);
        tv.setText(mTitle);
        return tv;
    }
}
