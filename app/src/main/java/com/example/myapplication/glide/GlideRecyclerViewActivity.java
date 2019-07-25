package com.example.myapplication.glide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.okhttp.domain.DataBean;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GlideRecyclerViewActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_glide)
    RecyclerView mRvGlide;

    private List<DataBean.ItemData> data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_recycler_view);
        ButterKnife.bind(this);

        mTvTitle.setText("在RecyclerView中加载图片");

        getList();

//        initData();

    }


    private void getList() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                connNetGetData();
            }
        }.start();
    }

    private String connNetGetData() {
        String content = null;
        try {
            String path = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");

            int code = conn.getResponseCode();
            if (code == 200) {
                InputStream in = conn.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int len = -1;
                byte[] buffer = new byte[1024];

                while ((len = in.read(buffer)) != -1) {

                    baos.write(buffer, 0, len);
                }


                content = new String(baos.toByteArray(), "UTF-8");

                parseJson(content);
                in.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return content;
    }

    private void parseJson(String content) {
        Gson gson = new Gson();
        DataBean dataBean = gson.fromJson(content, DataBean.class);

//        List<DataBean.ItemData> data = dataBean.getTrailers();
        data = dataBean.getTrailers();
        Log.v("TAG", "大小有" + data.size());
        Log.v("TAG", "url:" + data.get(0).getCoverImg());


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecyclerView();
            }
        });
    }


    private void initRecyclerView() {
        //初始化RecyclerView
        mRvGlide.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        GlideRecyclerViewAdapter adapter = new GlideRecyclerViewAdapter(this, data);
        mRvGlide.setAdapter(adapter);

        adapter.setOnItemClickListener(new GlideRecyclerViewAdapter.OnItemClickLisener() {
            @Override
            public void onItemClick(View view) {
                Toast.makeText(GlideRecyclerViewActivity.this, "this is onitem", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
