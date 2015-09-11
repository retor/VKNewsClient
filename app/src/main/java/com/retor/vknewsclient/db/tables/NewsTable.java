package com.retor.vknewsclient.db.tables;

import android.support.annotation.NonNull;

/**
 * Created by retor on 05.09.2015.
 */
public class NewsTable {
    @NonNull
    public static final String TABLE = "news";
    @NonNull
    public static final String COLUMN_ID = "_id";
    @NonNull
    public static final String COLUMN_POST_ID = "post_id";
    @NonNull
    public static final String COLUMN_USER_ID = "user_id";
    @NonNull
    public static final String COLUMN_AUTHOR = "author";
    @NonNull
    public static final String COLUMN_AUTHOR_PIC = "author_pic";
    @NonNull
    public static final String COLUMN_CONTENT_TEXT = "content_text";
    @NonNull
    public static final String COLUMN_DATE = "date";
    @NonNull
    public static final String COLUMN_LIKES = "likes_count";
    @NonNull
    public static final String COLUMN_COMMENTS = "comments_count";

    // This is just class with Meta Data, we don't need instances
    private NewsTable() {
        throw new IllegalStateException("No instances please");
    }

    // Better than static final field -> allows VM to unload useless String
    // Because you need this string only once per application life on the device
    @NonNull
    public static String getCreateTableQuery() {
        return "CREATE TABLE " + TABLE + "("
                + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY, "
                + COLUMN_POST_ID + " INTEGER NOT NULL, "
                + COLUMN_USER_ID + " INTEGER NOT NULL, "
                + COLUMN_AUTHOR + " TEXT NOT NULL, "
                + COLUMN_AUTHOR_PIC + " TEXT NOT NULL, "
                + COLUMN_CONTENT_TEXT + " TEXT NOT NULL, "
                + COLUMN_LIKES + " INTEGER NOT NULL, "
                + COLUMN_COMMENTS + " INTEGER NOT NULL, "
                + COLUMN_DATE + " INTEGER NOT NULL"
                + ");";
    }
}
