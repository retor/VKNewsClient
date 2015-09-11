package com.retor.vknewsclient.interactor;

import com.retor.vknewsclient.db.model.CommentWithAttachments;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by retor on 10.09.2015.
 */
public interface InteractorComments {
    Subscription getComments(String owner_id, String comment_id, Subscriber<List<CommentWithAttachments>> subscriber);
    Subscription getCommentsNext(String owner_id, String comment_id, String last_comment, Subscriber<List<CommentWithAttachments>> subscriber);
}
