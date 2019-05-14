package com.example.administrator.review.UI;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.review.R;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //加载引导图片
        ImageView welcomeImg = (ImageView)findViewById(R.id.welcome_img);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        welcomeImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //设置动画效果
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        welcomeImg.setAnimation(animation);
        //设置跳转标记
        SharedPreferences pref = this.getPreferences(MODE_PRIVATE);
        boolean isFirstStart = pref.getBoolean("isFirstStart", true);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isFirstStart", false);
        editor.commit();
        //设置延迟启动
                final Intent firstIntent = isFirstStart ? new Intent(this, GuideActivity.class) :
                new Intent(this, MainActivity.class);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(firstIntent);
                finish();
            }
        };
        final long delay = 3000;
        Timer timer = new Timer();
        timer.schedule(task, delay);
    }
}
