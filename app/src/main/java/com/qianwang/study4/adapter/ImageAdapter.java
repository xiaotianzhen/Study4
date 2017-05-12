package com.qianwang.study4.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luo on 2017/5/6.
 */

public class ImageAdapter extends PagerAdapter {

    private List<ImageView> imageViewList = new ArrayList<ImageView>();
    private Context mContext;

    public ImageAdapter(Context context, List<ImageView> imageViews) {
        this.imageViewList = imageViews;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return imageViewList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = imageViewList.get(position);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageViewList.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }
}
