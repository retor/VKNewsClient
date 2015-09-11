package com.retor.vklib.methods;

import com.retor.vklib.response.NewsResponse;

import java.util.Map;

import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.QueryMap;
import rx.Observable;

/**
 * Created by retor on 01.09.2015.
 */
public interface NewsMethods {
    @GET("/method/newsfeed.get")
    Observable<Response> getNews(@Query("start_from")String start_from, @QueryMap Map<String, String> options);
    @GET("/method/wall.getComments")
    Observable<Response> getNewsComments(@Query("owner_id")String owner_id, @Query("post_id")String post_id, @Query("start_comment_id")String start_id,  @QueryMap Map<String, String> options);
    @GET("/method/newsfeed.get")
    Observable<NewsResponse> getNewsResponse(@Query("start_from")String start_from, @QueryMap Map<String, String> options);
}
