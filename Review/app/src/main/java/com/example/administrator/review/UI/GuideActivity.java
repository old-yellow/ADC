package com.example.administrator.review.UI;

import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.review.Adapter.GuideAdapter;
import com.example.administrator.review.R;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity {

    private ViewPager viewPager;

    private ViewGroup pointGroup;

    private ImageView point;

    private ImageView[] points;

    private List<View> viewList;

    private int[] mImgIds;

    private ImageButton guideEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        guideEnd = (ImageButton)findViewById(R.id.guide_end);
        guideEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        initImgs();
        initPoints();
    }

    private void initImgs() {
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        mImgIds = new int[] {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4};
        viewList = new ArrayList<>();
        for (int i = 0; i < mImgIds.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(mImgIds[i]);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(params);
            viewList.add(imageView);
        }
        viewPager.setAdapter(new GuideAdapter(viewList));
    }

    private void initPoints() {
        pointGroup = (ViewGroup)findViewById(R.id.point_view);
        points = new ImageView[mImgIds.length];
        for (int i = 0; i < points.length; i++) {
            point = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(16, 16);
            point.setLayoutParams(params);
            if (i == 0) {
                point.setBackgroundResource(R.drawable.full_hole);
            }
        }

    }
}
