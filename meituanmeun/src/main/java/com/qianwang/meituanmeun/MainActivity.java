package com.qianwang.meituanmeun;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.qianwang.meituanmeun.adapter.GridViewAdapter;
import com.qianwang.meituanmeun.adapter.ViewpagerAdapter;
import com.qianwang.meituanmeun.bean.Contains;
import com.qianwang.meituanmeun.bean.Menu;

import java.util.ArrayList;
import java.util.List;

import static com.qianwang.meituanmeun.R.id.mViewpager;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<Menu> mMenus = new ArrayList<Menu>();
    private String[] menuNames = {"美食", "电影", "酒店住宿", "休闲娱乐", "外卖", "自助餐", "KTV", "机票/火车票", "周边游", "美甲美睫",
            "火锅", "生日蛋糕", "甜品饮品", "水上乐园", "汽车服务", "美发", "丽人", "景点", "足疗按摩", "运动健身", "健身", "超市", "买菜",
            "今日新单", "小吃快餐", "面膜", "洗浴/汗蒸", "母婴亲子", "生活服务", "婚纱摄影", "学习培训", "家装", "结婚", "全部分配"};
    private int pageSize = 10;
    private Menu menu;
    private List<View> mViews = new ArrayList<View>();
    private LinearLayout ll_point;
    private int page = 0;


    private void initView() {
        mViewPager = (ViewPager) findViewById(mViewpager);
        ll_point = (LinearLayout) findViewById(R.id.llayout_point);

    }

    private void initData() {

        for (int i = 0; i < menuNames.length; i++) {
            menu = new Menu();
            int iconId = getResources().getIdentifier(Contains.iconStr + i, "mipmap", getPackageName());
            menu.setMenuName(menuNames[i]);
            menu.setIconId(iconId);
            mMenus.add(menu);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

        if (menuNames.length % pageSize == 0) {
            page = menuNames.length / pageSize;
        } else {
            page = menuNames.length / pageSize + 1;
        }
        Log.i("520it", "" + "***********************************");
        for (int i = 0; i < page; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.menu_gridview, mViewPager, false);
            GridView gridView = (GridView) view.findViewById(R.id.Gridv_menu);
            gridView.setAdapter(new GridViewAdapter(mMenus, i, pageSize, getApplicationContext()));
            mViews.add(gridView);
        }
        mViewPager.setAdapter(new ViewpagerAdapter(mViews));
        setPoint();
    }


    public void setPoint() {

        for (int i = 0; i < page; i++) {
            ll_point.addView(LayoutInflater.from(this).inflate(R.layout.item_point,null));
        }

        //设置默认在第一页
        ll_point.getChildAt(0).findViewById(R.id.v_dot).setBackgroundResource(R.drawable.dot_selected);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                ll_point.getChildAt(0).findViewById(R.id.v_dot).setBackgroundResource(R.drawable.dot_selected);

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
