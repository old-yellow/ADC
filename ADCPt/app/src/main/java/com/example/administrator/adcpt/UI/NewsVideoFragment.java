package com.example.administrator.adcpt.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.adcpt.R;
import com.example.administrator.adcpt.adapter.NewsVideoAdapter;
import com.example.administrator.adcpt.entity.NewsVideoItem;
import com.example.administrator.adcpt.entity.Video;
import com.example.administrator.adcpt.entity.VideoCard;
import com.example.administrator.adcpt.entity.VideoItem;
import com.example.administrator.adcpt.internet.HttpManager;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsVideoFragment extends Fragment {

    private static final String TAG = "NewsVideoFragment";

    private XRecyclerView xRecyclerView;

    private List<VideoCard> mVideoCardList = new ArrayList<>();

    private NewsVideoAdapter mAdapter;

    private Boolean tag=true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_video, container, false);
        initVideos(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    //初始化列表
    private void initVideos(View view) {
        initVideoItem(view);
        xRecyclerView = (XRecyclerView) view.findViewById(R.id.news_video);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        GridLayoutManager manager = new GridLayoutManager(view.getContext(), 2);
        xRecyclerView.setLayoutManager(manager);
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallClipRotate);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新数据
                refreshNewsVideoList(view);
                tag=true;
                xRecyclerView.setLoadingMoreEnabled(true);
            }

            @Override
            public void onLoadMore() {
                if(tag) {
                    //加载更多
                    loadMoreNewsVideoList(view);
                    tag=false;
                    //结束加载动画
                    xRecyclerView.loadMoreComplete();
                    xRecyclerView.setLoadingMoreEnabled(false);
                }
            }
        });
    }

    private void refreshNewsVideoList(View view) {
        mVideoCardList.clear();
        //重新加载数据
        initVideoItem(view);
        mAdapter.notifyDataSetChanged();
        //结束刷新动画
        xRecyclerView.refreshComplete();
    }

    private void loadMoreNewsVideoList(View view) {
        //加载更多
        initVideoItem(view);
    }

    //初始化每条数据
    private void initVideoItem(View view) {


        //初始化数据
        HttpManager.getMethod("http://api.m.mtime.cn/PageSubArea/TrailerList.api/",
                "http://api.m.mtime.cn/PageSubArea/TrailerList.api", new Callback<Video>() {
                    @Override
                    public void onResponse(Call<Video> call, Response<Video> response) {
                        Video video = response.body();
                        Log.d(TAG, "size= " + video.getTrailers().size());
//                        for (int i = 0; i < video.getTrailers().size(); i += 2) {
//                            //初始化列表项
//                            String video1Cover = video.getTrailers().get(i).getCoverImg();
//                            String video1Title = video.getTrailers().get(i).getVideoTitle();
//                            String video2Cover = video.getTrailers().get(i + 1).getCoverImg();
//                            String video2Title = video.getTrailers().get(i + 1).getVideoTitle();
//                            VideoItem videoItem = new VideoItem(
//                                    video1Cover, video1Title, video2Cover, video2Title);
//                            videoItems.add(videoItem);
//                        }
                        for (int i = 0; i < video.getTrailers().size(); i++) {
                            VideoCard videoCard = new VideoCard(video.getTrailers().get(i).getCoverImg(),
                                    video.getTrailers().get(i).getVideoTitle(),
                                    video.getTrailers().get(i).getUrl());
                            mVideoCardList.add(videoCard);
                        }
                        mAdapter = new NewsVideoAdapter(mVideoCardList);
                        xRecyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onFailure(Call<Video> call, Throwable t) {
                        Toast.makeText(getContext(), "Wrong", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
