package com.example.administrator.adcpt.internet;

import com.example.administrator.adcpt.entity.Video;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2019/5/21.
 */

public class HttpManager {

    public static void getMethod(String baseUrl, String url, final Callback<Video> callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProjectData projectData = retrofit.create(ProjectData.class);
        Call<Video> call = projectData.getMethod(url);
        call.enqueue(new Callback<Video>() {
            @Override
            public void onResponse(Call<Video> call, Response<Video> response) {
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<Video> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }
}
