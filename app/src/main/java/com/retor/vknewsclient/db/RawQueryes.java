package com.retor.vknewsclient.db;

import com.pushtorefresh.storio.sqlite.queries.RawQuery;
import com.retor.vknewsclient.db.tables.AttachCommentsTable;
import com.retor.vknewsclient.db.tables.AttachNewsTable;
import com.retor.vknewsclient.db.tables.CommentsTable;
import com.retor.vknewsclient.db.tables.NewsTable;

/**
 * Created by retor on 10.09.2015.
 */
public class RawQueryes {
    public static RawQuery newsQuery() {
        return RawQuery.builder()
                .query("SELECT * FROM " + NewsTable.TABLE
                        + " JOIN " + AttachNewsTable.TABLE
                        + " ON " + NewsTable.TABLE + "." + NewsTable.COLUMN_POST_ID
                        + " = " + AttachNewsTable.TABLE + "." + AttachNewsTable.COLUMN_NEWS_ID)
                .build();
    }

    public static RawQuery commentsQuery() {
        return RawQuery.builder()
                .query("SELECT * FROM " + CommentsTable.TABLE
                        + " JOIN " + AttachCommentsTable.TABLE
                        + " ON " + CommentsTable.TABLE + "." + CommentsTable.COLUMN_COMMENT_ID
                        + " = " + AttachCommentsTable.TABLE + "." + AttachCommentsTable.COLUMN_COMMENT_ID)
                .build();
    }

    public static RawQuery clearQuery() {
        return RawQuery.builder()
                .query("DROP TABLE IF EXISTS " + NewsTable.TABLE +
                        ", DROP TABLE IF EXISTS " + CommentsTable.TABLE +
                        ", DROP TABLE IF EXISTS " + AttachNewsTable.TABLE +
                        ", DROP TABLE IF EXISTS " + AttachCommentsTable.TABLE)
                .build();
    }
}
