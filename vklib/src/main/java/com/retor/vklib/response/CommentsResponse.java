package com.retor.vklib.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.retor.vklib.model.Group;
import com.retor.vklib.model.Profile;
import com.retor.vklib.model.VkComment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by retor on 10.09.2015.
 */
public class CommentsResponse implements Parcelable {

    public List<VkComment> items;

    public int post_id;

    //Last comment id
    public String next_from;

    public List<Profile> profiles;

    public List<Group> groups;

    public CommentsResponse() {
    }

    public List<VkComment> getItems() {
        return items;
    }

    public void setItems(List<VkComment> items) {
        this.items = items;
    }

    public String getNext_from() {
        return next_from;
    }

    public void setNext_from(String next_from) {
        this.next_from = next_from;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(final int post_id) {
        this.post_id = post_id;
    }

    protected CommentsResponse(Parcel in) {
        if (in.readByte() == 0x01) {
            items = new ArrayList<VkComment>();
            in.readList(items, VkComment.class.getClassLoader());
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
        post_id = in.readInt();
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
        dest.writeInt(post_id);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<CommentsResponse> CREATOR = new Parcelable.Creator<CommentsResponse>() {
        @Override
        public CommentsResponse createFromParcel(Parcel in) {
            return new CommentsResponse(in);
        }

        @Override
        public CommentsResponse[] newArray(int size) {
            return new CommentsResponse[size];
        }
    };
}
