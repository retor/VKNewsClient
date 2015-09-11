package com.retor.vknewsclient.presenter;

import android.os.Bundle;

/**
 * Created by retor on 28.08.2015.
 */
public abstract class Presenter<T, V> {
    protected V view;
    private boolean inAction = false;

    public V getView() {
        return view;
    }

    public void setView(final V view) {
        this.view = view;
    }

    public boolean isInAction() {
        return inAction;
    }

    public void setInAction(final boolean inAction) {
        this.inAction = inAction;
    }

    public abstract void onCreate(T activity, Bundle savedState);
    public abstract void onResume(T activity);
    public abstract void onDestroy();
    public abstract void onSaveState(Bundle outState);
    public abstract void onRestoreState(Bundle savedState);
}
