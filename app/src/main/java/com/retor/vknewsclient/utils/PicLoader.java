package com.retor.vknewsclient.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by retor on 09.09.2015.
 */
public class PicLoader {
    private Map<String, ImageView> queryMap = new HashMap<String, ImageView>();
    private Picasso picasso;

    @Inject
    public PicLoader(Context context) {
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttpDownloader(context,Integer.MAX_VALUE));
        this.picasso = builder.build();
        picasso.setIndicatorsEnabled(true);
        picasso.setLoggingEnabled(true);
        Picasso.setSingletonInstance(picasso);
    }

    public void loadPic(String url, ImageView into){
        picasso.load(Uri.parse(url)).into(into);
/*        queryMap.put(url,into);
        work();*/
    }

    private void work(){
        Observable.from(queryMap.entrySet()).doOnEach(new Observer<Map.Entry<String, ImageView>>() {
            @Override
            public void onCompleted() {
                queryMap.clear();
            }

            @Override
            public void onError(final Throwable e) {

            }

            @Override
            public void onNext(final Map.Entry<String, ImageView> stringImageViewEntry) {

            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }
}
