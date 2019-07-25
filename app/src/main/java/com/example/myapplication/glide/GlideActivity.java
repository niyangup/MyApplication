package com.example.myapplication.glide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GlideActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        ButterKnife.bind(this);

        initData();

    }

    @OnClick(R.id.btn_base)
    public void baseOnclick(View view) {
        Intent intent = new Intent(GlideActivity.this, GlideBaseActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_list_pic)
    public void listOnclick(View view) {
        Intent intent = new Intent(GlideActivity.this, GlideRecyclerViewActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_change_pic)
    public void changeOnclick(View view) {
        Intent intent = new Intent(GlideActivity.this, GlideChangeActivity.class);
        startActivity(intent);
    }

    private void initData() {
        mTvTitle.setText("Glide");
    }


}
