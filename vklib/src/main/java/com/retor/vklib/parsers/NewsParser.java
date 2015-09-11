package com.retor.vklib.parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.retor.vklib.model.Attachments;
import com.retor.vklib.model.Group;
import com.retor.vklib.model.NewsPost;
import com.retor.vklib.model.Profile;
import com.retor.vklib.model.attachments.AttachModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by retor on 01.09.2015.
 */
public class NewsParser {
    public static Observable<List<NewsPost>> getPosts(final String input) {
        return Observable.create(new Observable.OnSubscribe<List<NewsPost>>() {
            @Override
            public void call(final Subscriber<? super List<NewsPost>> subscriber) {
                List<NewsPost> out = new ArrayList<NewsPost>();
                try {
                    Type attachType = new TypeToken<Attachments<AttachModel>>() {
                    }.getType();
                    Type listType = new TypeToken<List<NewsPost>>() {
                    }.getType();
                    Gson gson = new GsonBuilder().registerTypeAdapter(attachType, new AttachParser()).create();
                    out.addAll(gson.<Collection<? extends NewsPost>>fromJson(input, listType));
                } catch (Exception e) {
                    subscriber.onError(e);
                }
                subscriber.onNext(out);
                subscriber.onCompleted();
            }
        });
    }
}
