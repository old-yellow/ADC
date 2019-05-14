package com.example.administrator.adcpt.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.administrator.adcpt.adapter.HomeTabAdapter;
import com.example.administrator.adcpt.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    //板块内容管理
    private List<Fragment> fragments;

    private ViewPager homePager;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);
        // Inflate the layout for this fragment
        return view;
    }

    private void init(View view) {

        TabLayout homeTab;

        //每个板块的标题
        List<String> titles;


        homeTab = (TabLayout) view.findViewById(R.id.home_tab);

        initViewPager(view);


        //添加标题
        titles = new ArrayList<>();
        titles.add("直播");
        titles.add("推荐");
        titles.add("热门");
        titles.add("追番");
        titles.add("影视");
        titles.add("70年");
        for (int i = 0; i < titles.size(); i++) {
            homeTab.addTab(homeTab.newTab().setText(titles.get(i)));
        }

        HomeTabAdapter homeTabAdapter = new HomeTabAdapter(
                getChildFragmentManager(), fragments, titles);

        //适配
        homeTab.setupWithViewPager(homePager);
        homePager.setAdapter(homeTabAdapter);
        //设置下划线
        reflex(homeTab);
    }

    private void initViewPager(View view) {

        //每个板块的内容
        homePager = (ViewPager) view.findViewById(R.id.home_pager);
        //初始化内容
        ShowFragment showFragment = new ShowFragment();
        AdviceFragment adviceFragment = new AdviceFragment();
        HotFragment hotFragment = new HotFragment();
        CartoonFragment cartoonFragment = new CartoonFragment();
        MovieFragment moviewFragment = new MovieFragment();
        YearsFragment yearsFragment = new YearsFragment();

        //添加内容
        fragments = new ArrayList<>();
        fragments.add(showFragment);
        fragments.add(adviceFragment);
        fragments.add(hotFragment);
        fragments.add(cartoonFragment);
        fragments.add(moviewFragment);
        fragments.add(yearsFragment);
    }

    //设置下划线宽度
    public void reflex(final TabLayout tabLayout) {
        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);

                    int dp10 = dip2px(10);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width;
                        params.leftMargin = dp10;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public int dip2px(float dpValue) {
        final float scale = getActivity().getApplication().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
