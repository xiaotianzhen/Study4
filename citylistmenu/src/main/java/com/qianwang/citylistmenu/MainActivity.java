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

                View stickInfoView = recyclerView.findChildViewUnder(tv_header.getMeasuredWidth() / 2, 5);
                if (stickInfoView != null && stickInfoView.getContentDescription() != null) {
                    tv_header.setText(stickInfoView.getContentDescription().toString());
                }

                View transInfoView = recyclerView.findChildViewUnder(tv_header.getMeasuredWidth() / 2, tv_header.getMeasuredHeight() + 1);
                if (transInfoView != null && transInfoView.getTag() != null) {
                    Log.i("520it", "transInfoView.getTag()" + "***********************************"+transInfoView.getTag());
                    int state = (int) transInfoView.getTag();
                    int dealty = transInfoView.getTop() - tv_header.getMeasuredHeight();
                    if (state == MyAdapter.HAS_STICKY_VIEW) {
                        if(transInfoView.getTop()>0){
                           tv_header.setTranslationY(dealty);
                        }else {
                            tv_header.setTranslationY(0);
                        }

                    } else if (state==MyAdapter.NONE_STICKY_VIEW) {
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
        Log.i("520it", "firstPinYin" + "***********************************" + firstPinYin);
    }

    public String transformPinYin(String str) {

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            sb.append(Pinyin.toPinyin(str.charAt(i)));
        }
        return sb.toString();
    }

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

    private void scrollPosition(int index) {

        int firsrPositon = mLayoutManager.findFirstVisibleItemPosition();
        int lastPosition = mLayoutManager.findLastVisibleItemPosition();

        if (index <= firsrPositon) {
            recyclerView.scrollToPosition(index);
        } else if (index <= lastPosition) {
            int top = recyclerView.getChildAt(index - firsrPositon).getTop();
            recyclerView.scrollBy(0, top);
        } else {
            recyclerView.scrollToPosition(index);
        }
    }
}
