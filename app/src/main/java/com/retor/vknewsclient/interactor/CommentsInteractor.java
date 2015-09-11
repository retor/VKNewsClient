package com.retor.vknewsclient.interactor;

import android.support.annotation.NonNull;

import com.retor.vklib.api.SDK;
import com.retor.vklib.model.VkComment;
import com.retor.vklib.model.attachments.AttachModel;
import com.retor.vklib.model.attachments.Photo;
import com.retor.vklib.response.CommentsResponse;
import com.retor.vknewsclient.db.model.AttachmentComment;
import com.retor.vknewsclient.db.model.Comment;
import com.retor.vknewsclient.db.model.CommentWithAttachments;
import com.retor.vknewsclient.utils.AuthorCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

import static com.retor.vknewsclient.utils.AuthorCreator.getAuthor;

/**
 * Created by retor on 10.09.2015.
 */
public class CommentsInteractor implements InteractorComments {
    private String last_comment;

    private Observable.Transformer<CommentsResponse, List<CommentWithAttachments>> TRANSFORMER_COMMENTS = new Observable.Transformer<CommentsResponse, List<CommentWithAttachments>>() {
        @Override
        public Observable<List<CommentWithAttachments>> call(final Observable<CommentsResponse> commentsResponseObservable) {
            return getCommentList(commentsResponseObservable);
        }
    };

    @Override
    public Subscription getComments(final String owner_id, final String comment_id, final Subscriber<List<CommentWithAttachments>> subscriber) {
        return TRANSFORMER_COMMENTS.call(SDK.getNewsApi().getComments(comment_id, owner_id, null))
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.from(Executors.newFixedThreadPool(3)))
                .subscribe(subscriber);/*new Action1<List<CommentWithAttachments>>() {
                    @Override
                    public void call(final List<CommentWithAttachments> commentWithAttachmentses) {
                        if (!commentWithAttachmentses.isEmpty())
                            saveToDB(commentWithAttachmentses, subscriber);
                        else
                            subscriber.onCompleted();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(final Throwable throwable) {
                        throwable.printStackTrace();
                        Log.d("Opss...", throwable.getLocalizedMessage());
                    }
                }, new Action0() {
                    @Override
                    public void call() {

                    }
                });*/
    }

    @Override
    public Subscription getCommentsNext(final String owner_id, final String comment_id, final String last_comment, final Subscriber<List<CommentWithAttachments>> subscriber) {
        if (last_comment!=null) {
            return TRANSFORMER_COMMENTS.call(SDK.getNewsApi().getComments(comment_id, owner_id, this.last_comment))
                    .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.from(Executors.newCachedThreadPool()))
                    .subscribe(subscriber);/*new Action1<List<CommentWithAttachments>>() {
                        @Override
                        public void call(final List<CommentWithAttachments> commentWithAttachmentses) {
                            if (!commentWithAttachmentses.isEmpty())
                                saveToDB(commentWithAttachmentses, subscriber);
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(final Throwable throwable) {
                            throwable.printStackTrace();
                            Log.d("Opss...", throwable.getLocalizedMessage());
                        }
                    }, new Action0() {
                        @Override
                        public void call() {

                        }
                    });*/
        }else{
            return getComments(owner_id, comment_id, subscriber);
        }
    }

    @NonNull
    private Observable<List<AttachmentComment>> getCommentAttachments(final VkComment comment) {
        if (comment.getAttachmentsList() == null || comment.getAttachmentsList().isEmpty()) {
            List<AttachmentComment> null_list = new ArrayList<AttachmentComment>(0);
            return Observable.from(null_list).toList();
        }else
            return Observable.from(comment.getAttachmentsList()).map(new Func1<AttachModel, AttachmentComment>() {
                @Override
                public AttachmentComment call(final AttachModel attachModel) {
                    return AttachmentComment.newAttachment(((Photo) attachModel).getId(), comment.getId(), attachModel.getType(), ((Photo) attachModel).getPhoto_130());
                }
            }).toList();
    }

    private Observable<List<CommentWithAttachments>> getCommentList(final Observable<CommentsResponse> commentsResponseObservable) {
        return commentsResponseObservable.flatMap(new Func1<CommentsResponse, Observable<List<CommentWithAttachments>>>() {
            @Override
            public Observable<List<CommentWithAttachments>> call(final CommentsResponse response) {
                last_comment = response.next_from;
                return Observable.from(response.items)
                        .flatMap(new Func1<VkComment, Observable<CommentWithAttachments>>() {
                            @Override
                            public Observable<CommentWithAttachments> call(final VkComment comment) {
                                return Observable.combineLatest(getCommentAttachments(comment), getSingleComment(comment, response), new Func2<List<AttachmentComment>, Comment, CommentWithAttachments>() {

                                    @Override
                                    public CommentWithAttachments call(final List<AttachmentComment> attachmentComments, final Comment comment) {
                                        return new CommentWithAttachments(comment, attachmentComments);
                                    }
                                });
                            }
                        }).toList();
            }
        });
    }

    private Observable<Comment> getSingleComment(VkComment comment, CommentsResponse response) {
        AuthorCreator.Author author = getAuthor(response.profiles, response.groups,comment.getFrom_id());
        return Observable.just(Comment.newComment(
                comment.getId(),
                comment.getId(),
                response.getPost_id(),
                author.getName(),
                author.getPic(),
                comment.getText(),
                comment.getDate(),
                comment.getReply_to_user(),
                comment.getReply_to_comment(),
                comment.getLikes().getCount()));
    }
}
