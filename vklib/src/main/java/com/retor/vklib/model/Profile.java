package com.retor.vklib.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by retor on 01.09.2015.
 */
public class Profile implements Parcelable {
    private int id;

    private String first_name;

    private String sex;

    private String photo_50;

    private String last_name;

    private String screen_name;

    private String photo_100;

    private String online;

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public String getFirst_name ()
    {
        return first_name;
    }

    public void setFirst_name (String first_name)
    {
        this.first_name = first_name;
    }

    public String getSex ()
    {
        return sex;
    }

    public void setSex (String sex)
    {
        this.sex = sex;
    }

    public String getPhoto_50 ()
    {
        return photo_50;
    }

    public void setPhoto_50 (String photo_50)
    {
        this.photo_50 = photo_50;
    }

    public String getLast_name ()
    {
        return last_name;
    }

    public void setLast_name (String last_name)
    {
        this.last_name = last_name;
    }

    public String getScreen_name ()
    {
        return screen_name;
    }

    public void setScreen_name (String screen_name)
    {
        this.screen_name = screen_name;
    }

    public String getPhoto_100 ()
    {
        return photo_100;
    }

    public void setPhoto_100 (String photo_100)
    {
        this.photo_100 = photo_100;
    }

    public String getOnline ()
    {
        return online;
    }

    public void setOnline (String online)
    {
        this.online = online;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", sex='" + sex + '\'' +
                ", photo_50='" + photo_50 + '\'' +
                ", last_name='" + last_name + '\'' +
                ", screen_name='" + screen_name + '\'' +
                ", photo_100='" + photo_100 + '\'' +
                ", online='" + online + '\'' +
                '}';
    }

    protected Profile(Parcel in) {
        id = in.readInt();
        first_name = in.readString();
        sex = in.readString();
        photo_50 = in.readString();
        last_name = in.readString();
        screen_name = in.readString();
        photo_100 = in.readString();
        online = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(first_name);
        dest.writeString(sex);
        dest.writeString(photo_50);
        dest.writeString(last_name);
        dest.writeString(screen_name);
        dest.writeString(photo_100);
        dest.writeString(online);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Profile> CREATOR = new Parcelable.Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };
}