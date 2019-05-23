package com.example.administrator.adcpt.internet;

import com.example.administrator.adcpt.entity.Video;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2019/5/21.
 */

public interface ProjectData {

    @GET
    Call<Video> getMethod(@Url String url);

    @POST
    Call<String> postMethod(@Url String url, @FieldMap Map<String, String> map);
}
