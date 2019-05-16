package com.example.administrator.adcpt.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.administrator.adcpt.R;
import com.example.administrator.adcpt.adapter.NewsVideoAdapter;
import com.example.administrator.adcpt.entity.NewsVideoItem;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;


public class NewsVideoFragment extends Fragment {

    private XRecyclerView xRecyclerView;

    private List<NewsVideoItem> newsVideos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_video, container, false);
        initNewsVideoItems(view);
        return view;
    }

    //初始化列表
    private void initNewsVideoItems(View view) {
        initNewsVideoItemList(view);
        xRecyclerView = (XRecyclerView)view.findViewById(R.id.news_video);
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(manager);
        xRecyclerView.setAdapter(new NewsVideoAdapter(newsVideos));
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallClipRotate);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新数据
                refreshNewsVideoList(view);
                //结束刷新动画
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                //加载更多
                loadMoreNewsVideoList(view);
                //结束加载动画
                xRecyclerView.loadMoreComplete();
            }
        });
    }

    private void refreshNewsVideoList(View view) {

    }

    private void loadMoreNewsVideoList(View view) {

    }

    //初始化每条数据
    private void initNewsVideoItemList(View view) {

        newsVideos = new ArrayList<>();

    }

}
