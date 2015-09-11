package com.retor.vknewsclient.adapters;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by retor on 28.08.2015.
 */
public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<VH>{
    protected List<T> list;

    protected T getPost(int id){
        return list.get(id);
    }

    public void setList(final List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public void addPosts(final List<T> postList) {
        if (this.list==null)
            this.list = postList;
        else
            this.list.addAll(postList);
        notifyDataSetChanged();
    }

    public void clear(){
        list.clear();
        notifyDataSetChanged();
    }
}
