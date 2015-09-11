package com.retor.vklib.model.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by retor on 24.08.2015.
 */
public class Reposts implements Parcelable {
    private int count;

    private String user_reposted;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUser_reposted() {
        return user_reposted;
    }

    public void setUser_reposted(String user_reposted) {
        this.user_reposted = user_reposted;
    }

    @Override
    public String toString() {
        return "ClassPojo [count = " + count + ", user_reposted = " + user_reposted + "]";
    }

    protected Reposts(Parcel in) {
        count = in.readInt();
        user_reposted = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(count);
        dest.writeString(user_reposted);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Reposts> CREATOR = new Parcelable.Creator<Reposts>() {
        @Override
        public Reposts createFromParcel(Parcel in) {
            return new Reposts(in);
        }

        @Override
        public Reposts[] newArray(int size) {
            return new Reposts[size];
        }
    };
}