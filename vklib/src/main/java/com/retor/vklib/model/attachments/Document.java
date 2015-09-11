package com.retor.vklib.model.attachments;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by retor on 01.09.2015.
 */
public class Document extends AttachModel implements Parcelable {
    private int id;
    private int owner_id;
    private String title;
    private int size;
    private String ext;
    private String url;
    private String photo_100;
    private String photo_130;
    private String type = "doc";

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(final int owner_id) {
        this.owner_id = owner_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public int getSize() {
        return size;
    }

    public void setSize(final int size) {
        this.size = size;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(final String ext) {
        this.ext = ext;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getPhoto_100() {
        return photo_100;
    }

    public void setPhoto_100(final String photo_100) {
        this.photo_100 = photo_100;
    }

    public String getPhoto_130() {
        return photo_130;
    }

    public void setPhoto_130(final String photo_130) {
        this.photo_130 = photo_130;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    protected Document(Parcel in) {
        id = in.readInt();
        owner_id = in.readInt();
        title = in.readString();
        size = in.readInt();
        ext = in.readString();
        url = in.readString();
        photo_100 = in.readString();
        photo_130 = in.readString();
        type = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(owner_id);
        dest.writeString(title);
        dest.writeInt(size);
        dest.writeString(ext);
        dest.writeString(url);
        dest.writeString(photo_100);
        dest.writeString(photo_130);
        dest.writeString(type);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Document> CREATOR = new Parcelable.Creator<Document>() {
        @Override
        public Document createFromParcel(Parcel in) {
            return new Document(in);
        }

        @Override
        public Document[] newArray(int size) {
            return new Document[size];
        }
    };
}
