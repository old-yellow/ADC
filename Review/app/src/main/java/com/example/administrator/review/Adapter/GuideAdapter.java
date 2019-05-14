package com.example.administrator.review.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2019/5/10.
 */

public class GuideAdapter extends PagerAdapter{

    private List<View> mViewList;

    public GuideAdapter(List<View> viewList) {
        this.mViewList = viewList;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }
}
