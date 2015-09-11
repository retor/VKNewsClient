package com.retor.vknewsclient.di.app;

import android.content.Context;

import com.retor.vklib.authorizator.Authorizator;
import com.retor.vklib.authorizator.interfaces.IAuth;
import com.retor.vknewsclient.utils.PicLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by retor on 03.09.2015.
 */
@Module
public class SupportModule {
    @Provides@Singleton
    public IAuth providesAuthorizator(){
        return new Authorizator();
    }

    @Provides@Singleton
    public PicLoader providesPicLoader(Context context){
        return new PicLoader(context);
    }
}
