package com.example.myapplication.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
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
    private String TAG = FrameActivity.class.getSimpleName();
    private Button mBtnOKutils;
    private ProgressBar mProgressBar;
    private Button mBtnDownload;
    private Button mBtnUpFiles;
    private Button mBtnImage;
    private ImageView mImageView;
    private Button mBtnImageList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        mBtnGetPost = findViewById(R.id.btn_get_post);
        mTvContent = findViewById(R.id.tv_content);
        mBtnDownload = findViewById(R.id.btn_download);
        mBtnOKutils = findViewById(R.id.btn_okhttp_utils);
        mProgressBar = findViewById(R.id.pb);
        mBtnUpFiles = findViewById(R.id.btn_upfile);
        mBtnImage = findViewById(R.id.btn_image);
        mImageView = findViewById(R.id.iv);
        mBtnImageList = findViewById(R.id.btn_change);

        mBtnDownload.setOnClickListener(this);
        mBtnGetPost.setOnClickListener(this);
        mBtnOKutils.setOnClickListener(this);
        mBtnUpFiles.setOnClickListener(this);
        mBtnImage.setOnClickListener(this);
        mBtnImageList.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get_post:
//                getDataFromUrl(url);
                getDataFromPost(url);
                break;
            case R.id.btn_okhttp_utils:
//                getDataByOKHttpUtils();
                postDataByOKHttpUtils();
                break;
            case R.id.btn_download:
                downloadFile();
                break;
            case R.id.btn_upfile:
                multiFileUpload();
                break;
            case R.id.btn_image:
                getImage();
                break;
            case R.id.btn_change:
                Intent intent = new Intent(FrameActivity.this, OKHttpImageListActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void getDataFromPost(final String url) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    final String result = postMethod(url, "");
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

    public void downloadFile() {
        String url = "http://vfx.mtime.cn/Video/2019/06/28/mp4/190628075308350550.mp4";
        OkHttpUtils//
                .get()//
                .url(url)//
                .build()//
                .execute(new FileCallBack(getExternalCacheDir().getAbsolutePath(), "text.mp4")//
                {

                    @Override
                    public void onBefore(Request request, int id) {
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        mProgressBar.setProgress((int) (100 * progress));
                        Log.e(TAG, "inProgress :" + (int) (100 * progress));
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError :" + e.getMessage());
                    }

                    @Override
                    public void onResponse(File file, int id) {
                        Log.e(TAG, "onResponse :" + file.getAbsolutePath());
                    }
                });
    }

    public void getDataByOKHttpUtils() {
        String url = "http://www.zhiyun-tech.com/App/Rider-M/changelog-zh.txt";
//        url="http://www.391k.com/api/xapi.ashx/info.json?key=bd_hyrzjjfb4modhj&size=10&page=1";
        url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        OkHttpUtils
                .get()
                .url(url)
                .id(100)
                .build()
                .execute(new MyStringCallback());
    }

    public void postDataByOKHttpUtils() {
        String url = "http://www.zhiyun-tech.com/App/Rider-M/changelog-zh.txt";
//        url="http://www.391k.com/api/xapi.ashx/info.json?key=bd_hyrzjjfb4modhj&size=10&page=1";
        url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        OkHttpUtils
                .post()
                .url(url)
                .id(100)
                .build()
                .execute(new MyStringCallback());
    }


    private class MyStringCallback extends StringCallback {
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
                    Toast.makeText(FrameActivity.this, "http", Toast.LENGTH_SHORT).show();
                    break;
                case 101:
                    Toast.makeText(FrameActivity.this, "https", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        @Override
        public void inProgress(float progress, long total, int id) {
            Log.e(TAG, "inProgress:" + progress);
            mProgressBar.setProgress((int) (100 * progress));
        }
    }


    /**
     * 多文件上传
     */
    public void multiFileUpload() {
        String mBaseUrl = "http://192.168.43.191:8081/FileUpload/FileUploadServlet";

        File file = new File(getExternalCacheDir().getAbsolutePath(), "hha.PNG");
        File file2 = new File(getExternalCacheDir().getAbsolutePath(), "test.txt");
        if (!file.exists() || !file2.exists()) {
            Toast.makeText(FrameActivity.this, "文件不存在，请修改文件路径", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("username", "niyang");
        params.put("password", "123");

        String url = mBaseUrl;
        OkHttpUtils.post()//
                .addFile("mFile", "hha1.PNG", file)//
                .addFile("mFile", "test2.txt", file2)//
                .url(url)
                .params(params)//
                .build()//
                .execute(new MyStringCallback());
    }

    public void getImage() {
        mTvContent.setText("");
        String url = "http://images.csdn.net/20150817/1.jpg";
        OkHttpUtils
                .get()//
                .url(url)//
                .tag(this)//
                .build()//
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mTvContent.setText("onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(Bitmap bitmap, int id) {
                        Log.e("TAG", "onResponse：complete");
                        mImageView.setImageBitmap(bitmap);
                    }
                });
    }

}
