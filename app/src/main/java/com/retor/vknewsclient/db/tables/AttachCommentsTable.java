package com.retor.vknewsclient.db.tables;

import android.support.annotation.NonNull;

/**
 * Created by retor on 05.09.2015.
 */
public class AttachCommentsTable {
    @NonNull
    public static final String TABLE = "attachcomments";

    @NonNull
    public static final String COLUMN_ID = "_id";
    @NonNull
    public static final String COLUMN_COMMENT_ID = "comment_id";

    @NonNull
    public static final String COLUMN_TYPE = "type";

    @NonNull
    public static final String COLUMN_URL = "link";


    // This is just class with Meta Data, we don't need instances
    private AttachCommentsTable() {
        throw new IllegalStateException("No instances please");
    }

    // Better than static final field -> allows VM to unload useless String
    // Because you need this string only once per application life on the device
    @NonNull
    public static String getCreateTableQuery() {
        return "CREATE TABLE " + TABLE + "("
                + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY, "
                + COLUMN_COMMENT_ID + " INTEGER NOT NULL, "
                + COLUMN_TYPE + " TEXT NOT NULL, "
                + COLUMN_URL + " TEXT NOT NULL "
//                + "FOREIGN KEY(" + COLUMN_COMMENT_ID + ") REFERENCES + comments(_id) "
                + ");";
    }
}
