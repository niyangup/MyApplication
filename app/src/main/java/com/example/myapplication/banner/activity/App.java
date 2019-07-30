package com.example.myapplication.banner.activity;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.zxy.recovery.core.Recovery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class App extends Application {
    public static List<?> images=new ArrayList<>();
    public static List<String> titles=new ArrayList<>();
    public static int H;
    @Override
    public void onCreate() {
        super.onCreate();
        H=getScreenH(this);
//        Fresco.initialize(this);
        Recovery.getInstance()
                .debug(true)
                .recoverInBackground(false)
                .recoverStack(true)
                .mainPage(MainActivity.class)
                .init(this);
        String[] urls = getResources().getStringArray(R.array.url4);
        String[] tips = getResources().getStringArray(R.array.title);
        List list = Arrays.asList(urls);
        images = new ArrayList(list);
        titles= Arrays.asList(tips);
    }
    public int getScreenH(Context aty) {
        DisplayMetrics dm = aty.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }
}
