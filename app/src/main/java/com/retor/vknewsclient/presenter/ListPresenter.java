package com.retor.vknewsclient.presenter;

import android.support.v4.app.Fragment;

import com.retor.vknewsclient.view.ListView;

/**
 * Created by retor on 28.08.2015.
 */
public abstract class ListPresenter<T> extends Presenter<Fragment, ListView<T>> {
    public abstract void onClick(int item_position);
    public abstract void onEndScrolled();
    public abstract void onRefresh();
}
