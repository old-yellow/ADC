package com.example.administrator.adcpt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity {

    private final long DELAY = 3000;
    private TimerTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView startImg = (ImageView)findViewById(R.id.start_img);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        startImg.startAnimation(animation);
        SharedPreferences pref = this.getSharedPreferences("StartFlag", MODE_PRIVATE);
        boolean isFirstStart = pref.getBoolean("isFirstStart", true);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isFirstStart", false);
        editor.commit();
        if (isFirstStart) {
            final Intent firstIntent = new Intent(this, GuideActivity.class);
            Timer timer = new Timer();
            task = new TimerTask() {
                @Override
                public void run() {
                    startActivity(firstIntent);
                }
            };
            timer.schedule(task, DELAY);
        } else {
            final Intent firstIntent = new Intent(this, UseActivity.class);
            Timer timer = new Timer();
            task = new TimerTask() {
                @Override
                public void run() {
                    startActivity(firstIntent);
                }
            };
            timer.schedule(task, DELAY);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.finishAll();
    }
}
