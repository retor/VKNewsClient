package com.retor.vknewsclient.db.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteColumn;
import com.pushtorefresh.storio.sqlite.annotations.StorIOSQLiteType;
import com.retor.vknewsclient.db.tables.AttachNewsTable;

/**
 * Created by retor on 06.09.2015.
 */
@StorIOSQLiteType(table = AttachNewsTable.TABLE)
public class AttachmentNews implements Parcelable{
    @StorIOSQLiteColumn(name = AttachNewsTable.COLUMN_ID, key = true)
    int _id;
    @NonNull
    @StorIOSQLiteColumn(name = AttachNewsTable.COLUMN_NEWS_ID)
    int news_id;
    @NonNull
    @StorIOSQLiteColumn(name = AttachNewsTable.COLUMN_TYPE)
    String type;
    @NonNull
    @StorIOSQLiteColumn(name = AttachNewsTable.COLUMN_URL)
    String url;

    AttachmentNews() {
    }

    AttachmentNews(int _id, @NonNull int news_id, @NonNull String type, @NonNull String url) {
        this._id = _id;
        this.news_id = news_id;
        this.type = type;
        this.url = url;
    }

    public static AttachmentNews newAttachmentNews(int _id, @NonNull int news_id, @NonNull String type, @NonNull String url){
        return new AttachmentNews(_id, news_id, type, url);
    }

    public int get_id() {
        return _id;
    }

    @NonNull
    public int getNews_id() {
        return news_id;
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
        return "AttachmentNews{" +
                "_id=" + _id +
                ", news_id=" + news_id +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttachmentNews that = (AttachmentNews) o;

        if (_id != that._id) return false;
        if (news_id != that.news_id) return false;
        if (!type.equals(that.type)) return false;
        return url.equals(that.url);

    }

    @Override
    public int hashCode() {
        int result = _id;
        result = 31 * result + news_id;
        result = 31 * result + type.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }
    protected AttachmentNews(Parcel in) {
        _id = in.readInt();
        news_id = in.readInt();
        type = in.readString();
        url = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeInt(news_id);
        dest.writeString(type);
        dest.writeString(url);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<AttachmentNews> CREATOR = new Parcelable.Creator<AttachmentNews>() {
        @Override
        public AttachmentNews createFromParcel(Parcel in) {
            return new AttachmentNews(in);
        }

        @Override
        public AttachmentNews[] newArray(int size) {
            return new AttachmentNews[size];
        }
    };
}
