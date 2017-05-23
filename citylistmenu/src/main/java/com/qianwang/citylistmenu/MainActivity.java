package com.qianwang.citylistmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
    public static List<String>  firstPinYin= new ArrayList<>();
    private RecyclerView recyclerView;
    private TextView my_circle_view;
    private MySlideView slideView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        recyclerView.setAdapter(new MyAdapter(this, mCityList));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initView() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        my_circle_view = (TextView) findViewById(R.id.my_circle_view);
        slideView = (MySlideView) findViewById(R.id.slideView);
        slideView.setListener(this);

        mCityList.clear();
        firstPinYin.clear();
        for (int i = 0; i < City.stringCitys.length; i++) {
            String cityStr = City.stringCitys[i];
            City city = new City();
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
        Log.i("520it", "firstPinYin" + "***********************************"+firstPinYin);
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
        int selectPosition=0;

        for(int i=0;i<mCityList.size();i++){
              if(mCityList.get(i).getFirstPinYin().equals(str)){
                  selectPosition=i;
                  break;
              }
        }

        scrollPosition(selectPosition);
    }

    private void scrollPosition(int selectPosition) {




    }
}
