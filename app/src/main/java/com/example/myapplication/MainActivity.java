package com.example.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myapplication.fragment.BaseFragment;
import com.example.myapplication.fragment.CustomFragment;
import com.example.myapplication.fragment.FrameFragment;
import com.example.myapplication.fragment.OtherFragment;
import com.example.myapplication.fragment.ThirdFragment;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {


    private RadioGroup mRg;
    private ArrayList<BaseFragment> mFragmentList;

    private int position = 0;

    /**
     * 要显示的Fragment
     */
    private Fragment mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化Fragment集合
        initFragmentList();
        //初始化布局
        initView();


    }

    private void initFragmentList() {

        mFragmentList = new ArrayList<>();

        mFragmentList.add(new FrameFragment());
        mFragmentList.add(new ThirdFragment());
        mFragmentList.add(new CustomFragment());
        mFragmentList.add(new OtherFragment());
    }

    private void initView() {
        mRg = (RadioGroup) findViewById(R.id.rb_group);


        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {

                switch (id) {
                    case R.id.rb_frame:
                        Toast.makeText(MainActivity.this, "常用框架", Toast.LENGTH_SHORT).show();
                        position = 0;
                        break;
                    case R.id.rb_third:
                        Toast.makeText(MainActivity.this, "第三方", Toast.LENGTH_SHORT).show();
                        position = 1;
                        break;
                    case R.id.rb_custom:
                        Toast.makeText(MainActivity.this, "自定义", Toast.LENGTH_SHORT).show();
                        position = 2;
                        break;
                    case R.id.rb_other:
                        Toast.makeText(MainActivity.this, "其他", Toast.LENGTH_SHORT).show();
                        position = 3;
                        break;
                    default:
                        position = 0;
                        break;
                }
                BaseFragment to = mFragmentList.get(position);
                switchFrament(mContent, to);
            }
        });
        mRg.check(R.id.rb_frame);

    }

    private void switchFrament(Fragment from, Fragment to) {
        if (from != to) {
            //不相等时才切换
            mContent = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()) {
                //如果to没有被添加,则需要先隐藏from,之后添加并show,
                if (from != null) {
                    ft.hide(from);
                }

                if (to != null) {
                    ft.add(R.id.fl_content, to).show(to);
                }

                ft.commit();

            } else {
                //如果to被添加了,则只需要隐藏from后show to
                if (from != null) {
                    ft.hide(from);
                }

                if (to != null) {
                    ft.show(to).commit();
                }
            }
        }
    }

//    private void switchFrament(Fragment fragment) {
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fl_content, fragment);
//        transaction.commit();
//    }


}
