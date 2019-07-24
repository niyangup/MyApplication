package com.example.myapplication.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends Activity implements View.OnClickListener {

    private Button mBtnAdd;
    private Button mBtnDelete;
    private Button mBtnList;
    private Button mBtnFlow;
    private Button mBtnGrid;

    private TextView mTvTitle;

    private RecyclerView mRecyclerView;

    private List<String> mList;

    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        //初始化控件
        initView();

        //初始化数据
        initData();

        //添加分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //设置方向,默认定位第0条,无论是否反转
        mRecyclerView.setLayoutManager(new LinearLayoutManager(RecyclerViewActivity.this, LinearLayoutManager.VERTICAL, false));

        //设置进入时定位的第最后一条
        //mRecyclerView.scrollToPosition(mList.size() - 1);


        mAdapter = new MyAdapter();

        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {

            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        //初始化Title
        mTvTitle.setText("RecyclerView");

        //初始化集合数据
        mList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mList.add("这是数据" + i);
        }
    }

    private void initView() {
        mBtnAdd = findViewById(R.id.btn_add);
        mBtnDelete = findViewById(R.id.btn_delete);
        mBtnList = findViewById(R.id.btn_list);
        mBtnGrid = findViewById(R.id.btn_grid);
        mBtnFlow = findViewById(R.id.btn_flow);
        mRecyclerView = findViewById(R.id.recycleview);
        mTvTitle = findViewById(R.id.tv_title);

        //为按钮添加点击事件
        mBtnAdd.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
        mBtnList.setOnClickListener(this);
        mBtnGrid.setOnClickListener(this);
        mBtnFlow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //添加
            case R.id.btn_add:
                mAdapter.addData(0, "NEW_CONTENT");
                mRecyclerView.scrollToPosition(0);
                break;
            //删除
            case R.id.btn_delete:
                mAdapter.removeData(0);
                break;
            //list
            case R.id.btn_list:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(RecyclerViewActivity.this, LinearLayoutManager.VERTICAL, false));
                break;
            //grid
            case R.id.btn_grid:
                mRecyclerView.setLayoutManager(new GridLayoutManager(RecyclerViewActivity.this, 2, GridLayoutManager.VERTICAL, false));
                break;
            //瀑布流
            case R.id.btn_flow:
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
                break;
        }
    }

    public static interface OnItemClickListener {
        public void onItemClick(View view, String data);
    }

    private OnItemClickListener onItemClickListener = null;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        //相当于getView中的创建布局部分
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int positon) {
            View view = View.inflate(RecyclerViewActivity.this, R.layout.item_recycleview, null);
            return new MyViewHolder(view);
        }

        //相当于getView中的创建viewHolder绑定数据部分
        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int positon) {
            //根据位置获取数据
            String data = mList.get(positon);
            holder.mTvData.setText(data);
        }

        //总条数
        @Override
        public int getItemCount() {
            return mList.size();
        }

        public void addData(int postion, String data) {
            //集合加一
            mList.add(postion, data);

            //适配器item增加后刷新
            notifyItemInserted(postion);
        }

        public void removeData(int position) {
            //集合减一
            mList.remove(position);

            //适配器item减少后刷新
            notifyItemRemoved(position);
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView mIvIcon;
        public TextView mTvData;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mIvIcon = itemView.findViewById(R.id.iv_icon);
            mTvData = itemView.findViewById(R.id.tv_content);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(RecyclerViewActivity.this, "this is " + getLayoutPosition(), Toast.LENGTH_SHORT).show();
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v, mList.get(getLayoutPosition()));
                    }
                }
            });
        }

    }


}

