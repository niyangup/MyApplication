package com.example.myapplication.glide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;


class GlideRecyclerViewChangeAdapter extends RecyclerView.Adapter<GlideRecyclerViewChangeAdapter.MyChangeViewHolder> {
    private Context mContext;
    private List<String> mList;

    public GlideRecyclerViewChangeAdapter(Context context) {
        this.mContext = context;
        mList = new ArrayList<>();
        for (int i = 0; i <= 21; i++) {
            mList.add(" " + i);
        }
    }

    @NonNull
    @Override
    public MyChangeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext, R.layout.item_glide_change, null);
        MyChangeViewHolder holder = new MyChangeViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyChangeViewHolder holder, int position) {

        /*int integer = Integer.parseInt(mList.get(position).trim());

        switch (integer) {
            case 1: {
                int width = Utils.dip2px(mContext, 133.33f);
                int height = Utils.dip2px(mContext, 126.33f);
                Glide.with(mContext)
                        .load(R.drawable.check)
                        .override(width, height)
                        .apply(bitmapTransform(new MaskTransformation(mContext, R.drawable.mask_starfish)))
                        .into(holder.mIvChange);
                break;
            }
            case 2: {
                int width = Utils.dip2px(mContext, 150.0f);
                int height = Utils.dip2px(mContext, 100.0f);
                Glide.with(mContext)
                        .load(R.drawable.check)
                        .override(width, height)
                        .apply(bitmapTransform(new MaskTransformation(mContext, R.drawable.mask_chat_right)))
                        .into(holder.mIvChange);
                break;
            }
            case 3:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .apply(bitmapTransform(
                                new CropTransformation(mContext, 300, 100, CropTransformation.CropType.TOP)))
                        .into(holder.mIvChange);
                break;
            case 4:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .apply(bitmapTransform(new CropTransformation(mContext, 300, 100)))
                        .into(holder.mIvChange);
                break;
            case 5:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .apply(bitmapTransform(
                                new CropTransformation(mContext, 300, 100, CropTransformation.CropType.BOTTOM)))
                        .into(holder.mIvChange);

                break;
            case 6:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .apply(bitmapTransform(new CropSquareTransformation(mContext)))
                        .into(holder.mIvChange);
                break;
            case 7:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .apply(bitmapTransform(new CropCircleTransformation(mContext)))
                        .into(holder.mIvChange);
                break;
            case 8:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .apply(bitmapTransform(new ColorFilterTransformation(mContext, Color.argb(80, 255, 0, 0))))
                        .into(holder.mIvChange);
                break;
            case 9:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .apply(bitmapTransform(new GrayscaleTransformation(mContext)))
                        .into(holder.mIvChange);
                break;
            case 10:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .apply(bitmapTransform(new RoundedCornersTransformation(mContext, 30, 0,
                                RoundedCornersTransformation.CornerType.BOTTOM)))
                        .into(holder.mIvChange);
                break;
            case 11:
                Glide.with(mContext)
                        .load(R.drawable.check)
                        .apply(bitmapTransform(new BlurTransformation(mContext, 25)))
                        .into(holder.mIvChange);
                break;
            case 12:
                Glide.with(mContext)
                        .load(R.drawable.demo)
                        .apply(bitmapTransform(new ToonFilterTransformation(mContext)))
                        .into(holder.mIvChange);
                break;
            case 13:
                Glide.with(mContext)
                        .load(R.drawable.check)
                        .apply(bitmapTransform(new SepiaFilterTransformation(mContext)))
                        .into(holder.mIvChange);
                break;
            case 14:
                Glide.with(mContext)
                        .load(R.drawable.check)
                        .apply(bitmapTransform(new ContrastFilterTransformation(mContext, 2.0f)))
                        .into(holder.mIvChange);
                break;
            case 15:
                Glide.with(mContext)
                        .load(R.drawable.check)
                        .apply(bitmapTransform(new InvertFilterTransformation(mContext)))
                        .into(holder.mIvChange);
                break;
            case 16:
                Glide.with(mContext)
                        .load(R.drawable.check)
                        .apply(bitmapTransform(new PixelationFilterTransformation(mContext, 20)))
                        .into(holder.mIvChange);
                break;
            case 17:
                Glide.with(mContext)
                        .load(R.drawable.check)
                        .apply(bitmapTransform(new SketchFilterTransformation(mContext)))
                        .into(holder.mIvChange);
                break;
            case 18:
                Glide.with(mContext)
                        .load(R.drawable.check)
                        .apply(bitmapTransform(
                                new SwirlFilterTransformation(mContext, 0.5f, 1.0f, new PointF(0.5f, 0.5f))))
                        .into(holder.mIvChange);
                break;
            case 19:
                Glide.with(mContext)
                        .load(R.drawable.check)
                        .apply(bitmapTransform(new BrightnessFilterTransformation(mContext, 0.5f)))
                        .into(holder.mIvChange);
                break;
            case 20:
                Glide.with(mContext)
                        .load(R.drawable.check)
                        .apply(bitmapTransform(new KuwaharaFilterTransformation(mContext, 25)))
                        .into(holder.mIvChange);
                break;
            case 21:
                Glide.with(mContext)
                        .load(R.drawable.check)
                        .apply(bitmapTransform(new VignetteFilterTransformation(mContext, new PointF(0.5f, 0.5f),
                                new float[]{0.0f, 0.0f, 0.0f}, 0f, 0.75f)))
                        .into(holder.mIvChange);
                break;
        }*/
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class MyChangeViewHolder extends RecyclerView.ViewHolder {

        ImageView mIvChange;

        TextView mTvName;

        public MyChangeViewHolder(@NonNull View itemView) {
            super(itemView);
            mIvChange = itemView.findViewById(R.id.iv_glide_change);
            mTvName = itemView.findViewById(R.id.tv_glide_name);
        }
    }
}
