package com.retor.vklib.model.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by retor on 24.08.2015.
 */
public class Likes implements Parcelable {
    private int can_publish;

    private int can_like;

    private int user_likes;

    private int count;

    public boolean getCan_publish ()
    {
        return can_publish!=0;
    }

    public void setCan_publish (int can_publish)
    {
        this.can_publish = can_publish;
    }

    public boolean getCan_like ()
    {
        return can_like!=0;
    }

    public void setCan_like (int can_like)
    {
        this.can_like = can_like;
    }

    public int getUser_likes ()
    {
        return user_likes;
    }

    public void setUser_likes (int user_likes)
    {
        this.user_likes = user_likes;
    }

    public int getCount ()
    {
        return count;
    }

    public void setCount (int count)
    {
        this.count = count;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [can_publish = "+can_publish+", can_like = "+can_like+", user_likes = "+user_likes+", count = "+count+"]";
    }

    protected Likes(Parcel in) {
        can_publish = in.readInt();
        can_like = in.readInt();
        user_likes = in.readInt();
        count = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(can_like);
        dest.writeInt(can_publish);
        dest.writeInt(user_likes);
        dest.writeInt(count);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Likes> CREATOR = new Parcelable.Creator<Likes>() {
        @Override
        public Likes createFromParcel(Parcel in) {
            return new Likes(in);
        }

        @Override
        public Likes[] newArray(int size) {
            return new Likes[size];
        }
    };
}