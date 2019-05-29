package com.example.administrator.adcpt.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.adcpt.R;
import com.example.administrator.adcpt.entity.NewsVideoItem;
import com.example.administrator.adcpt.entity.Video;
import com.example.administrator.adcpt.entity.VideoCard;
import com.example.administrator.adcpt.entity.VideoItem;
import com.example.administrator.adcpt.ui.VideoActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Administrator on 2019/5/16.
 */

public class NewsVideoAdapter extends XRecyclerView.Adapter<NewsVideoAdapter.ViewHolder> {

    //    List<NewsVideoItem> mNewsVideoItemList;
//
//    public NewsVideoAdapter(List<NewsVideoItem> newsVideoItemList) {
//        mNewsVideoItemList = newsVideoItemList;
//    }
//
//    static class ViewHolder extends RecyclerView.ViewHolder {
//
//        ImageView avatarImg;
//
//        TextView upNameText;
//
//        TextView uploadTimeText;
//
//        TextView labelText;
//
//        ImageView coverImg;
//
//        TextView titleText;
//
//        TextView reprintNumText;
//
//        TextView commentNumText;
//
//        TextView likeNumText;
//
//        TextView durationText;
//
//        TextView playAmountText;
//
//        TextView bulletScreenText;
//
//        public ViewHolder(View view) {
//            super(view);
//            avatarImg = (ImageView)view.findViewById(R.id.avatar_img);
//            upNameText = (TextView)view.findViewById(R.id.up_name);
//            uploadTimeText = (TextView)view.findViewById(R.id.upload_time);
//            labelText = (TextView)view.findViewById(R.id.label_text);
//            coverImg = (ImageView)view.findViewById(R.id.cover_img);
//            titleText = (TextView)view.findViewById(R.id.title_text);
//            reprintNumText = (TextView)view.findViewById(R.id.reprint_num);
//            commentNumText = (TextView)view.findViewById(R.id.comment_num);
//            likeNumText = (TextView)view.findViewById(R.id.like_num);
//            durationText = (TextView)view.findViewById(R.id.duration);
//            playAmountText = (TextView)view.findViewById(R.id.paly_amount);
//            bulletScreenText = (TextView)view.findViewById(R.id.bullet_screen);
//        }
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.news_video_item_layout, parent, false);
//        ViewHolder holder = new ViewHolder(view);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        NewsVideoItem newsVideoItem = mNewsVideoItemList.get(position);
//        holder.avatarImg.setImageResource(newsVideoItem.getAvatarId());
//        holder.upNameText.setText(newsVideoItem.getUpName());
//        holder.uploadTimeText.setText(newsVideoItem.getUploadTime());
//        holder.labelText.setText(newsVideoItem.getLabel());
//        holder.coverImg.setImageResource(newsVideoItem.getCoverId());
//        holder.titleText.setText(newsVideoItem.getTitle());
//        holder.reprintNumText.setText(newsVideoItem.getReprintNum());
//        holder.commentNumText.setText(newsVideoItem.getCommentNum());
//        holder.likeNumText.setText(newsVideoItem.getLikeNum());
//        holder.durationText.setText(newsVideoItem.getDuration());
//        holder.playAmountText.setText(newsVideoItem.getPlayAmount() + "观看");
//        holder.bulletScreenText.setText(newsVideoItem.getBulletScreen() + "弹幕");
//    }
//
//    @Override
//    public int getItemCount() {
//        return mNewsVideoItemList.size();
//    }
    private static final String TAG = "NewsVideoAdapter";

    //上下文
    public Context mContext;
    //数据列表
    public List<VideoCard> mVideoCardList;

    public NewsVideoAdapter(List<VideoCard> videoCardList) {
        mVideoCardList = videoCardList;
    }

    static class ViewHolder extends XRecyclerView.ViewHolder {

        @BindView(R.id.video_cover)
        public ImageView videoCover;
        @BindView(R.id.video_title)
        public TextView videoTitle;

        public CardView cardView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            cardView = (CardView) view;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_item_layout, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "VideoStart");
                int position = holder.getAdapterPosition();
                VideoCard videoCard = mVideoCardList.get(position);
                Intent intent = new Intent(mContext, VideoActivity.class);
                intent.putExtra(VideoActivity.VIDEO_URL, videoCard.getVideoUrl());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(mContext).load(mVideoCardList.get(position).getVideoCover())
                .bitmapTransform(new RoundedCornersTransformation(mContext, 5, 0))
                .into(holder.videoCover);
        holder.videoTitle.setText(mVideoCardList.get(position).getVideoTitle());
    }

    @Override
    public int getItemCount() {
        return mVideoCardList.size();
    }
}
