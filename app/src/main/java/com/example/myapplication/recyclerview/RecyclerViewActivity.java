package com.example.myapplication.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

public class RecyclerViewActivity extends Activity implements View.OnClickListener {

    private Button mBtnAdd;
    private Button mBtnDelete;
    private Button mBtnList;
    private Button mBtnFlow;
    private Button mBtnGrid;
    private TextView mTvTitle;

    private RecyclerView mRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        //初始化控件
        initView();

        //初始化Title
        initData();

        //为按钮添加点击事件
        mBtnAdd.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
        mBtnList.setOnClickListener(this);
        mBtnGrid.setOnClickListener(this);
        mBtnFlow.setOnClickListener(this);

    }

    private void initData() {
        mTvTitle.setText("RecyclerView");
    }

    private void initView() {
        mBtnAdd = findViewById(R.id.btn_add);
        mBtnDelete = findViewById(R.id.btn_delete);
        mBtnList = findViewById(R.id.btn_list);
        mBtnGrid = findViewById(R.id.btn_grid);
        mBtnFlow = findViewById(R.id.btn_flow);
        mRv = findViewById(R.id.recycleview);
        mTvTitle = findViewById(R.id.tv_title);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //添加
            case R.id.btn_add:

                break;
            //删除
            case R.id.btn_delete:

                break;
            //list
            case R.id.btn_list:

                break;
            //grid
            case R.id.btn_grid:

                break;
            //瀑布流
            case R.id.btn_flow:

                break;
        }
    }
}
