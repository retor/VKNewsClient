package com.retor.vknewsclient.db.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;
import com.retor.vknewsclient.db.tables.NewsTable;

/**
 * Created by retor on 05.09.2015.
 */
@StorIOSQLiteType(table = NewsTable.TABLE)
public class News implements Parcelable{

    @StorIOSQLiteColumn(name = NewsTable.COLUMN_ID, key = true)
    int _id;
    @NonNull
    @StorIOSQLiteColumn(name = NewsTable.COLUMN_POST_ID)
    int post_id;
    @NonNull
    @StorIOSQLiteColumn(name = NewsTable.COLUMN_USER_ID)
    int user_id;
    @NonNull
    @StorIOSQLiteColumn(name = NewsTable.COLUMN_AUTHOR)
    String author;
    @NonNull
    @StorIOSQLiteColumn(name = NewsTable.COLUMN_AUTHOR_PIC)
    String author_pic;
    @NonNull
    @StorIOSQLiteColumn(name = NewsTable.COLUMN_CONTENT_TEXT)
    String text;
    @NonNull
    @StorIOSQLiteColumn(name = NewsTable.COLUMN_DATE)
    long date;
    @NonNull
    @StorIOSQLiteColumn(name = NewsTable.COLUMN_LIKES)
    int likes_count;
    @NonNull
    @StorIOSQLiteColumn(name = NewsTable.COLUMN_COMMENTS)
    int comments_count;


    News() {
    }

    News(final int _id, @NonNull final int post_id, @NonNull final int user_id, @NonNull final String author, @NonNull final String author_pic, @NonNull final String text, @NonNull final long date, @NonNull final int likes_count, @NonNull final int comments_count) {
        this._id = _id;
        this.post_id = post_id;
        this.user_id = user_id;
        this.author = author;
        this.author_pic = author_pic;
        this.text = text;
        this.date = date;
        this.likes_count = likes_count;
        this.comments_count = comments_count;
    }

    public static News newNews(final int _id, @NonNull final int post_id, @NonNull final int user_id, @NonNull final String author, @NonNull final String author_pic, @NonNull final String text, @NonNull final long date, @NonNull final int likes_count, @NonNull final int comments_count) {
        return new News(_id, post_id, user_id, author, author_pic, text, date, likes_count, comments_count);
    }

    public int get_id() {
        return _id;
    }

    @NonNull
    public int getPost_id() {
        return post_id;
    }

    @NonNull
    public int getUser_id() {
        return user_id;
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

    @NonNull
    public int getLikes_count() {
        return likes_count;
    }

    @NonNull
    public int getComments_count() {
        return comments_count;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final News news = (News) o;

        if (_id != news._id) return false;
        if (post_id != news.post_id) return false;
        if (user_id != news.user_id) return false;
        if (date != news.date) return false;
        if (likes_count != news.likes_count) return false;
        if (comments_count != news.comments_count) return false;
        if (!author.equals(news.author)) return false;
        if (!author_pic.equals(news.author_pic)) return false;
        return text.equals(news.text);

    }

    @Override
    public int hashCode() {
        int result = _id;
        result = 31 * result + post_id;
        result = 31 * result + user_id;
        result = 31 * result + author.hashCode();
        result = 31 * result + author_pic.hashCode();
        result = 31 * result + text.hashCode();
        result = 31 * result + (int) (date ^ (date >>> 32));
        result = 31 * result + likes_count;
        result = 31 * result + comments_count;
        return result;
    }

    @Override
    public String toString() {
        return "News{" +
                "_id=" + _id +
                ", post_id=" + post_id +
                ", user_id=" + user_id +
                ", author='" + author + '\'' +
                ", author_pic='" + author_pic + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", likes_count=" + likes_count +
                ", comments_count=" + comments_count +
                '}';
    }

    protected News(Parcel in) {
        _id = in.readInt();
        post_id = in.readInt();
        user_id = in.readInt();
        author = in.readString();
        author_pic = in.readString();
        text = in.readString();
        date = in.readLong();
        likes_count = in.readInt();
        comments_count = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeInt(post_id);
        dest.writeInt(user_id);
        dest.writeString(author);
        dest.writeString(author_pic);
        dest.writeString(text);
        dest.writeLong(date);
        dest.writeInt(likes_count);
        dest.writeInt(comments_count);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<News> CREATOR = new Parcelable.Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };
}
