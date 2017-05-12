package com.qianwang.study4;

import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.qianwang.study4.adapter.ImageAdapter;
import com.qianwang.study4.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private RelativeLayout mRelativeLayout;
    private int[] imgId = {R.drawable.img01, R.drawable.img02, R.drawable.img03};
    private List<ImageView> imageViewList = new ArrayList<ImageView>();

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.mViewPager);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.activity_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        viewPager.setAdapter(new ImageAdapter(getApplicationContext(), imageViewList));
        viewPager.setPageTransformer(true, new MyTransformation());

        //需要在ViewPager和其父容器中设置clipChildren为false
        //需要为ViewPager的父容器设置OnTouchListener，将触摸事件传递给ViewPager
        mRelativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return viewPager.dispatchTouchEvent(motionEvent);
            }
        });
    }

    private void initData() {

        for (int i : imgId) {
            /*ImageView imageView=new ImageView(getApplicationContext());
            imageView.setBackgroundResource(i);
            imageViewList.add(imageView);*/
            Bitmap bitmap = ImageUtil.getReverseBtimapById(i, getApplicationContext());
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setImageBitmap(bitmap);
            imageViewList.add(imageView);

        }
    }
}