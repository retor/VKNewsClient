package com.retor.vknewsclient.relations;

import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.queries.RawQuery;
import com.retor.vknewsclient.db.model.CommentWithAttachments;
import com.retor.vknewsclient.db.tables.AttachCommentsTable;
import com.retor.vknewsclient.db.tables.CommentsTable;

import java.util.List;

/**
 * Created by retor on 08.09.2015.
 */
public class CommentRelations {

    @NonNull
    private final StorIOSQLite storIOSQLite;

    public CommentRelations(@NonNull StorIOSQLite storIOSQLite) {
        this.storIOSQLite = storIOSQLite;
    }

    public List<CommentWithAttachments> commentsWithAttachment() {
        return storIOSQLite
                .get()
                .listOfObjects(CommentWithAttachments.class)
                .withQuery(RawQuery.builder()
                        .query("SELECT * FROM " + CommentsTable.TABLE
                                + " JOIN " + AttachCommentsTable.TABLE
                                + " ON " + CommentsTable.TABLE + "." + CommentsTable.COLUMN_ID
                                + " = " + AttachCommentsTable.TABLE + "." + AttachCommentsTable.COLUMN_COMMENT_ID)
                        .build())
                .prepare()
                .executeAsBlocking();
    }
}
