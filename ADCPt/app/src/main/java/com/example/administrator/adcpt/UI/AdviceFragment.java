package com.example.administrator.adcpt.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.adcpt.R;
import com.example.administrator.adcpt.entity.Video;
import com.example.administrator.adcpt.internet.HttpManager;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdviceFragment extends Fragment {

    private static final String TAG = "AdviceFragment";

    //轮播容器
    @BindView(R.id.banner)
    Banner banner;

    //轮播图片数组
    List<String> mImgUrls = new ArrayList<>();

    //轮播图片数量
    public static final int BANNER_NUM = 6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView:advice");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_advice, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    //加载数据
    private void initView() {

        //设置轮播图片
        initImgUrls();
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置轮播的动画效果，内含多种特效
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.RIGHT);
    }

    //初始化轮播图片数据
    private void initImgUrls() {

        HttpManager.getMethod("http://api.m.mtime.cn/PageSubArea/TrailerList.api/",
                "http://api.m.mtime.cn/PageSubArea/TrailerList.api", new Callback<Video>() {
                    @Override
                    public void onResponse(Call<Video> call, Response<Video> response) {
                        Log.d(TAG, "onResponse");
                        mImgUrls.clear();
                        Video video = response.body();
                        for (int i = 0; i < BANNER_NUM; i++) {
                            mImgUrls.add(video.getTrailers().get(i).getCoverImg());
                        }
                        //必须最后调用的方法，启动轮播图。
                        banner.setImages(mImgUrls).start();
                    }

                    @Override
                    public void onFailure(Call<Video> call, Throwable t) {
                        Toast.makeText(getContext(), "获取图片失败", Toast.LENGTH_SHORT).show();
                    }
                });
        Log.d(TAG, "size= " + mImgUrls.size());


    }

    //图片加载器
    public static class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }

}
