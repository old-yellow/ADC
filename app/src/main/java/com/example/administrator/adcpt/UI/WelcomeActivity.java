package com.example.administrator.adcpt.UI;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.administrator.adcpt.Base.BaseActivity;
import com.example.administrator.adcpt.R;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends Activity {

    private final long DELAY = 3000;
    private TimerTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        ImageView startImg = (ImageView)findViewById(R.id.start_img);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        startImg.startAnimation(animation);
        SharedPreferences pref = this.getSharedPreferences("StartFlag", MODE_PRIVATE);
        boolean isFirstStart = pref.getBoolean("isFirstStart", true);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isFirstStart", false);
        editor.commit();
        final Intent firstIntent = isFirstStart? new Intent(
                this, GuideActivity.class) :
                new Intent(this, MainActivity.class);
        Timer timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                startActivity(firstIntent);
                finish();
            }
        };
        timer.schedule(task, DELAY);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
