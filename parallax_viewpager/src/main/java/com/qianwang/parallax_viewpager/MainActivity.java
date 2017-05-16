package com.qianwang.parallax_viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.qianwang.parallax_viewpager.fragment.SimpleFragment;
import com.qianwang.parallax_viewpager.view.CustomViewPager;

public class MainActivity extends AppCompatActivity {
    private CustomViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (CustomViewPager) findViewById(R.id.custom_vpager);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return  SimpleFragment.newInstance("Position:" + position);
            }

            @Override
            public int getCount() {
                return 3;
            }
        });

        mViewPager.setCurrentItem(1);

    }
}
