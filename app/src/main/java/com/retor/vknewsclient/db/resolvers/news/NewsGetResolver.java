package com.retor.vknewsclient.db.resolvers.news;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.operations.get.GetResolver;
import com.pushtorefresh.storio.sqlite.queries.Query;
import com.pushtorefresh.storio.sqlite.queries.RawQuery;
import com.retor.vknewsclient.db.model.AttachmentNews;
import com.retor.vknewsclient.db.model.News;
import com.retor.vknewsclient.db.model.NewsWithAttachments;
import com.retor.vknewsclient.db.tables.AttachNewsTable;
import com.retor.vknewsclient.db.tables.NewsTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by retor on 10.09.2015.
 */
public class NewsGetResolver extends GetResolver<NewsWithAttachments> {
    @NonNull
    @Override
    public NewsWithAttachments mapFromCursor(final Cursor cursor) {
        final News news = News.newNews(
                cursor.getInt(cursor.getColumnIndex(NewsTable.COLUMN_ID)),
                cursor.getInt(cursor.getColumnIndex(NewsTable.COLUMN_POST_ID)),
                cursor.getInt(cursor.getColumnIndex(NewsTable.COLUMN_USER_ID)),
                cursor.getString(cursor.getColumnIndex(NewsTable.COLUMN_AUTHOR)),
                cursor.getString(cursor.getColumnIndex(NewsTable.COLUMN_AUTHOR_PIC)),
                cursor.getString(cursor.getColumnIndex(NewsTable.COLUMN_CONTENT_TEXT)),
                cursor.getLong(cursor.getColumnIndex(NewsTable.COLUMN_DATE)),
                cursor.getInt(cursor.getColumnIndex(NewsTable.COLUMN_LIKES)),
                cursor.getInt(cursor.getColumnIndex(NewsTable.COLUMN_COMMENTS))
        );

        List<AttachmentNews> attachments = new ArrayList<AttachmentNews>();
        attachments.add(AttachmentNews.newAttachmentNews(
                cursor.getInt(cursor.getColumnIndex(AttachNewsTable.COLUMN_ID)),
                cursor.getInt(cursor.getColumnIndex(AttachNewsTable.COLUMN_NEWS_ID)),
                cursor.getString(cursor.getColumnIndex(AttachNewsTable.COLUMN_TYPE)),
                cursor.getString(cursor.getColumnIndex(AttachNewsTable.COLUMN_URL))
        ));
        return new NewsWithAttachments(news, attachments);
    }

    @NonNull
    @Override
    public Cursor performGet(@NonNull final StorIOSQLite storIOSQLite, @NonNull final RawQuery rawQuery) {
        return storIOSQLite.get().cursor().withQuery(rawQuery).prepare().executeAsBlocking();
    }

    @NonNull
    @Override
    public Cursor performGet(@NonNull final StorIOSQLite storIOSQLite, @NonNull final Query query) {
        return storIOSQLite.get().cursor().withQuery(query).prepare().executeAsBlocking();
    }
}
