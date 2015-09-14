package com.retor.vklib.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.retor.vklib.model.NewsPost;
import com.retor.vklib.model.Group;
import com.retor.vklib.model.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by retor on 01.09.2015.
 */
public class NewsResponse implements Parcelable {
    public List<NewsPost> items;

    public String next_from;

    public List<Profile> profiles;

    public List<Group> groups;

    public NewsResponse() {
    }

    public NewsResponse(final List<NewsPost> items, final String next_from, final List<Profile> profiles, final List<Group> groups) {
        this.items = items;
        this.next_from = next_from;
        this.profiles = profiles;
        this.groups = groups;
    }

    public List<NewsPost> getItems ()
    {
        return items;
    }

    public void setItems (List<NewsPost> items)
    {
        this.items = items;
    }

    public String getNext_from ()
    {
        return next_from;
    }

    public void setNext_from (String next_from)
    {
        this.next_from = next_from;
    }

    public List<Profile> getProfiles ()
    {
        return profiles;
    }

    public void setProfiles (List<Profile> profiles)
    {
        this.profiles = profiles;
    }

    public List<Group> getGroups ()
    {
        return groups;
    }

    public void setGroups (List<Group> groups)
    {
        this.groups = groups;
    }

    protected NewsResponse(Parcel in) {
        if (in.readByte() == 0x01) {
            items = new ArrayList<NewsPost>();
            in.readList(items, NewsPost.class.getClassLoader());
        } else {
            items = null;
        }
        next_from = in.readString();
        if (in.readByte() == 0x01) {
            profiles = new ArrayList<Profile>();
            in.readList(profiles, Profile.class.getClassLoader());
        } else {
            profiles = null;
        }
        if (in.readByte() == 0x01) {
            groups = new ArrayList<Group>();
            in.readList(groups, Group.class.getClassLoader());
        } else {
            groups = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (items == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(items);
        }
        dest.writeString(next_from);
        if (profiles == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(profiles);
        }
        if (groups == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(groups);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<NewsResponse> CREATOR = new Parcelable.Creator<NewsResponse>() {
        @Override
        public NewsResponse createFromParcel(Parcel in) {
            return new NewsResponse(in);
        }

        @Override
        public NewsResponse[] newArray(int size) {
            return new NewsResponse[size];
        }
    };
}
