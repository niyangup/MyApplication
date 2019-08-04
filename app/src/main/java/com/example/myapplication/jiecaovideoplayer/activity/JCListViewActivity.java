package com.example.myapplication.jiecaovideoplayer.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class JCListViewActivity extends AppCompatActivity implements View.OnClickListener {

    Button mNormalList, mViewPagerList, mMultiHolderList, mRecyleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jie_cao_list_view);

        mNormalList = (Button) findViewById(R.id.normal_list);
        mViewPagerList = (Button) findViewById(R.id.viewpayer_list);
        mMultiHolderList = (Button) findViewById(R.id.multi_holder_list);
        mRecyleView = (Button) findViewById(R.id.recyleview);

        mNormalList.setOnClickListener(this);
        mViewPagerList.setOnClickListener(this);
        mMultiHolderList.setOnClickListener(this);
        mRecyleView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.normal_list:
                startActivity(new Intent(JCListViewActivity.this, ListViewNormalActivity.class));
                break;
            case R.id.viewpayer_list:
                startActivity(new Intent(JCListViewActivity.this, ListViewViewpagerActivity.class));
                break;
            case R.id.multi_holder_list:
                startActivity(new Intent(JCListViewActivity.this, ListViewMultiHolderActivity.class));
                break;
//            case R.id.recyleview:
//                startActivity(new Intent(JCListViewActivity.this, RecyclerViewNormalActivity.class));
//                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
