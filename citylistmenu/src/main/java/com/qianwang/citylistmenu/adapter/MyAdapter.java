package com.qianwang.citylistmenu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qianwang.citylistmenu.R;
import com.qianwang.citylistmenu.entity.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luo on 2017/5/23.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context mContext;
    private List<City> mList = new ArrayList<City>();

    public MyAdapter(Context context, List<City> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_city, parent, false));
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        holder.tv_city.setText(mList.get(position).getCityName());
        holder.tv_pinyin.setText(mList.get(position).getFirstPinYin());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_pinyin;
        private TextView tv_city;

        public ViewHolder(View itemView) {

            super(itemView);
            tv_city = (TextView) itemView.findViewById(R.id.tv_city);
            tv_pinyin = (TextView) itemView.findViewById(R.id.tv_pinyin);
        }
    }
}
