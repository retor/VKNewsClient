package com.retor.vknewsclient.db.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;
import com.retor.vknewsclient.db.tables.CommentsTable;

/**
 * Created by retor on 05.09.2015.
 */
@StorIOSQLiteType(table = CommentsTable.TABLE)
public class Comment {

    @StorIOSQLiteColumn(name = CommentsTable.COLUMN_ID, key = true)
    int _id;
    @NonNull
    @StorIOSQLiteColumn(name = CommentsTable.COLUMN_COMMENT_ID)
    int comment_id;
    @NonNull
    @StorIOSQLiteColumn(name = CommentsTable.COLUMN_NEWS_ID)
    int news_id;
    @NonNull
    @StorIOSQLiteColumn(name = CommentsTable.COLUMN_AUTHOR)
    String author;
    @NonNull
    @StorIOSQLiteColumn(name = CommentsTable.COLUMN_AUTHOR_PIC)
    String author_pic;
    @NonNull
    @StorIOSQLiteColumn(name = CommentsTable.COLUMN_CONTENT_TEXT)
    String text;
    @NonNull
    @StorIOSQLiteColumn(name = CommentsTable.COLUMN_DATE)
    long date;
    @Nullable
    @StorIOSQLiteColumn(name = CommentsTable.COLUMN_TO_USER)
    String to_user;
    @Nullable
    @StorIOSQLiteColumn(name = CommentsTable.COLUMN_TO_COMMENT)
    String to_comment;
    @NonNull
    @StorIOSQLiteColumn(name = CommentsTable.COLUMN_LIKES)
    int likes;

    public Comment() {
    }

    public Comment(@NonNull final int comment_id, final int _id, @NonNull final int news_id, @NonNull final String author, @NonNull final String author_pic, @NonNull final String text, @NonNull final long date, final String to_user, final String to_comment, @NonNull final int likes) {
        this.comment_id = comment_id;
        this._id = _id;
        this.news_id = news_id;
        this.author = author;
        this.author_pic = author_pic;
        this.text = text;
        this.date = date;
        this.to_user = to_user;
        this.to_comment = to_comment;
        this.likes = likes;
    }

    public static Comment newComment(@NonNull final int comment_id, final int _id, @NonNull final int news_id, @NonNull final String author, @NonNull final String author_pic, @NonNull final String text, @NonNull final long date, final String to_user, final String to_comment, @NonNull final int likes) {
        return new Comment(comment_id,_id,news_id,author,author_pic,text,date,to_user,to_comment,likes);
    }

    public int get_id() {
        return _id;
    }

    @NonNull
    public int getComment_id() {
        return comment_id;
    }

    @NonNull
    public int getNews_id() {
        return news_id;
    }

    @NonNull
    public String getAuthor() {
        return author;
    }

    @NonNull
    public String getAuthor_pic() {
        return author_pic;
    }

    @NonNull
    public String getText() {
        return text;
    }

    @NonNull
    public long getDate() {
        return date;
    }

    @Nullable
    public String getTo_user() {
        return to_user;
    }

    @Nullable
    public String getTo_comment() {
        return to_comment;
    }

    @NonNull
    public int getLikes() {
        return likes;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Comment comment = (Comment) o;

        if (_id != comment._id) return false;
        if (comment_id != comment.comment_id) return false;
        if (news_id != comment.news_id) return false;
        if (date != comment.date) return false;
        if (likes != comment.likes) return false;
        if (!author.equals(comment.author)) return false;
        if (!author_pic.equals(comment.author_pic)) return false;
        if (!text.equals(comment.text)) return false;
        if (to_user != null ? !to_user.equals(comment.to_user) : comment.to_user != null)
            return false;
        return !(to_comment != null ? !to_comment.equals(comment.to_comment) : comment.to_comment != null);

    }

    @Override
    public int hashCode() {
        int result = _id;
        result = 31 * result + comment_id;
        result = 31 * result + news_id;
        result = 31 * result + author.hashCode();
        result = 31 * result + author_pic.hashCode();
        result = 31 * result + text.hashCode();
        result = 31 * result + (int) (date ^ (date >>> 32));
        result = 31 * result + (to_user != null ? to_user.hashCode() : 0);
        result = 31 * result + (to_comment != null ? to_comment.hashCode() : 0);
        result = 31 * result + likes;
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "_id=" + _id +
                ", comment_id=" + comment_id +
                ", news_id=" + news_id +
                ", author='" + author + '\'' +
                ", author_pic='" + author_pic + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", to_user='" + to_user + '\'' +
                ", to_comment='" + to_comment + '\'' +
                ", likes=" + likes +
                '}';
    }
}
