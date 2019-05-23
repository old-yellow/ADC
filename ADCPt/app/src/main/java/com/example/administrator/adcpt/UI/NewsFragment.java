package com.example.administrator.adcpt.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.adcpt.R;
import com.example.administrator.adcpt.adapter.TabAdapter;
import com.example.administrator.adcpt.util.TabUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {

    //板块内容管理
    private List<Fragment> fragments;

    private ViewPager newsPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        init(view);
        return view;
    }

    private void init(View view) {

        TabLayout newsTab;

        //板块标题
        List<String> titles;

        newsTab = (TabLayout)view.findViewById(R.id.news_tab);

        initViewPager(view);

        //添加标题
        titles = new ArrayList<>();
        titles.add("视频");
        titles.add("综合");
        titles.add("热门");
        for (int i = 0; i < titles.size(); i++) {
            newsTab.addTab(newsTab.newTab().setText(titles.get(i)));
        }

        TabAdapter adapter = new TabAdapter(getChildFragmentManager(), fragments, titles);
        newsTab.setupWithViewPager(newsPager);
        newsPager.setAdapter(adapter);
        newsPager.setOffscreenPageLimit(2);
        //设置下划线适应文本
        TabUtils.reflex(newsTab, getActivity(), 30);
    }

    private void initViewPager(View view) {

        newsPager = (ViewPager)view.findViewById(R.id.news_pager);
        //初始化板块内容
        NewsVideoFragment newsVideoFragment = new NewsVideoFragment();
        NewsComplexFragment newsComplexFragment = new NewsComplexFragment();
        NewsHotFragment newsHotFragment = new NewsHotFragment();

        //添加内容
        fragments = new ArrayList<>();
        fragments.add(newsVideoFragment);
        fragments.add(newsComplexFragment);
        fragments.add(newsHotFragment);
    }

}
