package com.retor.vknewsclient.db.interactor;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.operations.put.PutResults;
import com.pushtorefresh.storio.sqlite.queries.RawQuery;
import com.retor.vknewsclient.db.model.CommentWithAttachments;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by retor on 10.09.2015.
 */
public class InteractorDBComments implements InteractorDB<CommentWithAttachments> {
    private StorIOSQLite storIOSQLite;

    @Inject
    public InteractorDBComments(final StorIOSQLite storIOSQLite) {
        this.storIOSQLite = storIOSQLite;
    }

    @Override
    public List<CommentWithAttachments> loadData(RawQuery query,  final Subscriber<List<CommentWithAttachments>> subscriber) {
        return storIOSQLite.get()
                .listOfObjects(CommentWithAttachments.class)
                .withQuery(query)
                .prepare()
                .executeAsBlocking();
    }

    @Override
    public Observable<PutResults<CommentWithAttachments>> save(final List<CommentWithAttachments> items) {
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
    public void clear(List<CommentWithAttachments> list){
        storIOSQLite.delete().objects(list).prepare().executeAsBlocking();
    }
}
