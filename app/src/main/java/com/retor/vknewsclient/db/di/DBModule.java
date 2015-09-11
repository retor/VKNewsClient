package com.retor.vknewsclient.db.di;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.SQLiteTypeMapping;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite;
import com.retor.vknewsclient.db.PostsDBHandler;
import com.retor.vknewsclient.db.model.AttachmentComment;
import com.retor.vknewsclient.db.model.AttachmentCommentStorIOSQLiteDeleteResolver;
import com.retor.vknewsclient.db.model.AttachmentCommentStorIOSQLiteGetResolver;
import com.retor.vknewsclient.db.model.AttachmentCommentStorIOSQLitePutResolver;
import com.retor.vknewsclient.db.model.AttachmentNews;
import com.retor.vknewsclient.db.model.AttachmentNewsStorIOSQLiteDeleteResolver;
import com.retor.vknewsclient.db.model.AttachmentNewsStorIOSQLiteGetResolver;
import com.retor.vknewsclient.db.model.AttachmentNewsStorIOSQLitePutResolver;
import com.retor.vknewsclient.db.model.Comment;
import com.retor.vknewsclient.db.model.CommentStorIOSQLiteDeleteResolver;
import com.retor.vknewsclient.db.model.CommentStorIOSQLiteGetResolver;
import com.retor.vknewsclient.db.model.CommentStorIOSQLitePutResolver;
import com.retor.vknewsclient.db.model.CommentWithAttachments;
import com.retor.vknewsclient.db.model.News;
import com.retor.vknewsclient.db.model.NewsStorIOSQLiteDeleteResolver;
import com.retor.vknewsclient.db.model.NewsStorIOSQLiteGetResolver;
import com.retor.vknewsclient.db.model.NewsStorIOSQLitePutResolver;
import com.retor.vknewsclient.db.model.NewsWithAttachments;
import com.retor.vknewsclient.db.resolvers.comments.CommentsDeleteResolver;
import com.retor.vknewsclient.db.resolvers.comments.CommentsGetResolver;
import com.retor.vknewsclient.db.resolvers.comments.CommentsPutResolver;
import com.retor.vknewsclient.db.resolvers.news.NewsDeleteResolver;
import com.retor.vknewsclient.db.resolvers.news.NewsGetResolver;
import com.retor.vknewsclient.db.resolvers.news.NewsPutResolver;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by retor on 07.09.2015.
 */
@Module
public class DBModule {

    @Provides
    @NonNull
    @Singleton
    public StorIOSQLite providesStorIOSQLite(SQLiteOpenHelper helper){
        return DefaultStorIOSQLite.builder()
                .sqliteOpenHelper(helper)
                .addTypeMapping(News.class, SQLiteTypeMapping.<News>builder()
                        .putResolver(new NewsStorIOSQLitePutResolver())
                        .getResolver(new NewsStorIOSQLiteGetResolver())
                        .deleteResolver(new NewsStorIOSQLiteDeleteResolver())
                        .build())
                .addTypeMapping(Comment.class, SQLiteTypeMapping.<Comment>builder()
                        .putResolver(new CommentStorIOSQLitePutResolver())
                        .getResolver(new CommentStorIOSQLiteGetResolver())
                        .deleteResolver(new CommentStorIOSQLiteDeleteResolver())
                        .build())
                .addTypeMapping(AttachmentNews.class, SQLiteTypeMapping.<AttachmentNews>builder()
                        .putResolver(new AttachmentNewsStorIOSQLitePutResolver())
                        .getResolver(new AttachmentNewsStorIOSQLiteGetResolver())
                        .deleteResolver(new AttachmentNewsStorIOSQLiteDeleteResolver())
                        .build())
                .addTypeMapping(AttachmentComment.class, SQLiteTypeMapping.<AttachmentComment>builder()
                        .putResolver(new AttachmentCommentStorIOSQLitePutResolver())
                        .getResolver(new AttachmentCommentStorIOSQLiteGetResolver())
                        .deleteResolver(new AttachmentCommentStorIOSQLiteDeleteResolver())
                        .build())
                .addTypeMapping(NewsWithAttachments.class, SQLiteTypeMapping.<NewsWithAttachments>builder()
                        .putResolver(new NewsPutResolver())
                        .getResolver(new NewsGetResolver())
                        .deleteResolver(new NewsDeleteResolver())
                        .build())
                .addTypeMapping(CommentWithAttachments.class, SQLiteTypeMapping.<CommentWithAttachments>builder()
                        .putResolver(new CommentsPutResolver())
                        .getResolver(new CommentsGetResolver())
                        .deleteResolver(new CommentsDeleteResolver())
                        .build())
                .build();
    }

    @Provides
    @NonNull
    @Singleton
    public SQLiteOpenHelper providesSqLiteOpenHelper(Context context){
        return new PostsDBHandler(context);
    }
}
