package com.example.administrator.adcpt.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.example.administrator.adcpt.R;

import cn.albert.autosystembar.SystemBarHelper;

/**
 * Created by Administrator on 2019/5/8.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         /*
        沉浸式状态栏统一处理
         */
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//
//            getWindow().getDecorView().setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//            new SystemBarHelper.Builder()
//                    // 设置状态栏颜色
//                    .statusBarColor(ContextCompat.getColor(BaseActivity.this, R.color.colorPink))
//                    // 设置状态栏时间,电量的风格, 6.0以上, 部分国产机可以不用6.0以上.
//                    .statusBarFontStyle(2)
//                    // 根据状态栏下面的背景颜色自动调整状态栏的颜色, 自动调整状态栏时间,电量的风格, 默认是开启的
//                    .enableAutoSystemBar(true)
//                    .into(this);
//        }
        //去掉标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
