package com.example.myapplication.glide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GlideChangeActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv_change)
    RecyclerView mRvChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_change);
        ButterKnife.bind(this);

        mTvTitle.setText("Glide图形变换");
        initRV();
    }

    private void initRV() {
        mRvChange.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        GlideRecyclerViewChangeAdapter adapter = new GlideRecyclerViewChangeAdapter(this);
        mRvChange.setAdapter(adapter);
    }
}
