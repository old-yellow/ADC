package com.example.administrator.adcpt;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener{

    private int[] mImgIds;
    private ViewPager mViewPager;
    //图片资源的集合
    private List<View> viewList;
    //放置圆点
    private ViewGroup vg;
    private ImageView ivPoint;
    private ImageView[] ivPointArray;

    private ImageButton guideEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = GuideActivity.this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_guide);
        guideEnd = (ImageButton)findViewById(R.id.guide_end);
        guideEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity.this, UseActivity.class));
                //GuideActivity.this.finish();
            }
        });

        //加载ViewPager
        initViewPager();
        //加载圆点
        initPoint();
    }

    private void initViewPager() {
        mViewPager = (ViewPager)findViewById(R.id.view_pager);
        //实例化图片
        mImgIds = new int[] {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4};
        viewList = new ArrayList<>();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        //放入viewList
        int len = mImgIds.length;
        for (int i = 0; i < len; i++) {
            ImageView guideImg = new ImageView(this);
            guideImg.setLayoutParams(params);
            guideImg.setBackgroundResource(mImgIds[i]);
            viewList.add(guideImg);
        }
        //viewList初始化完成后，设置adapter
        mViewPager.setAdapter(new GuideAdapter(viewList));
        //设置滑动监听
        mViewPager.addOnPageChangeListener(this);
    }

    private void initPoint() {
        vg = (ViewGroup)findViewById(R.id.guide_point);
        ivPointArray = new ImageView[mImgIds.length];
        //循环新建底部圆点
        for (int i = 0; i < mImgIds.length; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(16, 16);
            params.setMargins(0, 0, 15, 0);
            ivPoint = new ImageView(GuideActivity.this);
            ivPoint.setLayoutParams(params);
            ivPointArray[i] = ivPoint;
            //选中的页面对应的点设为实点，否则为空点
            if (i == 0) {
                ivPoint.setBackgroundResource(R.drawable.full_hole);
            } else {
                ivPoint.setBackgroundResource(R.drawable.empty_hole);
            }
            vg.addView(ivPoint);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //滑动监听回调
    }

    @Override
    public void onPageSelected(int position) {
        int len = mImgIds.length;
        for (int i = 0; i < len; i++) {
            ivPointArray[position].setBackgroundResource(R.drawable.full_hole);
            if (position != i) {
                ivPointArray[i].setBackgroundResource(R.drawable.empty_hole);
            }
        }
        //如果是最后一页则显示结束按钮
        if (position == len - 1) {
            guideEnd.setVisibility(View.VISIBLE);
        } else {
            guideEnd.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //滑动状态监听（实现动画）
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.finishAll();
    }
}
