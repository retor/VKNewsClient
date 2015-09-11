package com.retor.vknewsclient.db.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by retor on 06.09.2015.
 */
public class CommentWithAttachments implements Parcelable {
    @NonNull
    private Comment comment;

    private List<AttachmentComment> attachmentList;

    CommentWithAttachments() {
    }

    public CommentWithAttachments(@NonNull Comment comment, List<AttachmentComment> attachmentList) {
        this.comment = comment;
        this.attachmentList = attachmentList;
    }

    @NonNull
    public Comment getComment() {
        return comment;
    }

    public void setComment(@NonNull Comment comment) {
        this.comment = comment;
    }

    public List<AttachmentComment> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<AttachmentComment> attachmentList) {
        this.attachmentList = attachmentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentWithAttachments that = (CommentWithAttachments) o;

        if (!comment.equals(that.comment)) return false;
        return !(attachmentList != null ? !attachmentList.equals(that.attachmentList) : that.attachmentList != null);

    }

    @Override
    public int hashCode() {
        int result = comment.hashCode();
        result = 31 * result + (attachmentList != null ? attachmentList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CommentWithAttachments{" +
                "comment=" + comment +
                ", attachmentList=" + attachmentList +
                '}';
    }

    protected CommentWithAttachments(Parcel in) {
        comment = (Comment) in.readValue(Comment.class.getClassLoader());
        if (in.readByte() == 0x01) {
            attachmentList = new ArrayList<AttachmentComment>();
            in.readList(attachmentList, AttachmentComment.class.getClassLoader());
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
        dest.writeValue(comment);
        if (attachmentList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(attachmentList);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<CommentWithAttachments> CREATOR = new Parcelable.Creator<CommentWithAttachments>() {
        @Override
        public CommentWithAttachments createFromParcel(Parcel in) {
            return new CommentWithAttachments(in);
        }

        @Override
        public CommentWithAttachments[] newArray(int size) {
            return new CommentWithAttachments[size];
        }
    };
}
