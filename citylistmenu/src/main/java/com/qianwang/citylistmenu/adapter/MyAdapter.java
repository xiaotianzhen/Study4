package com.qianwang.citylistmenu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qianwang.citylistmenu.R;
import com.qianwang.citylistmenu.entity.City;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luo on 2017/5/23.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    public static final int FIRST_STICKY_VIEW = 1;
    public static final int HAS_STICKY_VIEW = 2;
    public static final int NONE_STICKY_VIEW = 3;
    private Context mContext;
    private List<City> mList = new ArrayList<City>();
    private OnItemOnClickListener mItemOnClickListener;

    public void setItemOnClickListener(OnItemOnClickListener itemOnClickListener) {
        mItemOnClickListener = itemOnClickListener;
    }

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

        if (position == 0) {
            holder.tv_pinyin.setVisibility(View.VISIBLE);
            holder.tv_city.setText(mList.get(0).getCityName());
            holder.tv_pinyin.setText(mList.get(0).getFirstPinYin());
            holder.item_ll.setTag(FIRST_STICKY_VIEW);
        } else {
            if (mList.get(position).getFirstPinYin().equals(mList.get(position - 1).getFirstPinYin())) {
                holder.tv_pinyin.setVisibility(View.GONE);
                holder.tv_city.setText(mList.get(position).getCityName());
                holder.item_ll.setTag(NONE_STICKY_VIEW);
            }else {
                holder.tv_pinyin.setVisibility(View.VISIBLE);
                holder.tv_city.setText(mList.get(position).getCityName());
                holder.tv_pinyin.setText(mList.get(position).getFirstPinYin());
                holder.item_ll.setTag(HAS_STICKY_VIEW);
            }
        }
        holder.item_ll.setContentDescription(mList.get(position).getFirstPinYin());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        private TextView tv_pinyin;
        private TextView tv_city;
        private LinearLayout item_ll;

        public ViewHolder(View itemView) {

            super(itemView);
            tv_city = (TextView) itemView.findViewById(R.id.tv_city);
            tv_pinyin = (TextView) itemView.findViewById(R.id.tv_pinyin);
            item_ll= (LinearLayout) itemView.findViewById(R.id.item_ll);
            tv_city.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            if(mItemOnClickListener!=null){
                mItemOnClickListener.onClick(view,getAdapterPosition());
            }
        }
    }


    public interface  OnItemOnClickListener{

        void onClick(View view,int position);
    }
}
