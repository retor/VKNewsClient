package com.retor.vknewsclient.relations;

import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.queries.RawQuery;
import com.retor.vknewsclient.db.model.PostWithAttachmentAndComments;
import com.retor.vknewsclient.db.tables.AttachNewsTable;
import com.retor.vknewsclient.db.tables.NewsTable;

import java.util.List;

/**
 * Created by retor on 05.09.2015.
 */
public class PostRelations {

    @NonNull
    private final StorIOSQLite storIOSQLite;

    public PostRelations(@NonNull StorIOSQLite storIOSQLite) {
        this.storIOSQLite = storIOSQLite;
    }

    public List<PostWithAttachmentAndComments> postsFullGet() {
        return storIOSQLite
                .get()
                .listOfObjects(PostWithAttachmentAndComments.class)
                .withQuery(RawQuery.builder()
                        .query("SELECT * FROM " + NewsTable.TABLE
                                + " JOIN " + AttachNewsTable.TABLE

                                + " ON " + NewsTable.TABLE + "." + NewsTable.COLUMN_ID
                                + " = " + AttachNewsTable.TABLE + "." + AttachNewsTable.COLUMN_NEWS_ID)
                        .build())
                .prepare()
                .executeAsBlocking();
    }
}
