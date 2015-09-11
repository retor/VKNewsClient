package com.retor.vknewsclient.db.interactor;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.operations.put.PutResults;
import com.pushtorefresh.storio.sqlite.queries.RawQuery;
import com.retor.vknewsclient.db.RawQueryes;
import com.retor.vknewsclient.db.model.NewsWithAttachments;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by retor on 10.09.2015.
 */
public class InteractorDBNews implements InteractorDB<NewsWithAttachments> {
    private StorIOSQLite storIOSQLite;

    @Inject
    public InteractorDBNews(final StorIOSQLite storIOSQLite) {
        this.storIOSQLite = storIOSQLite;
    }

    @Override
    public List<NewsWithAttachments> loadData(RawQuery query, final Subscriber<List<NewsWithAttachments>> subscriber) {
        return storIOSQLite.get()
                .listOfObjects(NewsWithAttachments.class)
                .withQuery(RawQueryes.newsQuery())
                .prepare()
                .executeAsBlocking();
    }

    @Override
    public Observable<PutResults<NewsWithAttachments>> save(final List<NewsWithAttachments> items) {
        return storIOSQLite.put()
                .objects(items)
                .prepare()
                .createObservable();
        /*                .subscribe(new Observer<PutResults<PostWithAttachmentAndComments>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(final Throwable e) {
                        e.printStackTrace();
                        e.toString();
                    }

                    @Override
                    public void onNext(final PutResults<PostWithAttachmentAndComments> postWithAttachmentAndCommentsPutResults) {
                        postWithAttachmentAndCommentsPutResults.toString();
                    }
                });*/
    }

    @Override
    public void clear(List<NewsWithAttachments> list){
        storIOSQLite.delete().objects(list).prepare().executeAsBlocking();
    }
}
