package com.example.myapplication.glide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.okhttp.domain.DataBean;
import com.example.myapplication.recyclerview.RecyclerViewActivity;

import java.util.List;

import butterknife.BindView;


public class GlideRecyclerViewAdapter extends RecyclerView.Adapter<GlideRecyclerViewAdapter.GlideViewHolder> {
    private Context mContext;
    /*private String[] mList = {"http://img5.mtime.cn/mg/2019/06/29/002009.16684021_120X90X4.jpg",
            "http://img5.mtime.cn/mg/2019/06/27/231348.59732586_120X90X4.jpg",
            "http://img5.mtime.cn/mg/2019/06/27/224744.68512147_120X90X4.jpg",
            "http://img5.mtime.cn/mg/2019/06/27/225551.29349352_120X90X4.jpg",
            "http://img5.mtime.cn/mg/2019/06/27/104144.36321374_120X90X4.jpg",
            "http://img5.mtime.cn/mg/2019/06/27/104649.48931556_120X90X4.jpg"};*/
    private List<DataBean.ItemData> mList;

    public GlideRecyclerViewAdapter(Context context, List<DataBean.ItemData> data) {
        this.mContext = context;
        this.mList = data;
    }

    @NonNull
    @Override
    public GlideViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = View.inflate(mContext, R.layout.item_glide_recyclerview, null);
        GlideViewHolder holder = new GlideViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GlideViewHolder holder, int position) {
        DataBean.ItemData itemData = mList.get(position);
        String url = itemData.getCoverImg();
        Log.v("TAG", "适配器url" + url);
//        String url = mList[position];

        Glide.with(mContext)
                .load(url)//加载图片的url
                //.override(width,height)//设置图片分辨率,像素值,可以转化为dp再设置
                .placeholder(R.drawable.ic_launcher_background)//占位图
                .error(R.drawable.ic_launcher_foreground)//出错的占位图
                .centerCrop()
                .fitCenter()
                .into(holder.mIvGlide);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    interface OnItemClickLisener {
        void onItemClick(View view);
    }

    private OnItemClickLisener onItemClickListener;

    public void setOnItemClickListener(OnItemClickLisener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class GlideViewHolder extends RecyclerView.ViewHolder {

        ImageView mIvGlide;

        public GlideViewHolder(@NonNull View itemView) {
            super(itemView);
            mIvGlide = itemView.findViewById(R.id.iv_glide);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v);
                    }
                }
            });
        }
    }


}
