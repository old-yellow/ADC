package com.example.administrator.adcpt.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.adcpt.R;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends Activity {

    private final long DELAY = 3000;
    private TimerTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        ImageView startImg = (ImageView)findViewById(R.id.start_img);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        startImg.setLayoutParams(params);
        startImg.setBackgroundResource(R.mipmap.ic_splash_default);
        RelativeLayout welcomeLayout = (RelativeLayout)findViewById(R.id.welcome_layout);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        welcomeLayout.startAnimation(animation);
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

}
