package com.example.administrator.adcpt.entity;

/**
 * Created by Administrator on 2019/5/21.
 */

public class VideoItem {

    private String video1Cover;

    private String video1Title;

    private String video2Cover;

    private String video2Title;

    public String getVideo1Cover() {
        return video1Cover;
    }

    public void setVideo1Cover(String video1Cover) {
        this.video1Cover = video1Cover;
    }

    public String getVideo1Title() {
        return video1Title;
    }

    public void setVideo1Title(String video1Title) {
        this.video1Title = video1Title;
    }

    public String getVideo2Cover() {
        return video2Cover;
    }

    public void setVideo2Cover(String video2Cover) {
        this.video2Cover = video2Cover;
    }

    public String getVideo2Title() {
        return video2Title;
    }

    public void setVideo2Title(String video2Title) {
        this.video2Title = video2Title;
    }

    public VideoItem(String video1Cover, String video1Title, String video2Cover, String video2Title) {
        this.video1Cover = video1Cover;
        this.video1Title = video1Title;
        this.video2Cover = video2Cover;
        this.video2Title = video2Title;
    }
}
