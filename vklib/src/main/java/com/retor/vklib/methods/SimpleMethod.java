package com.retor.vklib.methods;

import java.util.Map;

import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import retrofit.http.QueryMap;
import rx.Observable;

/**
 * Created by retor on 31.08.2015.
 */
public interface SimpleMethod {
    @GET("/method/{method_name}")
    @Headers({"Content-Type: application/json"})
    Observable<Response> getResponse(@Path("method_name") String method, @QueryMap Map<String, String> options);
}
