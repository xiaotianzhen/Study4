package com.qianwang.bottonsheet;

import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.onItemClickListener {

    private NestedScrollView mScrollView;
    private List<String> mList = new ArrayList<String>();
    private BottomSheetDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mScrollView = (NestedScrollView) findViewById(R.id.nscrollview);
        initData();

    }

    public void intro(View view) {

        BottomSheetBehavior behavior = BottomSheetBehavior.from(findViewById(R.id.nscrollview));
        behavior.setPeekHeight(0);  //设置折叠后的高度
        mScrollView.setVisibility(View.VISIBLE);
        if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }

    public void select(View view) {

        View view1 = LayoutInflater.from(MainActivity.this).inflate(R.layout.recyclerview,null, false);
        RecyclerView recyclerView= (RecyclerView) view1.findViewById(R.id.recyclerview);
        ((LinearLayout)recyclerView.getParent()).removeView(recyclerView);
        MyAdapter adapter = new MyAdapter(MainActivity.this, mList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dialog = new BottomSheetDialog(this);
        dialog.setContentView(recyclerView);
        dialog.show();
        adapter.setListener(this);
    }


    public void initData() {

        for (int i = 0; i < 4; i++) {
            mList.add("item" + i);
        }
    }

    @Override
    public void onItemClick(View view, int position) {

       // Toast.makeText(MainActivity.this, position, Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }
}
