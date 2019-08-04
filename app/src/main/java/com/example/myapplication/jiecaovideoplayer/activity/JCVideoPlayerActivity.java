package com.example.myapplication.jiecaovideoplayer.activity;


import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerSimple;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class JCVideoPlayerActivity extends AppCompatActivity implements View.OnClickListener {


    JCVideoPlayer.JCAutoFullscreenListener mSensorEventListener;
    //传感器
    SensorManager mSensorManager;


    JCVideoPlayerStandard mJcVideoPlayerStandard;
    JCVideoPlayerSimple mJcVideoPlayerSimple;

    Button mTinyWindow, mAutoTinyWindow, mAboutListView, mAboutUI, mPlayDirectly, mAboutWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jie_cao_video_player);

        //初始化
        mTinyWindow = (Button) findViewById(R.id.tiny_window);
        mAutoTinyWindow = (Button) findViewById(R.id.auto_tiny_window);
        mAboutUI = (Button) findViewById(R.id.play_directly_without_layout);
        mAboutListView = (Button) findViewById(R.id.about_listview);
        mPlayDirectly = (Button) findViewById(R.id.about_ui);
        mAboutWebView = (Button) findViewById(R.id.about_webview);


        mTinyWindow.setOnClickListener(this);
        mAutoTinyWindow.setOnClickListener(this);
        mAboutListView.setOnClickListener(this);
        mAboutUI.setOnClickListener(this);
        mPlayDirectly.setOnClickListener(this);
        mAboutWebView.setOnClickListener(this);

        //设置简单的视频播放
        mJcVideoPlayerSimple = (JCVideoPlayerSimple) findViewById(R.id.simple_demo);
        mJcVideoPlayerSimple.setUp("http://devimages.apple.com/iphone/samples/bipbop/gear1/prog_index.m3u8"
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "标题");


        //标准的UI控件
        mJcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.jc_video);

        mJcVideoPlayerStandard.setUp("http://vfx.mtime.cn/Video/2019/06/29/mp4/190629004821240734.mp4"
                //设置屏幕方向,设置标题
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "标题");


        //设置封面
        Glide.with(this)
                .load("http://img5.mtime.cn/mg/2019/06/29/002009.16684021_120X90X4.jpg")
                .into(mJcVideoPlayerStandard.thumbImageView);


//        JCVideoPlayer.setJcUserAction(new MyUserActionStandard());
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorEventListener = new JCVideoPlayer.JCAutoFullscreenListener();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //注册传感器
        Sensor accelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(mSensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    protected void onPause() {
        super.onPause();
        //取消注册传感器
        mSensorManager.unregisterListener(mSensorEventListener);
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tiny_window:
                //小窗口播放视频
                mJcVideoPlayerStandard.startWindowTiny();
                break;
            case R.id.about_listview:
                startActivity(new Intent(this, JCListViewActivity.class));
                break;
//            case R.id.about_ui:
//                startActivity(new Intent(this, UISmallChangeActivity.class));
//                break;
//            case R.id.about_webview:
//                startActivity(new Intent(this, WebViewActivity.class));
//                break;
        }
    }
}
