package com.retor.vklib.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.retor.vklib.model.attachments.AttachModel;
import com.retor.vklib.parsers.AttachParser;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by retor on 31.08.2015.
 */
public class RequestService implements ServiceCreator {
    @Override
    public <S> S createService(Class<S> serviceClass) {
        Gson gson = new GsonBuilder().registerTypeAdapter(AttachModel.class, new AttachParser()).create();
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint("https://api.vk.com")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(new OkHttpClient()))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(final RequestFacade request) {
                        request.addHeader("Content-Type", "application/json; charset=utf-8");
                    }
                });
        RestAdapter adapter = builder.build();
        return adapter.create(serviceClass);
    }
}
