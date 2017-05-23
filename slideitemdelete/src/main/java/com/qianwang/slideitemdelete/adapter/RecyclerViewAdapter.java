package com.qianwang.slideitemdelete.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.qianwang.slideitemdelete.R;
import java.util.List;

/**
 * Created by luo on 2017/5/15.
 */

public class RecyclerViewAdapter  extends  RecyclerView.Adapter<RecyclerViewAdapter.MyViewHodler> {

    private Context mContext;
    private List<String> mList;
    private onItemClickListener mListener;
    public RecyclerViewAdapter(Context context,List<String> data,onItemClickListener listener) {

        this.mContext=context;
        this.mList=data;
        this.mListener=listener;
    }


    public void setListener(onItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(mContext).inflate(R.layout.item_recycler,null,false);
        return new MyViewHodler(view);
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, int position) {

        holder.tv_item.setText(mList.get(position));
        holder.ll_itemLayout.scrollTo(0, 0);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void removeRecycle(int position){
        if(mList.size()>position){

            mList.remove(position);
            notifyDataSetChanged();
            if(mList.size()==0){

                Toast.makeText(mContext,"已经没有数据可以删除了",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class MyViewHodler extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tv_item;
        public TextView tv_delete_item;
        public ImageView im_delete_item;
        public LinearLayout ll_itemLayout;

        public MyViewHodler(View itemView) {
            super(itemView);
            tv_item= (TextView) itemView.findViewById(R.id.tv_item);
            tv_delete_item= (TextView) itemView.findViewById(R.id.tv_delete_item);
            im_delete_item= (ImageView) itemView.findViewById(R.id.im_delete_item);
            ll_itemLayout= (LinearLayout) itemView.findViewById(R.id.ll_itemLayout);
            im_delete_item.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {

            if(mListener!=null){
                mListener.onDelete(view,getAdapterPosition());
            }
        }
    }

    public interface onItemClickListener{
        void  onDelete(View view,int Position);
    }
}
