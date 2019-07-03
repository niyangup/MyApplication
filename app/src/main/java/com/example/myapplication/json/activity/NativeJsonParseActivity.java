package com.example.myapplication.json.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.okhttp.domain.MyBean;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

public class NativeJsonParseActivity extends Activity {

    private static final String TAG = NativeJsonParseActivity.class.getSimpleName();
    private Button mBtnParse;
    private TextView mTvContent;
    private TextView mTvParse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_json_parse);

        mTvContent = findViewById(R.id.tv_content);
        mTvParse = findViewById(R.id.tv_parse);
        mBtnParse = findViewById(R.id.btn_parse);

        mBtnParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHtml();
            }
        });
    }

    public void getHtml() {
        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        OkHttpUtils
                .get()
                .url(url)
                .id(100)
                .build()
                .execute(new MyStringCallback());
    }

    public class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {
            setTitle("loading...");
        }

        @Override
        public void onAfter(int id) {
            setTitle("Sample-okHttp");
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            e.printStackTrace();
            mTvContent.setText("onError:" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            Log.e(TAG, "onResponse：complete");
            mTvContent.setText("onResponse:" + response);

            switch (id) {
                case 100:
                    Toast.makeText(NativeJsonParseActivity.this, "http", Toast.LENGTH_SHORT).show();
                    break;
                case 101:
                    Toast.makeText(NativeJsonParseActivity.this, "https", Toast.LENGTH_SHORT).show();
                    break;
            }
            parseJson(response);
        }

        @Override
        public void inProgress(float progress, long total, int id) {
            Log.e(TAG, "inProgress:" + progress);
            //mProgressBar.setProgress((int) (100 * progress));
        }
    }

    private void parseJson(String json) {
//        //解析Json数据 转化为Bean对象
//        Gson gson = new Gson();
//        DataInfo dataInfo = gson.fromJson(json, DataInfo.class);
//        mTvParse.setText(dataInfo.getTrailers().get(0).toString());

//        //解析Json数据,转化为List集合
//        String js = "[{\"id\":74862,\"movieName\":\"《爱宠大机密2》乡村冒险预告\",\"coverImg\":\"http://img5.mtime.cn/mg/2019/05/24/102859.56156442_120X90X4.jpg\",\"movieId\":236010},{\"id\":74838,\"movieName\":\"《哥斯拉2：怪兽之王》终极预告\",\"coverImg\":\"http://img5.mtime.cn/mg/2019/05/23/092948.24252228_120X90X4.jpg\",\"movieId\":213190}]";
//        Gson array = new Gson();
//        List<MyBean> data = array.fromJson(js, new TypeToken<List<MyBean>>() {
//        }.getType());
//        mTvParse.setText(data.get(0).toString() + "\n" + data.get(1).toString());

//        //将Java对象解析成Json数据
//        MyBean bean = new MyBean(1, "GKD名字", "hha.com", 1);
//        String toJson = array.toJson(bean);
//        mTvContent.setText(toJson);

        List<MyBean> MyBeanList = new ArrayList<>();
        MyBean bean1 = new MyBean(2, "名字2", "haha.com", 2);
        MyBean bean2 = new MyBean(3, "名字3", "hahha.com", 3);
        MyBeanList.add(bean1);
        MyBeanList.add(bean2);
        Gson gson = new Gson();
        String json1 = gson.toJson(MyBeanList);
        mTvParse.setText(json1);
    }
}
