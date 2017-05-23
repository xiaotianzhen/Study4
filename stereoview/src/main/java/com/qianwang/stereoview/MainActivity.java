package com.qianwang.stereoview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.qianwang.stereoview.custom.StereoView;


public class MainActivity extends AppCompatActivity {

    private StereoView stereoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stereoView= (StereoView) findViewById(R.id.mStereoview);
        stereoView.setStartScreen(2);
    }
}
