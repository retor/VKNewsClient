package com.retor.vklib.model.attachments;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by retor on 01.09.2015.
 */
public class AttachList<T extends AttachModel> implements List<T>, Parcelable {
    private List<T> list = new ArrayList<>();

    public AttachList(final List<T> list) {
        this.list = list;
    }

    public AttachList(T... items) {
        list = Arrays.asList(items);
    }

    @Override
    public void add(final int location, final T object) {
        list.add(location, object);
    }

    @Override
    public boolean add(final T object) {
        return list.add(object);
    }

    @Override
    public boolean addAll(final int location, final Collection<? extends T> collection) {
        return list.addAll(location,collection);
    }

    @Override
    public boolean addAll(final Collection<? extends T> collection) {
        return list.addAll(collection);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(final Object object) {
        return list.contains(object);
    }

    @Override
    public boolean containsAll(final Collection<?> collection) {
        return list.containsAll(collection);
    }

    @Override
    public T get(final int location) {
        return list.get(location);
    }

    @Override
    public int indexOf(final Object object) {
        return list.indexOf(object);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @NonNull
    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public int lastIndexOf(final Object object) {
        return list.lastIndexOf(object);
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @NonNull
    @Override
    public ListIterator<T> listIterator(final int location) {
        return list.listIterator();
    }

    @Override
    public T remove(final int location) {
        return list.remove(location);
    }

    @Override
    public boolean remove(final Object object) {
        return list.remove(object);
    }

    @Override
    public boolean removeAll(final Collection<?> collection) {
        return list.removeAll(collection);
    }

    @Override
    public boolean retainAll(final Collection<?> collection) {
        return list.retainAll(collection);
    }

    @Override
    public T set(final int location, final T object) {
        return list.set(location, object);
    }

    @Override
    public int size() {
        return list.size();
    }

    @NonNull
    @Override
    public List<T> subList(final int start, final int end) {
        return list.subList(start, end);
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @NonNull
    @Override
    public <T1> T1[] toArray(final T1[] array) {
        return list.toArray(array);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(list.size());
        for(T item: this) {
            dest.writeParcelable(item, flags);
        }
        dest.writeInt(this.size()-1);
    }

    /**
     * Creates list from Parcel
     */
    public AttachList(Parcel in) {
        int size = in.readInt();
        for(int i = 0; i < size; i++) {
            list.add( ((T) in.readParcelable(((Object) this).getClass().getClassLoader())));
        }
    }

    public static Creator<AttachList> CREATOR = new Creator<AttachList>() {
        public AttachList createFromParcel(Parcel source) {
            return new AttachList(source);
        }

        public AttachList[] newArray(int size) {
            return new AttachList[size];
        }
    };
}
