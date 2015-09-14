package com.retor.vknewsclient.presenter.impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.pushtorefresh.storio.sqlite.operations.put.PutResults;
import com.retor.vknewsclient.application.BaseApplication;
import com.retor.vknewsclient.db.RawQueryes;
import com.retor.vknewsclient.db.interactor.InteractorDB;
import com.retor.vknewsclient.db.model.CommentWithAttachments;
import com.retor.vknewsclient.db.model.NewsWithAttachments;
import com.retor.vknewsclient.di.modules.DaggerPresentersComponent;
import com.retor.vknewsclient.di.modules.InteractorsModule;
import com.retor.vknewsclient.di.modules.PresenterModules;
import com.retor.vknewsclient.interactor.InteractorComments;
import com.retor.vknewsclient.presenter.ListPresenter;
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
 * Created by retor on 10.09.2015.
 */
public class CommentsPresenter extends ListPresenter<List<CommentWithAttachments>> {
    @Inject
    protected InteractorComments interactorComments;
    @Inject
    protected InteractorDB<CommentWithAttachments> dbInteractor;
    @Inject
    protected CheckUtils checkUtils;
    @State
    List<CommentWithAttachments> posts;
    private Subscription subscription;
    private final String ACTION_TAG = "action";
    private NewsWithAttachments news;

    @Inject
    public CommentsPresenter(BaseApplication application) {
        DaggerPresentersComponent.builder()
                .applicationComponent(application.getComponent())
                .interactorsModule(new InteractorsModule())
                .presenterModules(new PresenterModules())
                .build().inject(this);
        posts = new ArrayList<CommentWithAttachments>();
    }

    @Override
    public void onClick(int item_position) {

    }

    @Override
    public void onEndScrolled() {
        Subscriber<List<CommentWithAttachments>> subscriber = new Subscriber<List<CommentWithAttachments>>() {
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
            public void onNext(final List<CommentWithAttachments> commentWithAttachmentses) {
                getView().addData(commentWithAttachmentses);
                posts.addAll(commentWithAttachmentses);
            }
        };
        if (checkUtils.isNetworkConnected()) {
            if (!isInAction()) {
                setInAction(true);
                getView().showProgress();
                subscription = interactorComments.getCommentsNext(String.valueOf(news.getNews().getUser_id()),
                        String.valueOf(news.getNews().getPost_id()),
                        String.valueOf(posts.get(posts.size()-1).getComment().get_id()),
                        subscriber);
            }
        } else {
            subscriber.onError(new Exception("No internet connection"));
        }
    }

    @Override
    public void onRefresh() {
        Subscriber<List<CommentWithAttachments>> subscriber = new Subscriber<List<CommentWithAttachments>>() {
            @Override
            public void onCompleted() {
                getView().setData(posts);
                setInAction(false);
                getView().closeProrgess();
            }

            @Override
            public void onError(final Throwable e) {
                setInAction(false);
                getView().closeProrgess();
                getView().onError(e);
            }

            @Override
            public void onNext(final List<CommentWithAttachments> commentWithAttachmentses) {
                posts.addAll(commentWithAttachmentses);
                saveToDB(commentWithAttachmentses, null);
            }
        };
        if (checkUtils.isNetworkConnected()) {
            if (!isInAction()) {
                setInAction(true);
                getView().showProgress();
                dbInteractor.clear(posts);
                posts.clear();
                subscription = interactorComments.getComments(String.valueOf(news.getNews().getUser_id()), String.valueOf(news.getNews().getPost_id()), subscriber);
            }
        } else {
            setInAction(true);
            getView().onError(new Exception("No connection.DB loading..."));
            getView().showProgress();
            loadFromDB(subscriber);
        }
    }

    @Override
    public void onCreate(Fragment activity, Bundle savedState) {
        this.news = savedState.getParcelable("news");
        if (posts != null && !posts.isEmpty())
            getView().setData(posts);
        else
            onRefresh();
    }

    @Override
    public void onResume(Fragment activity) {

    }

    @Override
    public void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }

    @Override
    public void onSaveState(Bundle outState) {
        outState.putBoolean(ACTION_TAG, isInAction());
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public void onRestoreState(Bundle savedState) {
        if (savedState != null && !savedState.isEmpty()) {
            setInAction(savedState.getBoolean(ACTION_TAG));
        }
        Icepick.restoreInstanceState(this, savedState);
        if (posts != null && !posts.isEmpty())
            getView().setData(posts);
    }


    private void loadFromDB(final Subscriber<List<CommentWithAttachments>> subscriber) {
        List<CommentWithAttachments> comments = dbInteractor.loadData(RawQueryes.commentsQuery(), subscriber);
        if (comments != null && !comments.isEmpty()) {
            subscriber.onNext(comments);
        }
        subscriber.onCompleted();
    }

    private void saveToDB(final List<CommentWithAttachments> commentWithAttachmentses, final Subscriber<List<CommentWithAttachments>> subscriber) {
        dbInteractor.save(commentWithAttachmentses)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.from(Executors.newCachedThreadPool()))
                .subscribe(new Action1<PutResults<CommentWithAttachments>>() {
                    @Override
                    public void call(final PutResults<CommentWithAttachments> commentWithAttachmentsPutResults) {
//                        subscriber.onNext(commentWithAttachmentses);
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
}
