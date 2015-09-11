package com.retor.vklib.parsers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.retor.vklib.model.Group;
import com.retor.vklib.model.Profile;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by retor on 10.09.2015.
 */
public class ExtendedParser {
    public static Observable<List<Profile>> getProfiles(final String input) {
        return Observable.create(new Observable.OnSubscribe<List<Profile>>() {
            @Override
            public void call(final Subscriber<? super List<Profile>> subscriber) {
                List<Profile> out = new ArrayList<>();
                try {
                    Type listType = new TypeToken<List<Profile>>() {
                    }.getType();
                    out.addAll(new Gson().<Collection<? extends Profile>>fromJson(input, listType));
                } catch (Exception e) {
                    subscriber.onError(e);
                }
                subscriber.onNext(out);
                subscriber.onCompleted();
            }
        });


    }

    public static Observable<List<Group>> getGroups(final String input) {
        return Observable.create(new Observable.OnSubscribe<List<Group>>() {
            @Override
            public void call(final Subscriber<? super List<Group>> subscriber) {
                List<Group> out = new ArrayList<>();
                try {
                    Type listType = new TypeToken<List<Group>>() {
                    }.getType();
                    out.addAll(new Gson().<Collection<? extends Group>>fromJson(input, listType));
                } catch (Exception e) {
                    subscriber.onError(e);
                }
                subscriber.onNext(out);
                subscriber.onCompleted();
            }
        });
    }
}
