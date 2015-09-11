package com.retor.vklib.parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.retor.vklib.model.Attachments;
import com.retor.vklib.model.NewsPost;
import com.retor.vklib.model.VkComment;
import com.retor.vklib.model.attachments.AttachModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by retor on 10.09.2015.
 */
public class CommentParser {
    public static Observable<List<VkComment>> getComments(final String items) {
        return Observable.create(new Observable.OnSubscribe<List<VkComment>>() {
            @Override
            public void call(final Subscriber<? super List<VkComment>> subscriber) {
                List<VkComment> out = new ArrayList<VkComment>();
                try {
                    Type attachType = new TypeToken<Attachments<AttachModel>>() {
                    }.getType();
                    Type listType = new TypeToken<List<VkComment>>() {
                    }.getType();
                    Gson gson = new GsonBuilder().registerTypeAdapter(attachType, new AttachParser()).create();
                    out.addAll(gson.<Collection<? extends VkComment>>fromJson(items, listType));
                } catch (Exception e) {
                    subscriber.onError(e);
                }
                subscriber.onNext(out);
                subscriber.onCompleted();
            }
        });
    }
}
