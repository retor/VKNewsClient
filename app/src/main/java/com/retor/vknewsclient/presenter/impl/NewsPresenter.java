package com.retor.vknewsclient.presenter.impl;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import com.pushtorefresh.storio.sqlite.operations.put.PutResults;
import com.retor.vknewsclient.application.BaseApplication;
import com.retor.vknewsclient.db.RawQueryes;
import com.retor.vknewsclient.db.interactor.InteractorDB;
import com.retor.vknewsclient.db.model.NewsWithAttachments;
import com.retor.vknewsclient.di.modules.DaggerPresentersComponent;
import com.retor.vknewsclient.di.modules.InteractorsModule;
import com.retor.vknewsclient.di.modules.PresenterModules;
import com.retor.vknewsclient.interactor.InteractorNews;
import com.retor.vknewsclient.presenter.ListPresenter;
import com.retor.vknewsclient.ui.NewsDetailsDialog;
import com.retor.vknewsclient.utils.CheckUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import icepick.Icepick;
import icepick.State;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by retor on 28.08.2015.
 */
public class NewsPresenter extends ListPresenter<List<NewsWithAttachments>> {

    @Inject
    protected InteractorNews interactorNews;
    @Inject
    protected InteractorDB<NewsWithAttachments> dbInteractor;
    @Inject
    protected CheckUtils checkUtils;
    @State
    List<NewsWithAttachments> posts;
    private final String ACTION_TAG = "action";
    private Subscription subscription;

    @Inject
    public NewsPresenter(BaseApplication application) {
        DaggerPresentersComponent.builder()
                .applicationComponent(application.getComponent())
                .interactorsModule(new InteractorsModule())
                .presenterModules(new PresenterModules())
                .build().inject(this);
        posts = new ArrayList<NewsWithAttachments>();
    }

    @Override
    public void onCreate(final Fragment activity, final Bundle savedState) {
        Icepick.restoreInstanceState(this, savedState);
        if (posts != null && !posts.isEmpty())
            getView().setData(posts);
        else
            onRefresh();
    }

    @Override
    public void onResume(final Fragment activity) {

    }

    @Override
    public void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }

    @Override
    public void onSaveState(final Bundle outState) {
        outState.putBoolean(ACTION_TAG, isInAction());
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public void onRestoreState(final Bundle savedState) {
        if (savedState != null && !savedState.isEmpty()) {
            setInAction(savedState.getBoolean(ACTION_TAG));
        }
        Icepick.restoreInstanceState(this, savedState);
        if (posts != null && !posts.isEmpty())
            getView().setData(posts);
    }

    @Override
    public void onClick(final int item_position) {
        Bundle arg = new Bundle();
        arg.putParcelable("news", posts.get(item_position));
        DialogFragment dialogFragment = new NewsDetailsDialog();
        dialogFragment.setArguments(arg);
        getView().showDetailDialog(dialogFragment);
    }

    @Override
    public void onEndScrolled() {
        Subscriber subscriber = new Subscriber<List<NewsWithAttachments>>() {
            @Override
            public void onCompleted() {
                setInAction(false);
                getView().closeProrgess();
            }

            @Override
            public void onError(Throwable e) {
                setInAction(false);
                getView().closeProrgess();
                getView().onError(e);
            }

            @Override
            public void onNext(List<NewsWithAttachments> newsWithAttachmentses) {
                getView().addData(newsWithAttachmentses);
                posts.addAll(newsWithAttachmentses);
            }
        };
        if (checkUtils.isNetworkConnected()) {
            if (!isInAction()) {
                setInAction(true);
                getView().showProgress();
                subscription = interactorNews.getNewsNext(subscriber);
            }
        }else {
            subscriber.onError(new Exception("No internet connection"));
        }
    }

    @Override
    public void onRefresh() {
        Subscriber<List<NewsWithAttachments>> subscriber = new Subscriber<List<NewsWithAttachments>>() {
            @Override
            public void onCompleted() {
                getView().setData(posts);
                setInAction(false);
                getView().closeProrgess();
            }

            @Override
            public void onError(Throwable e) {
                setInAction(false);
                getView().closeProrgess();
                getView().onError(e);
            }

            @Override
            public void onNext(List<NewsWithAttachments> newsWithAttachmentses) {
                posts.addAll(newsWithAttachmentses);
                saveToDB(newsWithAttachmentses, null);
            }
        };
        if (checkUtils.isNetworkConnected()) {
            if (!isInAction()) {
                setInAction(true);
                getView().showProgress();
                dbInteractor.clear(posts);
                posts.clear();
                subscription = interactorNews.getNews(subscriber);
            }
        }else{
            setInAction(true);
            getView().onError(new Exception("No connection.DB loading..."));
            getView().showProgress();
            loadFromDB(subscriber);
        }
    }

    private void saveToDB(final List<NewsWithAttachments> newsWithAttachmentses, final Subscriber<List<NewsWithAttachments>> subscriber) {
        dbInteractor.save(newsWithAttachmentses)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.from(Executors.newCachedThreadPool()))
                .subscribe(new Action1<PutResults<NewsWithAttachments>>() {
                    @Override
                    public void call(final PutResults<NewsWithAttachments> newsWithAttachmentsPutResults) {
//                        subscriber.onNext(newsWithAttachmentses);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(final Throwable throwable) {
//                        subscriber.onError(throwable);
                    }
                }, new Action0() {
                    @Override
                    public void call() {
//                        subscriber.onCompleted();
                    }
                });
    }

    private void loadFromDB(final Subscriber<List<NewsWithAttachments>> subscriber) {
        List<NewsWithAttachments> news = dbInteractor.loadData(RawQueryes.newsQuery(), subscriber);
        if (news != null && !news.isEmpty()) {
            subscriber.onNext(news);
        }
        subscriber.onCompleted();
    }
}
