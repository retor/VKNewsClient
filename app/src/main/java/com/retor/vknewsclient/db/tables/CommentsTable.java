package com.retor.vknewsclient.db.tables;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by retor on 05.09.2015.
 */
public class CommentsTable {
    @NonNull
    public static final String TABLE = "comments";
    @NonNull
    public static final String COLUMN_ID = "_id";
    @NonNull
    public static final String COLUMN_COMMENT_ID = "comment_id";
    @NonNull
    public static final String COLUMN_NEWS_ID = "news_id";
    @NonNull
    public static final String COLUMN_AUTHOR = "author";
    @NonNull
    public static final String COLUMN_AUTHOR_PIC = "author_pic";
    @NonNull
    public static final String COLUMN_CONTENT_TEXT = "content_text";
    @NonNull
    public static final String COLUMN_DATE = "date";
    @Nullable
    public static final String COLUMN_TO_USER = "to_user";
    @Nullable
    public static final String COLUMN_TO_COMMENT = "to_comment";
    @NonNull
    public static final String COLUMN_LIKES = "likes_count";

    // This is just class with Meta Data, we don't need instances
    private CommentsTable() {
        throw new IllegalStateException("No instances please");
    }

    // Better than static final field -> allows VM to unload useless String
    // Because you need this string only once per application life on the device
    @NonNull
    public static String getCreateTableQuery() {
        return "CREATE TABLE " + TABLE + "("
                + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY, "
                + COLUMN_COMMENT_ID + " INTEGER NOT NULL, "
                + COLUMN_NEWS_ID + " INTEGER NOT NULL, "
                + COLUMN_AUTHOR + " TEXT NOT NULL, "
                + COLUMN_AUTHOR_PIC + " TEXT NOT NULL, "
                + COLUMN_CONTENT_TEXT + " TEXT NOT NULL, "
                + COLUMN_DATE + " INTEGER NOT NULL, "
                + COLUMN_TO_USER + " INTEGER NULLABLE, "
                + COLUMN_TO_COMMENT + " INTEGER NULLABLE, "
                + COLUMN_LIKES + " INTEGER NOT NULL"
                + ");";
    }
}
