package com.retor.vknewsclient.db.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by retor on 10.09.2015.
 */
public class NewsWithAttachments implements Parcelable {
    @NonNull
    private News news;

    private List<AttachmentNews> attachmentList;

    public NewsWithAttachments() {
    }

    public NewsWithAttachments(@NonNull News news, List<AttachmentNews> attachmentList) {
        this.news = news;
        this.attachmentList = attachmentList;
    }

    @NonNull
    public News getNews() {
        return news;
    }

    public void setNews(@NonNull News news) {
        this.news = news;
    }

    public List<AttachmentNews> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<AttachmentNews> attachmentList) {
        this.attachmentList = attachmentList;
    }

    @Override
    public String toString() {
        return "NewsWithAttachments{" +
                "news=" + news +
                ", attachmentList=" + attachmentList +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final NewsWithAttachments that = (NewsWithAttachments) o;

        if (!news.equals(that.news)) return false;
        return !(attachmentList != null ? !attachmentList.equals(that.attachmentList) : that.attachmentList != null);

    }

    @Override
    public int hashCode() {
        int result = news.hashCode();
        result = 31 * result + (attachmentList != null ? attachmentList.hashCode() : 0);
        return result;
    }

    protected NewsWithAttachments(Parcel in) {
        news = (News) in.readValue(News.class.getClassLoader());
        if (in.readByte() == 0x01) {
            attachmentList = new ArrayList<AttachmentNews>();
            in.readList(attachmentList, AttachmentNews.class.getClassLoader());
        } else {
            attachmentList = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(news);
        if (attachmentList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(attachmentList);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<NewsWithAttachments> CREATOR = new Parcelable.Creator<NewsWithAttachments>() {
        @Override
        public NewsWithAttachments createFromParcel(Parcel in) {
            return new NewsWithAttachments(in);
        }

        @Override
        public NewsWithAttachments[] newArray(int size) {
            return new NewsWithAttachments[size];
        }
    };
}
