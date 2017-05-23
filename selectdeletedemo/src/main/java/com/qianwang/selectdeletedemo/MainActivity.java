package com.qianwang.selectdeletedemo;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.qianwang.selectdeletedemo.adapter.MyRecyclerViewAdapter;
import com.qianwang.selectdeletedemo.bean.RecyclerItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<RecyclerItem> mList = new ArrayList<RecyclerItem>();
    private Toolbar toolbar;


    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void initData() {

        for (int i = 0; i < 20; i++) {
            RecyclerItem item = new RecyclerItem();
            item.setResID(R.mipmap.ic_launcher);
            item.setTitle("音乐");
            item.setS_title("music");
            mList.add(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        setSupportActionBar(toolbar);
        mRecyclerView.setAdapter(new MyRecyclerViewAdapter(this, mList));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {

            case R.id.edit:
                Toast.makeText(getApplicationContext(), "点击了编辑", Toast.LENGTH_SHORT).show();
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}
