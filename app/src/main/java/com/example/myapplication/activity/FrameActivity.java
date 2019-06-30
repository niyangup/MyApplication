package com.example.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FrameActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnGetPost;
    private TextView mTvContent;
    private OkHttpClient client = new OkHttpClient();

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    /**
     * 测试url
     */
    private String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        mBtnGetPost = findViewById(R.id.btn_get_post);
        mTvContent = findViewById(R.id.tv_content);

        mBtnGetPost.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get_post:
//                getDataFromUrl(url);
                getDataFromPost(url);

                break;
        }
    }

    private void getDataFromPost(final String url) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    final String result = postMethod(url,"");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTvContent.setText(result);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void getDataFromUrl(final String url) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    final String result = getMethod(url);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTvContent.setText(result);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }


    private String getMethod(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();

    }





    private String postMethod(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
            return response.body().string();

    }

}
