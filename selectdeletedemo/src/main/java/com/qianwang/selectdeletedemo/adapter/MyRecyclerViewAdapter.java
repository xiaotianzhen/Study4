package com.qianwang.selectdeletedemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianwang.selectdeletedemo.R;
import com.qianwang.selectdeletedemo.bean.RecyclerItem;

import java.util.List;

/**
 * Created by luo on 2017/5/16.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {


    private Context mContext;
    private List<RecyclerItem> mData;

    public MyRecyclerViewAdapter(Context context, List<RecyclerItem> list) {

        this.mContext = context;
        this.mData = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recycle, null, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.im_item.setBackgroundResource(mData.get(position).getResID());
        holder.tv_title.setText(mData.get(position).getTitle());
        holder.tv_stitle.setText(mData.get(position).getS_title());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView im_item;
        private TextView tv_title;
        private TextView tv_stitle;

        public ViewHolder(View itemView) {
            super(itemView);
            im_item = (ImageView) itemView.findViewById(R.id.im_item);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_stitle = (TextView) itemView.findViewById(R.id.tv_stitle);
        }
    }
}
