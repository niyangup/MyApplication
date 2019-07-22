package com.example.myapplication.eventbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/*发送普通事件
 * 1.注册广播
 * 2.注销广播
 * 3.创建消息类 MessageEvent
 * 4.发送消息
 * 5.接收消息
 * 注册->发送->接收->注销
 * */

/*发送粘性事件
 * 1.创建消息类 StickyEvent
 * 2.发送消息
 * 3.接收消息
 * 4.注册
 * 5.注销
 * 发送->接收->(需要接收消息时)注册(注册后才能接收到消息)->注销
 * */
public class EventbusActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnSkip;
    private Button mBtnSendToSkip;
    private TextView mTvContent;
    private TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus);

        //初始化控件
        initView();

        //初始化数据
        initData();

        //为两个按钮设置点击事件
        initOnclickListener();
    }

    private void initOnclickListener() {
        mBtnSendToSkip.setOnClickListener(this);
        mBtnSkip.setOnClickListener(this);
    }

    private void initData() {
        mTvTitle.setText("EventBus");

        //1.注册广播
        EventBus.getDefault().register(EventbusActivity.this);
    }

    private void initView() {
        mTvTitle = findViewById(R.id.tv_title);
        mBtnSkip = findViewById(R.id.btn_skip);
        mBtnSendToSkip = findViewById(R.id.btn_send_to_skip);
        mTvContent = findViewById(R.id.tv_content);
    }

    //5.接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void MessageEventBus(MessageEvent event) {
        //显示接收的消息
        mTvContent.setText(event.name);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //2.注销广播
        EventBus.getDefault().unregister(EventbusActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //跳转到发送页面
            case R.id.btn_skip:
                Intent intent = new Intent(EventbusActivity.this, EventBusSendActivity.class);
                startActivity(intent);
                break;

            //发送粘性事件跳转到发送页面
            case R.id.btn_send_to_skip:
                //2.发送粘性事件
                EventBus.getDefault().postSticky(new StickyEvent("这是粘性事件"));
                //跳转到发送数据的页面
                Intent intent1 = new Intent(EventbusActivity.this, EventBusSendActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
