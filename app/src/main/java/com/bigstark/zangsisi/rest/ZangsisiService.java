package com.bigstark.zangsisi.rest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by bigstark on 2016. 6. 20..
 */

public interface ZangsisiService {

    @GET("/")
    Call<ResponseBody> getHtml(@Query("page_id") String pageId);

    @GET("/")
    Call<ResponseBody> getContentsHtml(@Query("p") String pageId);
}
