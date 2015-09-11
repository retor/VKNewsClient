package com.retor.vklib.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.retor.vklib.exceptions.TokenExpiredException;
import com.retor.vklib.methods.NewsMethods;
import com.retor.vklib.model.Group;
import com.retor.vklib.model.NewsPost;
import com.retor.vklib.model.Profile;
import com.retor.vklib.model.VkComment;
import com.retor.vklib.parsers.CommentParser;
import com.retor.vklib.parsers.ExtendedParser;
import com.retor.vklib.parsers.NewsParser;
import com.retor.vklib.response.CommentsResponse;
import com.retor.vklib.response.NewsResponse;
import com.retor.vklib.services.ServiceCreator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.client.Response;
import retrofit.mime.TypedByteArray;
import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func3;

/**
 * Created by retor on 31.08.2015.
 */
public class VKNewsApi {

    private NewsMethods method;

    public VKNewsApi(ServiceCreator creator) {
        method = creator.createService(NewsMethods.class);
    }

    public Observable<NewsResponse> getNews(@Nullable String next_from) {
        return method.getNews(next_from, getNewsOptions())
                .flatMap(new Func1<Response, Observable<NewsResponse>>() {
                    @Override
                    public Observable<NewsResponse> call(final Response response) {
                        String s = new String(((TypedByteArray) response.getBody()).getBytes());
                        if (isError(s)) {
                            SDK.removeKey();
                            return Observable.error(new TokenExpiredException("Token expired"));
                        } else {
                            JsonParser parser = new JsonParser();
                            final JsonObject mainObject = parser.parse(s).getAsJsonObject().getAsJsonObject("response");
                            return getNewsResponseCombiner(mainObject);
                        }
                    }
                });
    }

    public Observable<CommentsResponse> getComments(final String postId, String ownerId, String start_comment_id) {
        return method.getNewsComments(ownerId, postId, start_comment_id, getCommentsOptions())
                .flatMap(new Func1<Response, Observable<CommentsResponse>>() {
                    @Override
                    public Observable<CommentsResponse> call(Response response) {
                        String s = new String(((TypedByteArray) response.getBody()).getBytes());
                        if (isError(s)) {
                            SDK.removeKey();
                            return Observable.error(new TokenExpiredException("Token expired"));
                        } else {
                            JsonParser parser = new JsonParser();
                            final JsonObject mainObject = parser.parse(s).getAsJsonObject().getAsJsonObject("response");
                            return getCommentResponseCombiner(mainObject, postId);
                        }
                    }
                });
    }

    @NonNull
    private Observable<NewsResponse> getNewsResponseCombiner(final JsonObject mainObject) {
        return Observable.combineLatest(NewsParser.getPosts(mainObject.get("items").getAsJsonArray().toString()),
                ExtendedParser.getGroups(mainObject.get("groups").getAsJsonArray().toString()),
                ExtendedParser.getProfiles(mainObject.get("profiles").getAsJsonArray().toString()), new Func3<List<NewsPost>, List<Group>, List<Profile>, NewsResponse>() {
                    @Override
                    public NewsResponse call(final List<NewsPost> list, final List<Group> groups, final List<Profile> profiles) {
                        NewsResponse out = new NewsResponse();
                        out.setItems(list);
                        out.setGroups(groups);
                        out.setProfiles(profiles);
                        out.setNext_from(mainObject.get("next_from").getAsString());
                        return out;
                    }
                });
    }

    private Observable<CommentsResponse> getCommentResponseCombiner(final JsonObject mainObject, final String post) {
        return Observable.combineLatest(CommentParser.getComments(mainObject.get("items").getAsJsonArray().toString()),
                ExtendedParser.getGroups(mainObject.get("groups").getAsJsonArray().toString()),
                ExtendedParser.getProfiles(mainObject.get("profiles").getAsJsonArray().toString()),
                new Func3<List<VkComment>, List<Group>, List<Profile>, CommentsResponse>() {
                    @Override
                    public CommentsResponse call(final List<VkComment> list, final List<Group> groups, final List<Profile> profiles) {
                        CommentsResponse out = new CommentsResponse();
                        out.setItems(list);
                        out.setGroups(groups);
                        out.setProfiles(profiles);
                        if (!list.isEmpty())
                            out.setNext_from(String.valueOf(list.get(list.size() - 1).getId()));
                        out.setPost_id(Integer.parseInt(post));
                        return out;
                    }
                });
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

    private boolean isError(final String s) {
        return s.contains("error") && s.contains("access_token has expired");
    }
}


