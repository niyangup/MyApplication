package com.example.myapplication.eventbus;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusSendActivity extends Activity implements View.OnClickListener {

    private Button mBtnSendInMain;
    private Button mBtnSendInStickiness;
    private TextView mTvResult;
    private TextView mTvTitle;
    private boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_send);

        //初始化控件
        initView();

        //初始化数据
        initData();

        //为两个按钮设置点击事件
        initOnclickListener();

    }

    private void initOnclickListener() {
        mBtnSendInMain.setOnClickListener(this);
        mBtnSendInStickiness.setOnClickListener(this);
    }

    private void initData() {
        mTvTitle.setText("EventBus");
    }

    private void initView() {
        mTvTitle = findViewById(R.id.tv_title);
        mBtnSendInMain = findViewById(R.id.btn_send_in_main);
        mBtnSendInStickiness = findViewById(R.id.btn_send_in_stickiness);
        mTvResult = findViewById(R.id.tv_result);
    }

    //3.接收粘性事件
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void stickyEventBus(StickyEvent event) {
        mTvResult.setText(event.msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //主程序发送数据
            case R.id.btn_send_in_main:
                //4.发送消息
                EventBus.getDefault().post(new MessageEvent("主线程发送过来的消息"));
                //发送完毕后销毁当前页面
                EventBusSendActivity.this.finish();
                break;

            //接收粘性事件
            case R.id.btn_send_in_stickiness:
                //4.注册

                if (isFirst) {
                    EventBus.getDefault().register(EventBusSendActivity.this);
                    isFirst = false;
                }

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //5.注销
        //移除所有的粘性事件
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(EventBusSendActivity.this);
    }
}
