package com.qianwang.citylistmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.promeg.pinyinhelper.Pinyin;
import com.qianwang.citylistmenu.adapter.MyAdapter;
import com.qianwang.citylistmenu.custom.MySlideView;
import com.qianwang.citylistmenu.entity.City;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements MySlideView.onTouchListener {

    private List<City> mCityList = new ArrayList<City>();
    private Set<String> pinyinList = new LinkedHashSet<>();
    public static List<String> firstPinYin = new ArrayList<>();
    private RecyclerView recyclerView;
    private TextView my_circle_view;
    private MySlideView slideView;
    private LinearLayoutManager mLayoutManager;
    private City city;
    private TextView tv_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        recyclerView.setAdapter(new MyAdapter(this, mCityList));
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //找到第一个子view,然后显示文本
                View stickInfoView = recyclerView.findChildViewUnder(tv_header.getMeasuredWidth() / 2, 5);  //通过坐标找子view
                if (stickInfoView != null && stickInfoView.getContentDescription() != null) {
                    tv_header.setText(stickInfoView.getContentDescription().toString());
                }
                //获取header的下一个item，判断是否需要改变header
                View transInfoView = recyclerView.findChildViewUnder(tv_header.getMeasuredWidth() / 2, tv_header.getMeasuredHeight() + 1);
                if (transInfoView != null && transInfoView.getTag() != null) {
                    int state = (int) transInfoView.getTag();
                    int dealty = transInfoView.getTop() - tv_header.getMeasuredHeight();
                    if (state == MyAdapter.HAS_STICKY_VIEW) {
                        if (transInfoView.getTop() > 0) {
                            tv_header.setTranslationY(dealty);
                        } else {
                            tv_header.setTranslationY(0);
                        }
                    } else if (state == MyAdapter.NONE_STICKY_VIEW) {
                        tv_header.setTranslationY(0);
                    }
                }
            }
        });
    }

    private void initView() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        my_circle_view = (TextView) findViewById(R.id.my_circle_view);
        slideView = (MySlideView) findViewById(R.id.slideView);
        tv_header = (TextView) findViewById(R.id.tv_header);
        slideView.setListener(this);
        mCityList.clear();
        firstPinYin.clear();
        for (int i = 0; i < City.stringCitys.length; i++) {
            String cityStr = City.stringCitys[i];
            city = new City();
            city.setCityName(cityStr);
            city.setCityPinYin(transformPinYin(cityStr));
            mCityList.add(city);
        }
        Collections.sort(mCityList, new Comparator<City>() {
            @Override
            public int compare(City cityFirst, City citySecond) {
                return cityFirst.getCityPinYin().compareTo(citySecond.getCityPinYin());
            }
        });
        for (City city : mCityList) {
            pinyinList.add(city.getCityPinYin().substring(0, 1));
        }
        //去除重复
        for (String string : pinyinList) {
            firstPinYin.add(string);
        }
    }

    /**
     * 获取汉子的拼音
     * @param str
     * @return
     */

    public String transformPinYin(String str) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            sb.append(Pinyin.toPinyin(str.charAt(i)));
        }
        return sb.toString();
    }

    /**
     *
     * Recyclerview提供的接口方法
     *
     * @param str 显示的当前位置
     * @param dismiss 是否显示隐藏的textview
     */
    @Override
    public void showText(String str, boolean dismiss) {
        if (dismiss) {
            my_circle_view.setVisibility(View.GONE);
        } else {
            my_circle_view.setVisibility(View.VISIBLE);
            my_circle_view.setText(str);
        }
        int selectPosition = 0;
        for (int i = 0; i < mCityList.size(); i++) {
            if (mCityList.get(i).getFirstPinYin().equals(str)) {
                selectPosition = i;
                break;
            }
        }

        scrollPosition(selectPosition);
    }

    /**
     * 先传入要置顶第几项，然后区分情况处理
     * @param index
     */

    private void scrollPosition(int index) {
        //先从RecyclerView的LayoutManager中获取第一项和最后一项的Position
        int firsrPositon = mLayoutManager.findFirstVisibleItemPosition();
        int lastPosition = mLayoutManager.findLastVisibleItemPosition();

        if (index <= firsrPositon) {
            //当要置顶的项在当前显示的第一个项的前面时
            recyclerView.scrollToPosition(index);
        } else if (index <= lastPosition) {
            //当要置顶的项已经在屏幕上显示时
            int top = recyclerView.getChildAt(index - firsrPositon).getTop();
            recyclerView.scrollBy(0, top);
        } else {
            //当要置顶的项在当前显示的最后一项的后面时
            recyclerView.scrollToPosition(index);
            //这里这个变量是用在RecyclerView滚动监听里面的
            //move = true;
        }
    }
}
