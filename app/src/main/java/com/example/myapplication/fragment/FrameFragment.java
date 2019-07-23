package com.example.myapplication.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.butterknife.ButterKnifeActivity;
import com.example.myapplication.eventbus.EventbusActivity;
import com.example.myapplication.json.activity.NativeJsonParseActivity;
import com.example.myapplication.okhttp.activity.FrameActivity;
import com.example.myapplication.recyclerview.RecyclerViewActivity;


public class FrameFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private ListView mListView;
    private String[] data = {"OKHttp", "nativeJsonParse", "ButterKnife", "EvenBus", "Gson", "RecyclerView", "FastJson", "xUtils3", "Retrofit2", "Fresco", "Glide", "greenDao", "RxJava", "volley", "picasso", "jcvideoplayer", "pulltorefresh", "Expandablelistview", "UniversalVideoView", "....."};

    @Override
    public View initView() {
        Toast.makeText(mContext, "布局被初始化了", Toast.LENGTH_SHORT).show();

        View view = View.inflate(mContext, R.layout.fragment_frame, null);
        mListView = (ListView) view.findViewById(R.id.list);

        mListView.setOnItemClickListener(this);
        final SwipeRefreshLayout mSRefresh = (SwipeRefreshLayout) view.findViewById(R.id.sfl);


        initSRefresh(mSRefresh);
        return view;
    }

    private void initSRefresh(final SwipeRefreshLayout mSRefresh) {
        mSRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSRefresh.setRefreshing(false);
                        Toast.makeText(mContext, "刷新完成", Toast.LENGTH_SHORT).show();
                    }
                }, 3000);
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        mListView.setAdapter(new MyAdapter());
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(mContext, "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
        TextView tv = (TextView) view;
        String content = tv.getText().toString();
        if (position == 0 && content.equalsIgnoreCase("okhttp")) {
            Intent intent = new Intent(mContext, FrameActivity.class);
            startActivity(intent);
        } else if (position == 1 && content.equalsIgnoreCase("nativeJsonParse")) {
            Intent intent = new Intent(mContext, NativeJsonParseActivity.class);
            startActivity(intent);
        } else if (content.equalsIgnoreCase("butterKnife")) {
            Intent intent = new Intent(mContext, ButterKnifeActivity.class);
            startActivity(intent);
        } else if (content.equalsIgnoreCase("evenBus")) {
            Intent intent = new Intent(mContext, EventbusActivity.class);
            startActivity(intent);
        } else if (content.equalsIgnoreCase("RecyclerView")) {
            Intent intent = new Intent(mContext, RecyclerViewActivity.class);
            startActivity(intent);
        }


    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.length;
        }

        @Override
        public String getItem(int i) {
            return data[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                TextView tv = new TextView(mContext);
                tv.setPadding(10, 35, 0, 35);
                view = tv;
                tv.setTextSize(20);
                tv.setTextColor(Color.BLACK);
                tv.setText(data[i]);
            } else {
                TextView tv = (TextView) view;
                tv.setTextSize(20);
                tv.setTextColor(Color.BLACK);
                tv.setText(data[i]);
            }
            return view;
        }
    }
}