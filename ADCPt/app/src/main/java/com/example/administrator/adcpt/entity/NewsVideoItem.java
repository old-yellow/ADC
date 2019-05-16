package com.example.administrator.adcpt.entity;

/**
 * Created by Administrator on 2019/5/16.
 */

public class NewsVideoItem {

    //up主名字
    private String upName;
    //时间
    private String uploadTime;
    //分类标签
    private String label;
    //封面
    private int coverId;
    //视频标题
    private String title;
    //转发数
    private int reprintNum;
    //评论数
    private int commentNum;
    //点赞数
    private int likeNum;
    //up主头像
    private int avatarId;
    //视频时长
    private String duration;
    //播放量
    private int playAmount;
    //弹幕数量
    private int bulletScreen;

    public NewsVideoItem(String upName, String uploadTime, String label, int coverId, String title,
                         int reprintNum, int commentNum, int likeNum, int avatarId, String duration,
                         int playAmount, int bulletScreen) {
        this.upName = upName;
        this.uploadTime = uploadTime;
        this.label = label;
        this.coverId = coverId;
        this.title = title;
        this.reprintNum = reprintNum;
        this.commentNum = commentNum;
        this.likeNum = likeNum;
        this.avatarId = avatarId;
        this.duration = duration;
        this.playAmount = playAmount;
        this.bulletScreen = bulletScreen;
    }

    public String getDuration() {
        return duration;
    }

    public int getPlayAmount() {
        return playAmount;
    }

    public int getBulletScreen() {
        return bulletScreen;
    }

    public String getUpName() {
        return upName;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public String getLabel() {
        return label;
    }

    public int getCoverId() {
        return coverId;
    }

    public String getTitle() {
        return title;
    }

    public int getReprintNum() {
        return reprintNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public int getAvatarId() {
        return avatarId;
    }

}
