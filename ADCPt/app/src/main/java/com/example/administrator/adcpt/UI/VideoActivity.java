package com.example.administrator.adcpt.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.adcpt.R;
import com.example.administrator.adcpt.base.BaseActivity;
import com.example.administrator.adcpt.util.TabUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.provider.MediaStore;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class VideoActivity extends BaseActivity {

    private static final String TAG = "VideoActivity";

    public static final String VIDEO_URL = "video_url";

    @BindView(R.id.video_view)
    VideoView mVideoView;

    @BindView(R.id.video_thumbnail)
    ImageView mThumbnail;

    @BindView(R.id.video_group)
    FrameLayout mVideoGroup;

    @BindView(R.id.video_start)
    ImageView mVideoStart;

    private MediaController mController;

    private boolean isFullScreen;

    //播放路径
    private String PATH;

    @OnClick(R.id.video_start)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.video_start :
                mVideoStart.setVisibility(View.GONE);
                mThumbnail.setVisibility(View.GONE);
                mVideoView.setVideoPath(PATH);
                mVideoView.setMediaController(mController);
                mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        //开始播放
                        mVideoView.start();
                        mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
                    }
                });
                mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        //停止播放
                        mVideoView.stopPlayback();
                        mVideoStart.setVisibility(View.VISIBLE);
                    }
                });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (io.vov.vitamio.LibsChecker.checkVitamioLibs(this)) {
            return;
        }
        setContentView(R.layout.activity_video);
        Log.d(TAG, "VideoPlay");
        ButterKnife.bind(this);
        //加载视频
        init();
    }

    private void init() {
        Intent intent = getIntent();
        PATH = intent.getStringExtra(VIDEO_URL);
        mController = new MediaController(this);
        mController.setVisibility(View.GONE);
        setData();
    }

    private void setData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap videoThumbnail = ThumbnailUtils.createVideoThumbnail(
                        PATH, MediaStore.Video.Thumbnails.MINI_KIND);
                if (videoThumbnail != null) {
                    mThumbnail.post(new Runnable() {
                        @Override
                        public void run() {
                            mThumbnail.setImageBitmap(videoThumbnail);
                        }
                    });
                }
            }
        }).start();
    }

    //横屏全屏播放

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            isFullScreen = true;
            //去掉状态栏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            mVideoGroup.setLayoutParams(params);
            mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
        } else {
            isFullScreen = false;
            //清除flag，恢复状态栏
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, TabUtils.dip2px(220, this));
            mVideoGroup.setLayoutParams(params);
        }
    }

    //返回键切换小屏

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && isFullScreen) {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//            mController.setFullScreenIconState(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoView != null) {
            //清除缓存
            mVideoView.destroyDrawingCache();
            //停止播放
            mVideoView.stopPlayback();
            mVideoView = null;
        }
    }
}
