package com.retor.vklib.model.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by retor on 24.08.2015.
 */
public class Post_source implements Parcelable {
    private String type;

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [type = "+type+"]";
    }

    protected Post_source(Parcel in) {
        type = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Post_source> CREATOR = new Parcelable.Creator<Post_source>() {
        @Override
        public Post_source createFromParcel(Parcel in) {
            return new Post_source(in);
        }

        @Override
        public Post_source[] newArray(int size) {
            return new Post_source[size];
        }
    };
}
