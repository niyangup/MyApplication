package com.example.myapplication.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.domain.DataBean;
import com.example.myapplication.utils.CacheUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

public class OKHttpImageListActivity extends AppCompatActivity {
    private static final String TAG = OKHttpImageListActivity.class.getSimpleName();
    private ListView mLv;
    private ProgressBar mPb;
    private TextView mTvNoData;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttplist);
        initView();
        getDataFromNet();

    }

    private void getDataFromNet() {
        url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        String saveJson = CacheUtils.getString(this, url);
        if(!TextUtils.isEmpty(saveJson)) {
            //如果有缓存,则先解析
            processData(saveJson);
        }

        OkHttpUtils
                .get()
                .url(url)
                .id(100)
                .build()
                .execute(new MyStringCallback());
    }

    private void initView() {
        mLv = findViewById(R.id.lv);
        mPb = findViewById(R.id.pbar);
        mTvNoData = findViewById(R.id.tv_nodata);
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
            mTvNoData.setVisibility(View.VISIBLE);
        }

        @Override
        public void onResponse(String response, int id) {
            Log.e(TAG, "onResponse：complete");
            mTvNoData.setVisibility(View.GONE);
            switch (id) {
                case 100:
                    Toast.makeText(OKHttpImageListActivity.this, "http", Toast.LENGTH_SHORT).show();
                    break;
                case 101:
                    Toast.makeText(OKHttpImageListActivity.this, "https", Toast.LENGTH_SHORT).show();
                    break;
            }
            Log.i(TAG, "this is json:" + response);

            //解析数据和显示数据
            if (response != null) {
                //设置缓存数据
                CacheUtils.putString(OKHttpImageListActivity.this, url, response);
                processData(response);
            }
        }

        @Override
        public void inProgress(float progress, long total, int id) {
            Log.e(TAG, "inProgress:" + progress);
//            mPb.setProgress((int) (100 * progress));
        }
    }

    /**
     * 解析和显示数据
     *
     * @param json
     */
    private void processData(String json) {
        Gson gson = new Gson();
        DataBean dataBean = gson.fromJson(json, DataBean.class);
//        DataBean dataBean = parsedJson(json);
        List<DataBean.ItemData> datas = dataBean.getTrailers();
        Log.i(TAG, "this is datas size:" + datas.size());

        if (datas != null && datas.size() > 0) {
            //有数据,显示适配器
            mTvNoData.setVisibility(View.GONE);
            mLv.setAdapter(new MyApdapter(datas));
        } else {
            //没有数据,tv_nodata显示
            mTvNoData.setVisibility(View.VISIBLE);
        }
        mPb.setVisibility(View.GONE);
    }

    /**
     * 解析json数据
     *
     * @param response
     * @return
     */
    private DataBean parsedJson(String response) {
        DataBean dataBean = new DataBean();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.optJSONArray("trailers");
            if (jsonArray != null && jsonArray.length() > 0) {
                List<DataBean.ItemData> trailers = new ArrayList<>();
                dataBean.setTrailers(trailers);
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObjectItem = (JSONObject) jsonArray.get(i);

                    if (jsonObjectItem != null) {

                        DataBean.ItemData mediaItem = new DataBean.ItemData();

                        String movieName = jsonObjectItem.optString("movieName");//name
                        mediaItem.setMovieName(movieName);

                        String videoTitle = jsonObjectItem.optString("videoTitle");//desc
                        mediaItem.setVideoTitle(videoTitle);

                        String imageUrl = jsonObjectItem.optString("coverImg");//imageUrl
                        mediaItem.setCoverImg(imageUrl);

                        String hightUrl = jsonObjectItem.optString("hightUrl");//data
                        mediaItem.setHightUrl(hightUrl);

                        //把数据添加到集合
                        trailers.add(mediaItem);
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dataBean;
    }

    private class MyApdapter extends BaseAdapter {
        private List<DataBean.ItemData> dataList;

        public MyApdapter(List<DataBean.ItemData> datas) {
            this.dataList = datas;
        }

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(OKHttpImageListActivity.this, R.layout.item_list_okphttp, null);
                holder = new ViewHolder();
                holder.iv_icon = convertView.findViewById(R.id.iv_icon);
                holder.tv_name = convertView.findViewById(R.id.tv_name);
                holder.tv_desc = convertView.findViewById(R.id.tv_desc);
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            DataBean.ItemData itemData = dataList.get(position);
            holder.tv_name.setText(itemData.getMovieName());
            holder.tv_desc.setText(itemData.getVideoTitle());
            getImage(itemData.getCoverImg(), holder.iv_icon);

            return convertView;
        }

        public void getImage(String url, final ImageView iv) {
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
//                            mTv.setText("onError:" + e.getMessage());
                            Toast.makeText(OKHttpImageListActivity.this, "onError:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(Bitmap bitmap, int id) {
                            Log.e("TAG", "onResponse：complete");
                            iv.setImageBitmap(bitmap);
                        }
                    });
        }
    }

    static class ViewHolder {
        ImageView iv_icon;
        TextView tv_name;
        TextView tv_desc;
    }
}
