package com.retor.vknewsclient.view;

import android.support.v4.app.DialogFragment;

/**
 * Created by retor on 28.08.2015.
 */
public interface ListView<T> extends ProgressView {
    void setData(T items);
    void addData(T items);
    void showDetailDialog(DialogFragment dialogFragment);
}
