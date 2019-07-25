package com.example.myapplication.glide;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.example.myapplication.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GlideBaseActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_one)
    TextView tvOne;
    @BindView(R.id.img_one)
    ImageView imgOne;
    @BindView(R.id.tv_two)
    TextView tvTwo;
    @BindView(R.id.img_two)
    ImageView imgTwo;
    @BindView(R.id.tv_three)
    TextView tvThree;
    @BindView(R.id.img_three)
    ImageView imgThree;
    @BindView(R.id.tv_four)
    TextView tvFour;
    @BindView(R.id.img_four)
    ImageView imgFour;
    @BindView(R.id.tv_five)
    TextView tvFive;
    @BindView(R.id.img_five)
    ImageView imgFive;
    @BindView(R.id.tv_six)
    TextView tvSix;
    @BindView(R.id.img_six)
    ImageView imgSix;
    @BindView(R.id.tv_seven)
    TextView tvSeven;
    @BindView(R.id.img_seven)
    ImageView imgSeven;
    @BindView(R.id.tv_eight)
    TextView tvEight;
    @BindView(R.id.img_eight)
    ImageView imgEight;
    @BindView(R.id.tv_nine)
    TextView tvNine;
    @BindView(R.id.img_nine)
    ImageView imgNine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_base);
        ButterKnife.bind(this);

        initData();
    }

    @SuppressLint("CheckResult")
    private void initData() {
        tvTitle.setText("基本用法");

        //1.加载网络图片
        tvOne.setText("1.加载网络图片");
        Glide.with(this).load("http://img5.mtime.cn/mg/2019/06/27/224744.68512147_120X90X4.jpg").into(imgOne);

        //2.加载资源图片
        tvTwo.setText("2.加载资源图片");
        Glide.with(this).load(R.drawable.atguigu_logo).into(imgTwo);

        //3.加载本地图片
        tvThree.setText("3.加载本地图片");
        String path = getExternalCacheDir().getAbsolutePath() + "/hha.png";
        File file = new File(path);
        Uri uri = Uri.fromFile(file);
        Glide.with(this).load(uri).into(imgThree);

        //4.加载网络gif图
        tvFour.setText("4.加载网络gif图");
        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564029595891&di=f96664a02ea919bc79c47075ba2711c5&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20170816%2F379ecf5920a64a2f9638161b5ea3e8d3.jpg";
        Glide.with(this).load(url).placeholder(R.drawable.ic_launcher_background).into(imgFour);

        //5.加载资源gif图
        tvFive.setText("5.加载资源gif图");
        Glide.with(this).load(R.drawable.test).placeholder(R.drawable.ic_launcher_background).into(imgFive);

        //6.加载本地gif图
        tvSix.setText("6.加载本地gif图");
        String path2 = getExternalCacheDir().getAbsolutePath() + "/haha.gif";
        File file2 = new File(path);
        Uri uri2 = Uri.fromFile(file);
        Glide.with(this).load(uri).into(imgSix);

        //7.加载本地视频
        tvSeven.setText("7.加载本地视频");
        String videoPath = getExternalCacheDir().getAbsolutePath() + "/text.mp4";
        File fileVideo = new File(videoPath);
        Uri uri1 = Uri.fromFile(fileVideo);
        Glide.with(this).load(uri1).placeholder(R.drawable.ic_launcher_background).into(imgSeven);

        //8.设置缩略图比例,然后,先加载缩略图,再加载原图
        tvEight.setText("8.设置缩略图比例");
        String path3 = getExternalCacheDir().getAbsolutePath() + "/hha.png";
        File file3 = new File(path3);
        //                                   缩略图是原来的0.1倍,居中,占位图
        Glide.with(this).load(file3).thumbnail(0.1f).centerCrop().placeholder(R.drawable.ic_launcher_background).into(imgEight);

        //9.先建立一个缩略图对象,然后,先加载缩略图,再加载原图
        tvNine.setText("9.先建立一个缩略图对象,然后,先加载缩略图,再加载原图");
        RequestBuilder<Drawable> builder = Glide.with(this).load(new File(path3));
        Glide.with(this).load(Uri.fromFile(fileVideo)).thumbnail(builder).centerCrop().placeholder(R.drawable.ic_launcher_background).into(imgNine);
    }
}
