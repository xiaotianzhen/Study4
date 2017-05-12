package com.qianwang.meituanmeun.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.qianwang.meituanmeun.R;
import com.qianwang.meituanmeun.bean.Menu;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luo on 2017/5/12.
 */

public class GridViewAdapter extends BaseAdapter {

    private List<Menu> mMenus = new ArrayList<Menu>();
    private int curpage;
    private int pagesize;
    private Context mContext;

    public GridViewAdapter(List<Menu> menus, int curPage, int pageSize, Context context) {
        this.mMenus = menus;
        this.curpage = curPage;
        this.pagesize = pageSize;
        this.mContext=context;
    }


    //p-((c+1)*p-m)=p-(cp+p-m)=p-cp-p+m=m-cp
    @Override
    public int getCount() {
        return mMenus.size() > (curpage + 1) * pagesize ? pagesize : (mMenus.size() - curpage * pagesize);
    }

    @Override
    public Object getItem(int position) {
        return mMenus.get(position+curpage* pagesize );
    }

    @Override
    public long getItemId(int position) {
        return position+curpage * pagesize ;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHoder viewHoder=null;

        if(view==null){
            viewHoder=new ViewHoder();
            view= LayoutInflater.from(mContext).inflate(R.layout.item_gridview,null,false);
            viewHoder.tv_menuName= (TextView) view.findViewById(R.id.tv_menuName);
            viewHoder.im_menuIcon= (ImageView) view.findViewById(R.id.im_menuIcon);
            view.setTag(viewHoder);
        }else {
            viewHoder= (ViewHoder) view.getTag();
        }
        int pos = position+curpage * pagesize;
        viewHoder.tv_menuName.setText(mMenus.get(pos).getMenuName());
        viewHoder.im_menuIcon.setBackgroundResource(mMenus.get(pos).getIconId());
        return view;
    }

    class ViewHoder{

        private TextView tv_menuName;
        private ImageView im_menuIcon;
    }
}
