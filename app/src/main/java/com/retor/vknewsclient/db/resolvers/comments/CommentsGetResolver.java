package com.retor.vknewsclient.db.resolvers.comments;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.operations.get.GetResolver;
import com.pushtorefresh.storio.sqlite.queries.Query;
import com.pushtorefresh.storio.sqlite.queries.RawQuery;
import com.retor.vknewsclient.db.model.AttachmentComment;
import com.retor.vknewsclient.db.model.Comment;
import com.retor.vknewsclient.db.model.CommentWithAttachments;
import com.retor.vknewsclient.db.tables.AttachCommentsTable;
import com.retor.vknewsclient.db.tables.CommentsTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by retor on 08.09.2015.
 */
public class CommentsGetResolver extends GetResolver<CommentWithAttachments> {
    @NonNull
    @Override
    public CommentWithAttachments mapFromCursor(final Cursor cursor) {
        final Comment comment = Comment.newComment(
                cursor.getInt(cursor.getColumnIndex(CommentsTable.COLUMN_ID)),
                cursor.getInt(cursor.getColumnIndex(CommentsTable.COLUMN_ID)),
                cursor.getInt(cursor.getColumnIndex(CommentsTable.COLUMN_NEWS_ID)),
                cursor.getString(cursor.getColumnIndex(CommentsTable.COLUMN_AUTHOR)),
                cursor.getString(cursor.getColumnIndex(CommentsTable.COLUMN_AUTHOR_PIC)),
                cursor.getString(cursor.getColumnIndex(CommentsTable.COLUMN_CONTENT_TEXT)),
                cursor.getLong(cursor.getColumnIndex(CommentsTable.COLUMN_DATE)),
                cursor.getString(cursor.getColumnIndex(CommentsTable.COLUMN_TO_USER)),
                cursor.getString(cursor.getColumnIndex(CommentsTable.COLUMN_TO_COMMENT)),
                cursor.getInt(cursor.getColumnIndex(CommentsTable.COLUMN_LIKES))
        );
        List<AttachmentComment> attachments = new ArrayList<AttachmentComment>();
/*        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {*/
            attachments.add(AttachmentComment.newAttachment(
                    cursor.getInt(cursor.getColumnIndex(AttachCommentsTable.COLUMN_ID)),
                    cursor.getInt(cursor.getColumnIndex(AttachCommentsTable.COLUMN_COMMENT_ID)),
                    cursor.getString(cursor.getColumnIndex(AttachCommentsTable.COLUMN_TYPE)),
                    cursor.getString(cursor.getColumnIndex(AttachCommentsTable.COLUMN_URL))
            ));
/*            cursor.moveToNext();
        }*/
        return new CommentWithAttachments(comment, attachments);
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
