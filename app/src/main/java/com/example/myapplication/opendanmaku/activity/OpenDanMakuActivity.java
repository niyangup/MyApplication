package com.example.myapplication.opendanmaku.activity;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.opendanmaku.DanmakuItem;
import com.opendanmaku.DanmakuView;
import com.opendanmaku.IDanmakuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OpenDanMakuActivity extends AppCompatActivity implements View.OnClickListener {

    private DanmakuView mDanmakuView;
    private Button switcherBtn;
    private Button sendBtn;
    private EditText textEditText;
    private TextView mTitleBar;
    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_dan_maku);

        mDanmakuView = (DanmakuView) findViewById(R.id.danmakuView);
        switcherBtn = (Button) findViewById(R.id.switcher);
        sendBtn = (Button) findViewById(R.id.send);
        textEditText = (EditText) findViewById(R.id.text);
        mTitleBar = findViewById(R.id.tv_title);
        mVideoView = findViewById(R.id.videoView);

        videoSettings();

        mTitleBar.setText("OpenDanMaku弹幕");

        List<IDanmakuItem> list = initItems();
        Collections.shuffle(list);
        mDanmakuView.addItem(list, true);
        mDanmakuView.setPickItemInterval(500);

        switcherBtn.setOnClickListener(this);
        sendBtn.setOnClickListener(this);
    }

    private void videoSettings() {
        //设置准备好的监听
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mVideoView.start();//开始播放
            }
        });
        //设置播放完成
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mVideoView.start();//重新开始播放
            }
        });

        //设置播放出错
        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(OpenDanMakuActivity.this, "播放出错了", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        //设置控制面板
        mVideoView.setMediaController(new MediaController(this));
        //设置播放地址
//        videoView.setVideoPath("http://vfx.mtime.cn/Video/2016/12/12/mp4/161212190638286683_480.mp4");
        mVideoView.setVideoPath("http://vfx.mtime.cn/Video/2019/06/29/mp4/190629004821240734.mp4");
    }

    private List<IDanmakuItem> initItems() {
        List<IDanmakuItem> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            IDanmakuItem item = new DanmakuItem(this, i + " : plain text danmuku", mDanmakuView.getWidth());
            list.add(item);
        }

        String msg = " : text with image   ";
        for (int i = 0; i < 100; i++) {
            ImageSpan imageSpan = new ImageSpan(this, R.drawable.em);
            SpannableString spannableString = new SpannableString(i + msg);
            spannableString.setSpan(imageSpan, spannableString.length() - 2, spannableString.length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            IDanmakuItem item = new DanmakuItem(this, spannableString, mDanmakuView.getWidth(), 0, 0, 0, 1.5f);
            list.add(item);
        }
        return list;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDanmakuView.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDanmakuView.hide();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDanmakuView.clear();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.switcher:
                if (mDanmakuView.isPaused()) {
                    switcherBtn.setText(R.string.hide);
                    mDanmakuView.show();
                } else {
                    switcherBtn.setText(R.string.show);
                    mDanmakuView.hide();
                }
                break;
            case R.id.send:
                String input = textEditText.getText().toString();
                if (TextUtils.isEmpty(input)) {
                    Toast.makeText(OpenDanMakuActivity.this, R.string.empty_prompt, Toast.LENGTH_SHORT).show();
                } else {
                    IDanmakuItem item = new DanmakuItem(this, new SpannableString(input), mDanmakuView.getWidth(), 0, R.color.my_item_color, 0, 1);
//                    IDanmakuItem item = new DanmakuItem(this, input, mDanmakuView.getWidth());
//                    item.setTextColor(getResources().getColor(R.color.my_item_color));
//                    item.setTextSize(14);
//                    item.setTextColor(textColor);
                    mDanmakuView.addItemToHead(item);
                }
                textEditText.setText("");
                break;
        }
    }
}
