package com.retor.vklib.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.retor.vklib.model.attachments.AttachModel;
import com.retor.vklib.model.beans.Comments;
import com.retor.vklib.model.beans.Likes;
import com.retor.vklib.model.beans.Post_source;
import com.retor.vklib.model.beans.Reposts;

/**
 * Created by retor on 24.08.2015.
 */
public class NewsPost implements Parcelable {
    public Post_source post_source;

    public String text;

    public Reposts reposts;

    public int source_id;

    public Likes likes;

    public String post_type;

    public Attachments<AttachModel> attachments;

    public int post_id;

    public long date;

    public String type;

    public Comments comments;

    public Post_source getPost_source() {
        return post_source;
    }

    public void setPost_source(Post_source post_source) {
        this.post_source = post_source;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Reposts getReposts() {
        return reposts;
    }

    public void setReposts(Reposts reposts) {
        this.reposts = reposts;
    }

    public int getSource_id() {
        return source_id;
    }

    public void setSource_id(int source_id) {
        this.source_id = source_id;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    public String getPost_type() {
        return post_type;
    }

    public void setPost_type(String post_type) {
        this.post_type = post_type;
    }

    public Attachments<AttachModel> getAttachments() {
        return attachments;
    }

    public void setAttachments(Attachments<AttachModel> attachments) {
        this.attachments = attachments;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "ClassPojo [post_source = " + post_source + ", text = " + text + ", reposts = " + reposts + ", source_id = " + source_id + ", likes = " + likes + ", post_type = " + post_type + ", attachments = " + "" + ", post_id = " + post_id + ", date = " + date + ", type = " + type + ", comments = " + comments + "]";
    }

    protected NewsPost(Parcel in) {
        post_source = (Post_source) in.readValue(Post_source.class.getClassLoader());
        text = in.readString();
        reposts = (Reposts) in.readValue(Reposts.class.getClassLoader());
        source_id = in.readInt();
        likes = (Likes) in.readValue(Likes.class.getClassLoader());
        post_type = in.readString();
        attachments = (Attachments) in.readValue(Attachments.class.getClassLoader());
        post_id = in.readInt();
        date = in.readLong();
        type = in.readString();
        comments = (Comments) in.readValue(Comments.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(post_source);
        dest.writeString(text);
        dest.writeValue(reposts);
        dest.writeInt(source_id);
        dest.writeValue(likes);
        dest.writeString(post_type);
        dest.writeValue(attachments);
        dest.writeInt(post_id);
        dest.writeLong(date);
        dest.writeString(type);
        dest.writeValue(comments);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<NewsPost> CREATOR = new Parcelable.Creator<NewsPost>() {
        @Override
        public NewsPost createFromParcel(Parcel in) {
            return new NewsPost(in);
        }

        @Override
        public NewsPost[] newArray(int size) {
            return new NewsPost[size];
        }
    };
}
