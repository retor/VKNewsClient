package com.retor.vknewsclient.interactor;

import com.retor.vknewsclient.db.model.NewsWithAttachments;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by retor on 28.08.2015.
 */
public interface InteractorNews {
    Subscription getNews(Subscriber<List<NewsWithAttachments>> subscriber);
    Subscription getNewsNext(Subscriber<List<NewsWithAttachments>> subscriber);
}
