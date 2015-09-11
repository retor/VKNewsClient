package com.retor.vknewsclient.view;

/**
 * Created by retor on 28.08.2015.
 */
public interface ProgressView {
    void showProgress();
    void closeProrgess();
    void onError(Throwable throwable);
}
