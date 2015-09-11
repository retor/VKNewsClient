package com.retor.vknewsclient.db.model;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by retor on 05.09.2015.
 */
public class PostWithAttachmentAndComments {
    @NonNull
    private News news;

    private List<AttachmentNews> attachmentList;

    private List<CommentWithAttachments> comments;

    PostWithAttachmentAndComments() {
    }

    public PostWithAttachmentAndComments(@NonNull News news, List<AttachmentNews> attachmentList, List<CommentWithAttachments> comments) {
        this.news = news;
        this.attachmentList = attachmentList;
        this.comments = comments;
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

    public List<CommentWithAttachments> getComments() {
        return comments;
    }

    public void setComments(List<CommentWithAttachments> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "PostWithAttachmentAndComments{" +
                "news=" + news +
                ", attachmentList=" + attachmentList +
                ", comments=" + comments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostWithAttachmentAndComments that = (PostWithAttachmentAndComments) o;

        if (!news.equals(that.news)) return false;
        if (attachmentList != null ? !attachmentList.equals(that.attachmentList) : that.attachmentList != null)
            return false;
        return !(comments != null ? !comments.equals(that.comments) : that.comments != null);

    }

    @Override
    public int hashCode() {
        int result = news.hashCode();
        result = 31 * result + (attachmentList != null ? attachmentList.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        return result;
    }
}
