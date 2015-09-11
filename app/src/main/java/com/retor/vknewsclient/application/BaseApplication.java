package com.retor.vknewsclient.application;

import android.app.Application;

import com.retor.vknewsclient.di.app.ApplicationComponent;

/**
 * Created by retor on 03.09.2015.
 */
public abstract class BaseApplication extends Application {
    public abstract ApplicationComponent getComponent();
}
