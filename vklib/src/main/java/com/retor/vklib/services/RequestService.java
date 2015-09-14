package com.retor.vklib.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.retor.vklib.model.Attachments;
import com.retor.vklib.model.attachments.AttachModel;
import com.retor.vklib.parsers.AttachParser;
import com.retor.vklib.parsers.TypedAdapterResponse;
import com.squareup.okhttp.ConnectionPool;
import com.squareup.okhttp.OkHttpClient;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by retor on 31.08.2015.
 */
public class RequestService implements ServiceCreator {
    @Override
    public <S> S createService(Class<S> serviceClass) {
        Gson gson = new GsonBuilder().registerTypeAdapter(AttachModel.class, new AttachParser()).create();
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint("https://api.vk.com")
                .setClient(new OkClient(createHttpClient()))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(getGson()))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(final RequestFacade request) {
                        request.addHeader("Content-Type", "application/json; charset=utf-8");
                    }
                });
        RestAdapter adapter = builder.build();
        return adapter.create(serviceClass);
    }


    private OkHttpClient createHttpClient(){
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(3000, TimeUnit.MILLISECONDS);
        client.setConnectionPool(new ConnectionPool(3, 3000));
        client.setReadTimeout(2000, TimeUnit.MILLISECONDS);
        client.setFollowSslRedirects(true);
        client.setFollowRedirects(true);
        client.setRetryOnConnectionFailure(true);
        return client;
    }

    private Gson getGson(){
        Type attachType = new TypeToken<Attachments<AttachModel>>() {
        }.getType();
        return new GsonBuilder()
                .registerTypeAdapterFactory(new TypedAdapterResponse())
                .registerTypeAdapter(attachType, new AttachParser())
                .create();
    }
}
