package com.example.myapplication.tablayout.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.tablayout.adapter.ViewPagerAdapter;
import com.example.myapplication.tablayout.fragment.MyFragment;

import java.util.ArrayList;

public class TabLayoutActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private TextView mTvTitle;
    private ArrayList<MyFragment> fragments;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        initView();

        initViewPager();
    }

    private void initViewPager() {
        fragments = new ArrayList<>();
        //初始化fragment
        for (int i = 0; i < 18; i++) {
            fragments.add(new MyFragment("标题" + i, "内容" + i));
        }

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);
        //设置tablayout
        mTabLayout.setupWithViewPager(mViewPager);
        //设置tab滚动
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void initView() {
        mTvTitle = findViewById(R.id.tv_title);
        mViewPager = findViewById(R.id.vp_pager);
        mTabLayout = findViewById(R.id.tablayout);

        mTvTitle.setText("TabLayout");
    }
}
