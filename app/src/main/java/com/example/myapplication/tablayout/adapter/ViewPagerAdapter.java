package com.example.myapplication.tablayout.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.myapplication.tablayout.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    ArrayList<MyFragment> mData;

    public ViewPagerAdapter(FragmentManager fm, ArrayList<MyFragment> list) {
        super(fm);
        mData = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    //得到页面的标题
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mData.get(position).getTitle();
    }
}
