package com.qianwang.slideitemdelete;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.qianwang.slideitemdelete.adapter.RecyclerViewAdapter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  RecyclerViewAdapter.onItemClickListener{

    private MyRecyclerView mRecyclerView;
    private List<String> mList=new ArrayList<String>();
    private  RecyclerViewAdapter adapter;

    private void initView(){
        mRecyclerView= (MyRecyclerView) findViewById(R.id.mRecyclerView);
    }

    private void initData(){

        for (int i=0;i<40;i++){
            mList.add("第"+(i+1)+"条数据");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
         adapter=new RecyclerViewAdapter(this,mList,this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onDelete(View view, int Position) {
        adapter.removeRecycle(Position);
    }
}
