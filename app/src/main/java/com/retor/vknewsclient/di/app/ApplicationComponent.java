package com.retor.vknewsclient.di.app;

import android.content.Context;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.retor.vklib.authorizator.interfaces.IAuth;
import com.retor.vknewsclient.application.BaseApplication;
import com.retor.vknewsclient.db.di.DBModule;
import com.retor.vknewsclient.db.interactor.InteractorDBComments;
import com.retor.vknewsclient.db.interactor.InteractorDBNews;
import com.retor.vknewsclient.pictures.PicLoader;
import com.retor.vknewsclient.utils.CheckUtils;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by retor on 03.09.2015.
 */
@Singleton
@Component(modules = {ApplicationModule.class, DBModule.class})
public interface ApplicationComponent {
    BaseApplication BASE_APPLICATION();
    CheckUtils CHECK_UTILS();
    IAuth AUTHORIZATOR();
    Context CONTEXT();
    StorIOSQLite STOR_IOSQ_LITE();
    InteractorDBNews DB_INTERACTOR_NEWS();
    InteractorDBComments DB_INTERACTOR_COMMENTS();
    PicLoader PIC_LOADER();
}
