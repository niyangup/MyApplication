package com.example.myapplication.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdFragment extends BaseFragment {
    @Override
    public View initView() {
        TextView view = new TextView(mContext);
        view.setText("this is ThirdFragment");
        view.setTextColor(Color.BLACK);
        view.setTextSize(20);
        Toast.makeText(mContext, "布局被初始化了", Toast.LENGTH_SHORT).show();
        return view;
    }
}

