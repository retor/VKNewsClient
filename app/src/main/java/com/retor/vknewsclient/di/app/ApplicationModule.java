package com.retor.vknewsclient.di.app;

import android.content.Context;

import com.retor.vknewsclient.application.BaseApplication;
import com.retor.vknewsclient.utils.CheckUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by retor on 03.09.2015.
 */
@Module(includes = {SupportModule.class})
public class ApplicationModule {
    private BaseApplication application;

    public ApplicationModule(final BaseApplication application) {
        this.application = application;
    }

    @Provides@Singleton
    public BaseApplication providesBaseApplication(){
        return application;
    }

    @Provides@Singleton
    public Context providesContext(){
        return application;
    }

    @Provides@Singleton
    public CheckUtils providesUtils(Context context){
        return new CheckUtils(context);
    }
}
