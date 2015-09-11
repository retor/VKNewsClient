package com.retor.vknewsclient.interactor;

import android.support.annotation.NonNull;

import com.retor.vklib.api.SDK;
import com.retor.vklib.model.NewsPost;
import com.retor.vklib.model.attachments.AttachModel;
import com.retor.vklib.model.attachments.Photo;
import com.retor.vklib.response.NewsResponse;
import com.retor.vknewsclient.db.model.AttachmentNews;
import com.retor.vknewsclient.db.model.News;
import com.retor.vknewsclient.db.model.NewsWithAttachments;
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
 * Created by retor on 28.08.2015.
 */
public class NewsInteractor implements InteractorNews {

    private String next_from;

    private Observable.Transformer<NewsResponse, List<NewsWithAttachments>> TRANSFORMER_NEWS = new Observable.Transformer<NewsResponse, List<NewsWithAttachments>>() {
        @Override
        public Observable<List<NewsWithAttachments>> call(final Observable<NewsResponse> newsResponseObservable) {
            return getPostList(newsResponseObservable);
        }
    };

    @Override
    public Subscription getNews(final Subscriber<List<NewsWithAttachments>> subscriber) {
        return TRANSFORMER_NEWS.call(SDK.getNewsApi().getNews(null))
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.from(Executors.newCachedThreadPool()))
                .subscribe(subscriber);/*new Action1<List<NewsWithAttachments>>() {
                    @Override
                    public void call(final List<NewsWithAttachments> newsWithAttachment) {
                        if (newsWithAttachment.isEmpty())
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
    public Subscription getNewsNext(final Subscriber<List<NewsWithAttachments>> subscriber) {
        if (this.next_from != null)
            return TRANSFORMER_NEWS.call(SDK.getNewsApi().getNews(next_from))
                    .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.from(Executors.newCachedThreadPool()))
                    .subscribe(subscriber);/*new Action1<List<NewsWithAttachments>>() {
                        @Override
                        public void call(final List<NewsWithAttachments> newsWithAttachmentses) {
                            if (newsWithAttachmentses.isEmpty())
                                subscriber.onCompleted();
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(final Throwable throwable) {
                            throwable.printStackTrace();
                            throwable.toString();
                        }
                    }, new Action0() {
                        @Override
                        public void call() {

                        }
                    });*/
        else
            return getNews(subscriber);
    }



    @NonNull
    private Observable<List<AttachmentNews>> getNewsAttachments(final NewsPost post) {
        if (post.getAttachments() == null || post.getAttachments().isEmpty()) {
            List<AttachmentNews> null_list = new ArrayList<AttachmentNews>(0);
            return Observable.from(null_list).toList();
        }else
            return Observable.from(post.getAttachments()).map(new Func1<AttachModel, AttachmentNews>() {
                @Override
                public AttachmentNews call(final AttachModel attachModel) {
                    return AttachmentNews.newAttachmentNews(((Photo) attachModel).getId(), post.getPost_id(), attachModel.getType(), ((Photo) attachModel).getPhoto_130());
                }
            }).toList();
    }

    private Observable<News> getSingleNews(NewsPost post, NewsResponse response) {
        AuthorCreator.Author author = getAuthor(response.profiles, response.groups, post.getSource_id());
        return Observable.just(News.newNews(post.getPost_id(),
                post.getPost_id(),
                author.getId(),
                author.getName(),
                author.getPic(),
                post.getText(),
                post.getDate(),
                post.getLikes().getCount(),
                post.getComments().getCount()
                ));
    }

    private Observable<List<NewsWithAttachments>> getPostList(final Observable<NewsResponse> newsResponseObservable) {
        return newsResponseObservable.flatMap(new Func1<NewsResponse, Observable<List<NewsWithAttachments>>>() {
            @Override
            public Observable<List<NewsWithAttachments>> call(final NewsResponse response) {
                next_from = response.getNext_from();
                return Observable.from(response.getItems())
                        .flatMap(new Func1<NewsPost, Observable<NewsWithAttachments>>() {
                            @Override
                            public Observable<NewsWithAttachments> call(final NewsPost post) {
                                return Observable.combineLatest(getNewsAttachments(post), getSingleNews(post, response), new Func2<List<AttachmentNews>, News, NewsWithAttachments>() {
                                    @Override
                                    public NewsWithAttachments call(final List<AttachmentNews> attachmentNewses, final News news) {
                                        return new NewsWithAttachments(news, attachmentNewses);
                                    }
                                });
                            }
                        }).toList();
            }
        });
    }

}
