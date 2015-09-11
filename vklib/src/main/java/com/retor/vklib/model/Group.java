package com.retor.vklib.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by retor on 01.09.2015.
 */
public class Group implements Parcelable {
    private int id;

    private String photo_200;

    private int is_admin;

    private String photo_50;

    private int is_member;

    private int is_closed;

    private String name;

    private String screen_name;

    private String type;

    private String photo_100;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto_200() {
        return photo_200;
    }

    public void setPhoto_200(String photo_200) {
        this.photo_200 = photo_200;
    }

    public boolean getIs_admin() {
        return is_admin!=0;
    }

    public void setIs_admin(int is_admin) {
        this.is_admin = is_admin;
    }

    public String getPhoto_50() {
        return photo_50;
    }

    public void setPhoto_50(String photo_50) {
        this.photo_50 = photo_50;
    }

    public boolean getIs_member() {
        return is_member!=0;
    }

    public void setIs_member(int is_member) {
        this.is_member = is_member;
    }

    public boolean getIs_closed() {
        return is_closed!=0;
    }

    public void setIs_closed(int is_closed) {
        this.is_closed = is_closed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoto_100() {
        return photo_100;
    }

    public void setPhoto_100(String photo_100) {
        this.photo_100 = photo_100;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", photo_200='" + photo_200 + '\'' +
                ", is_admin=" + is_admin +
                ", photo_50='" + photo_50 + '\'' +
                ", is_member=" + is_member +
                ", is_closed=" + is_closed +
                ", name='" + name + '\'' +
                ", screen_name='" + screen_name + '\'' +
                ", type='" + type + '\'' +
                ", photo_100='" + photo_100 + '\'' +
                '}';
    }

    protected Group(Parcel in) {
        id = in.readInt();
        photo_200 = in.readString();
        is_admin = in.readInt();
        photo_50 = in.readString();
        is_member = in.readInt();
        is_closed = in.readInt();
        name = in.readString();
        screen_name = in.readString();
        type = in.readString();
        photo_100 = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(photo_200);
        dest.writeInt(is_admin);
        dest.writeString(photo_50);
        dest.writeInt(is_member);
        dest.writeInt(is_closed);
        dest.writeString(name);
        dest.writeString(screen_name);
        dest.writeString(type);
        dest.writeString(photo_100);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Group> CREATOR = new Parcelable.Creator<Group>() {
        @Override
        public Group createFromParcel(Parcel in) {
            return new Group(in);
        }

        @Override
        public Group[] newArray(int size) {
            return new Group[size];
        }
    };
}