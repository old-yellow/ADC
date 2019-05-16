package com.example.administrator.adcpt.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.adcpt.R;
import com.example.administrator.adcpt.entity.NewsVideoItem;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by Administrator on 2019/5/16.
 */

public class NewsVideoAdapter extends XRecyclerView.Adapter<NewsVideoAdapter.ViewHolder>{

    List<NewsVideoItem> mNewsVideoItemList;

    public NewsVideoAdapter(List<NewsVideoItem> newsVideoItemList) {
        mNewsVideoItemList = newsVideoItemList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView avatarImg;

        TextView upNameText;

        TextView uploadTimeText;

        TextView labelText;

        ImageView coverImg;

        TextView titleText;

        TextView reprintNumText;

        TextView commentNumText;

        TextView likeNumText;

        TextView durationText;

        TextView playAmountText;

        TextView bulletScreenText;

        public ViewHolder(View view) {
            super(view);
            avatarImg = (ImageView)view.findViewById(R.id.avatar_img);
            upNameText = (TextView)view.findViewById(R.id.up_name);
            uploadTimeText = (TextView)view.findViewById(R.id.upload_time);
            labelText = (TextView)view.findViewById(R.id.label_text);
            coverImg = (ImageView)view.findViewById(R.id.cover_img);
            titleText = (TextView)view.findViewById(R.id.title_text);
            reprintNumText = (TextView)view.findViewById(R.id.reprint_num);
            commentNumText = (TextView)view.findViewById(R.id.comment_num);
            likeNumText = (TextView)view.findViewById(R.id.like_num);
            durationText = (TextView)view.findViewById(R.id.duration);
            playAmountText = (TextView)view.findViewById(R.id.paly_amount);
            bulletScreenText = (TextView)view.findViewById(R.id.bullet_screen);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_video_item_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsVideoItem newsVideoItem = mNewsVideoItemList.get(position);
        holder.avatarImg.setImageResource(newsVideoItem.getAvatarId());
        holder.upNameText.setText(newsVideoItem.getUpName());
        holder.uploadTimeText.setText(newsVideoItem.getUploadTime());
        holder.labelText.setText(newsVideoItem.getLabel());
        holder.coverImg.setImageResource(newsVideoItem.getCoverId());
        holder.titleText.setText(newsVideoItem.getTitle());
        holder.reprintNumText.setText(newsVideoItem.getReprintNum());
        holder.commentNumText.setText(newsVideoItem.getCommentNum());
        holder.likeNumText.setText(newsVideoItem.getLikeNum());
        holder.durationText.setText(newsVideoItem.getDuration());
        holder.playAmountText.setText(newsVideoItem.getPlayAmount() + "观看");
        holder.bulletScreenText.setText(newsVideoItem.getBulletScreen() + "弹幕");
    }

    @Override
    public int getItemCount() {
        return mNewsVideoItemList.size();
    }
}
