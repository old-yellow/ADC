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


import com.example.administrator.adcpt.adapter.TabAdapter;
import com.example.administrator.adcpt.R;
import com.example.administrator.adcpt.util.TabUtils;

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

        TabAdapter tabAdapter = new TabAdapter(
                getChildFragmentManager(), fragments, titles);

        //适配
        homeTab.setupWithViewPager(homePager);
        homePager.setAdapter(tabAdapter);
        //设置下划线
        TabUtils.reflex(homeTab, getActivity(), 10);
    }

    private void initViewPager(View view) {

        //每个板块的内容
        homePager = (ViewPager) view.findViewById(R.id.home_pager);
        //初始化内容
        ShowFragment showFragment = new ShowFragment();
        AdviceFragment adviceFragment = new AdviceFragment();
        HotFragment hotFragment = new HotFragment();
        CartoonFragment cartoonFragment = new CartoonFragment();
        MovieFragment movieFragment = new MovieFragment();
        YearsFragment yearsFragment = new YearsFragment();

        //添加内容
        fragments = new ArrayList<>();
        fragments.add(showFragment);
        fragments.add(adviceFragment);
        fragments.add(hotFragment);
        fragments.add(cartoonFragment);
        fragments.add(movieFragment);
        fragments.add(yearsFragment);
    }



}
