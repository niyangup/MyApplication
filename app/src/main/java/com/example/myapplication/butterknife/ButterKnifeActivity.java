package com.example.myapplication.butterknife;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ButterKnifeActivity extends Activity {

    @BindView(R.id.tv_title)
    public TextView mTvTitle;
    @BindView(R.id.cb_knife)
    public CheckBox mCb;
    @BindView(R.id.btn_knife)
    public Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        //初始化title
        mTvTitle.setText("ButterKnife");
    }

    //初始化checkbox的点击事件
    @OnClick(R.id.cb_knife)
    void cbButterKnife() {
        Toast.makeText(ButterKnifeActivity.this, "点击了checkBox", Toast.LENGTH_SHORT).show();
    }

    //初始化button的点击事件
    @OnClick(R.id.btn_knife)
    void buttonKnife() {
        Toast.makeText(ButterKnifeActivity.this, "点击了BUTTON", Toast.LENGTH_SHORT).show();
    }

}
