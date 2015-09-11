package com.retor.vknewsclient.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.retor.vknewsclient.db.tables.AttachCommentsTable;
import com.retor.vknewsclient.db.tables.AttachNewsTable;
import com.retor.vknewsclient.db.tables.CommentsTable;
import com.retor.vknewsclient.db.tables.NewsTable;

/**
 * Created by retor on 05.09.2015.
 */
public class PostsDBHandler extends SQLiteOpenHelper {
    // Database Info
    private static final String DATABASE_NAME = "postsDatabase";
    private static final int DATABASE_VERSION = 1;

    public PostsDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NewsTable.getCreateTableQuery());
        db.execSQL(CommentsTable.getCreateTableQuery());
        db.execSQL(AttachCommentsTable.getCreateTableQuery());
        db.execSQL(AttachNewsTable.getCreateTableQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + NewsTable.TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CommentsTable.TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + AttachNewsTable.TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + AttachCommentsTable.TABLE);
            onCreate(db);
        }
    }
}
