package com.retor.vklib.model.attachments;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by retor on 01.09.2015.
 */
public class Photo extends AttachModel implements Parcelable {
    private String text;

    private int width;

    private int post_id;

    private String photo_604;

    private long date;

    private int id;

    private int album_id;

    private String photo_130;

    private int height;

    private String photo_75;

    private int owner_id;

    private String photo_807;

    private int user_id;

    private String access_key;

    private String type = "photo";

    public Photo() {
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(final int width) {
        this.width = width;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(final int post_id) {
        this.post_id = post_id;
    }

    public String getPhoto_604() {
        return photo_604;
    }

    public void setPhoto_604(final String photo_604) {
        this.photo_604 = photo_604;
    }

    public long getDate() {
        return date;
    }

    public void setDate(final long date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(final int album_id) {
        this.album_id = album_id;
    }

    public String getPhoto_130() {
        return photo_130;
    }

    public void setPhoto_130(final String photo_130) {
        this.photo_130 = photo_130;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(final int height) {
        this.height = height;
    }

    public String getPhoto_75() {
        return photo_75;
    }

    public void setPhoto_75(final String photo_75) {
        this.photo_75 = photo_75;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(final int owner_id) {
        this.owner_id = owner_id;
    }

    public String getPhoto_807() {
        return photo_807;
    }

    public void setPhoto_807(final String photo_807) {
        this.photo_807 = photo_807;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(final int user_id) {
        this.user_id = user_id;
    }

    public String getAccess_key() {
        return access_key;
    }

    public void setAccess_key(final String access_key) {
        this.access_key = access_key;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    protected Photo(Parcel in) {
        text = in.readString();
        width = in.readInt();
        post_id = in.readInt();
        photo_604 = in.readString();
        date = in.readLong();
        id = in.readInt();
        album_id = in.readInt();
        photo_130 = in.readString();
        height = in.readInt();
        photo_75 = in.readString();
        owner_id = in.readInt();
        photo_807 = in.readString();
        user_id = in.readInt();
        access_key = in.readString();
        type = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeInt(width);
        dest.writeInt(post_id);
        dest.writeString(photo_604);
        dest.writeLong(date);
        dest.writeInt(id);
        dest.writeInt(album_id);
        dest.writeString(photo_130);
        dest.writeInt(height);
        dest.writeString(photo_75);
        dest.writeInt(owner_id);
        dest.writeString(photo_807);
        dest.writeInt(user_id);
        dest.writeString(access_key);
        dest.writeString(type);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Photo> CREATOR = new Parcelable.Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };
}