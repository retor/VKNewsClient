package com.retor.vknewsclient.db.model;

import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;
import com.retor.vknewsclient.db.tables.AttachCommentsTable;

/**
 * Created by retor on 06.09.2015.
 */
@StorIOSQLiteType(table = AttachCommentsTable.TABLE)
public class AttachmentComment {
    @StorIOSQLiteColumn(name = AttachCommentsTable.COLUMN_ID, key = true)
    int _id;
    @NonNull
    @StorIOSQLiteColumn(name = AttachCommentsTable.COLUMN_COMMENT_ID)
    int comment_id;
    @NonNull
    @StorIOSQLiteColumn(name = AttachCommentsTable.COLUMN_TYPE)
    String type;
    @NonNull
    @StorIOSQLiteColumn(name = AttachCommentsTable.COLUMN_URL)
    String url;

    AttachmentComment() {
    }

    AttachmentComment(int _id, @NonNull int comment_id, @NonNull String type, @NonNull String url) {
        this._id = _id;
        this.comment_id = comment_id;
        this.type = type;
        this.url = url;
    }

    public static AttachmentComment newAttachment(int _id, @NonNull int comment_id, @NonNull String type, @NonNull String url){
        return new AttachmentComment(_id, comment_id, type, url);
    }

    public int get_id() {
        return _id;
    }

    @NonNull
    public int getComment_id() {
        return comment_id;
    }

    @NonNull
    public String getType() {
        return type;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "AttachmentComment{" +
                "_id=" + _id +
                ", comment_id=" + comment_id +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttachmentComment that = (AttachmentComment) o;

        if (_id != that._id) return false;
        if (comment_id != that.comment_id) return false;
        if (!type.equals(that.type)) return false;
        return url.equals(that.url);

    }

    @Override
    public int hashCode() {
        int result = _id;
        result = 31 * result + comment_id;
        result = 31 * result + type.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }
}
