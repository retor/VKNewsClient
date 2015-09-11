package com.retor.vklib.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.retor.vklib.model.attachments.AttachModel;
import com.retor.vklib.model.beans.Likes;

/**
 * Created by retor on 10.09.2015.
 */
public class VkComment implements Parcelable {
    private int id;
    private int from_id;
    private long date;
    private Likes likes;
    private String text;
    private Attachments<AttachModel> attachments;
    private String reply_to_user;
    private String reply_to_comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFrom_id() {
        return from_id;
    }

    public void setFrom_id(int from_id) {
        this.from_id = from_id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Attachments<AttachModel> getAttachmentsList() {
        return attachments;
    }

    public void setAttachmentsList(Attachments<AttachModel> attachmentsList) {
        this.attachments = attachmentsList;
    }

    public String getReply_to_user() {
        return reply_to_user;
    }

    public void setReply_to_user(String reply_to_user) {
        this.reply_to_user = reply_to_user;
    }

    public String getReply_to_comment() {
        return reply_to_comment;
    }

    public void setReply_to_comment(String reply_to_comment) {
        this.reply_to_comment = reply_to_comment;
    }

    @Override
    public String toString() {
        return "VkComment{" +
                "id=" + id +
                ", from_id=" + from_id +
                ", date=" + date +
                ", likes=" + likes +
                ", text='" + text + '\'' +
                ", attachmentsList=" + attachments +
                ", reply_to_user='" + reply_to_user + '\'' +
                ", reply_to_comment='" + reply_to_comment + '\'' +
                '}';
    }

    protected VkComment(Parcel in) {
        id = in.readInt();
        from_id = in.readInt();
        date = in.readLong();
        likes = (Likes) in.readValue(Likes.class.getClassLoader());
        text = in.readString();
        attachments = (Attachments) in.readValue(Attachments.class.getClassLoader());
        reply_to_user = in.readString();
        reply_to_comment = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(from_id);
        dest.writeLong(date);
        dest.writeValue(likes);
        dest.writeString(text);
        dest.writeValue(attachments);
        dest.writeString(reply_to_user);
        dest.writeString(reply_to_comment);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<VkComment> CREATOR = new Parcelable.Creator<VkComment>() {
        @Override
        public VkComment createFromParcel(Parcel in) {
            return new VkComment(in);
        }

        @Override
        public VkComment[] newArray(int size) {
            return new VkComment[size];
        }
    };
}
