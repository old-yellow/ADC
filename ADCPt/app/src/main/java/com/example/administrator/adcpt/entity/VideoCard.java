package com.example.administrator.adcpt.entity;

import android.widget.ImageView;

/**
 * Created by Administrator on 2019/5/22.
 */
//用于列表展示所需数据
public class VideoCard {

    private String videoCover;

    private String videoTitle;

    private String videoUrl;

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }


    public VideoCard(String videoCover, String videoTitle, String videoUrl) {
        this.videoCover = videoCover;
        this.videoTitle = videoTitle;
        this.videoUrl = videoUrl;
    }

    public String getVideoCover() {
        return videoCover;
    }

    public void setVideoCover(String videoCover) {
        this.videoCover = videoCover;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }
}
