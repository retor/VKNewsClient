package com.retor.vklib.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.retor.vklib.methods.NewsMethods;
import com.retor.vklib.response.CommentsResponse;
import com.retor.vklib.response.NewsResponse;
import com.retor.vklib.services.ServiceCreator;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;

/**
 * Created by retor on 31.08.2015.
 */
public class VKNewsApi {

    private NewsMethods method;

    public VKNewsApi(ServiceCreator creator) {
        method = creator.createService(NewsMethods.class);
    }

    public Observable<NewsResponse> getNews(@Nullable String next_from) {
        return method.getNews(next_from, getNewsOptions());
    }

    public Observable<CommentsResponse> getComments(final String postId, String ownerId, String start_comment_id) {
        return method.getNewsComments(ownerId, postId, start_comment_id, getCommentsOptions());

    }

    @NonNull
    private Map<String, String> getCommentsOptions() {
        Map<String, String> commentsOptions = new HashMap<>();
        commentsOptions.put("need_likes", "1");
        commentsOptions.put("count", "15");
        commentsOptions.put("sort", "desc");
        commentsOptions.put("extended", "1");
        commentsOptions.put("v", "5.37");
        return commentsOptions;
    }

    @NonNull
    private Map<String, String> getNewsOptions() {
        Map<String, String> options = new HashMap<>();
        options.put("count", "30");
        options.put("filters", "post");
        options.put("fields", "photo_100,screen_name");
        options.put("v", "5.37");
        options.put("access_token", SDK.getKey().getToken());
        return options;
    }
}


