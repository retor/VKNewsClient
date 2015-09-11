package com.retor.vknewsclient.application;

import com.retor.vknewsclient.db.di.DBModule;
import com.retor.vknewsclient.di.app.ApplicationComponent;
import com.retor.vknewsclient.di.app.ApplicationModule;
import com.retor.vknewsclient.di.app.DaggerApplicationComponent;
import com.retor.vknewsclient.di.app.SupportModule;

/**
 * Created by retor on 03.09.2015.
 */
public class VkNewsApplication extends BaseApplication {
    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        //TODO create component here
        createComponent();
    }

    private void createComponent() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .dBModule(new DBModule())
                .supportModule(new SupportModule())
                .build();
    }

    @Override
    public ApplicationComponent getComponent() {
        return component;
    }
}
