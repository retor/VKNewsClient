package com.retor.vklib.model.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by retor on 24.08.2015.
 */
public class Comments implements Parcelable {
    private int count;

    private int can_post;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean getCan_post() {
        return can_post!=0;
    }

    public void setCan_post(int can_post) {
        this.can_post = can_post;
    }

    @Override
    public String toString() {
        return "ClassPojo [count = " + count + ", can_post = " + can_post + "]";
    }

    protected Comments(Parcel in) {
        count = in.readInt();
        can_post = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(count);
        dest.writeInt(can_post);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Comments> CREATOR = new Parcelable.Creator<Comments>() {
        @Override
        public Comments createFromParcel(Parcel in) {
            return new Comments(in);
        }

        @Override
        public Comments[] newArray(int size) {
            return new Comments[size];
        }
    };
}