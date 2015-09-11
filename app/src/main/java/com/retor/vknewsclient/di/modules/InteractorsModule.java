package com.retor.vknewsclient.di.modules;

import com.retor.vknewsclient.db.interactor.InteractorDB;
import com.retor.vknewsclient.db.interactor.InteractorDBComments;
import com.retor.vknewsclient.db.interactor.InteractorDBNews;
import com.retor.vknewsclient.db.model.CommentWithAttachments;
import com.retor.vknewsclient.db.model.NewsWithAttachments;
import com.retor.vknewsclient.di.scope.ApplicationScope;
import com.retor.vknewsclient.interactor.CommentsInteractor;
import com.retor.vknewsclient.interactor.InteractorComments;
import com.retor.vknewsclient.interactor.InteractorNews;
import com.retor.vknewsclient.interactor.NewsInteractor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by retor on 03.09.2015.
 */
@Module
public class InteractorsModule {
    @Provides@ApplicationScope
    public InteractorNews providesNewsInteractor(){
        return new NewsInteractor();
    }
    @Provides@ApplicationScope
    public InteractorComments providesCommentsInteractor(){
        return new CommentsInteractor();
    }
    @Provides@ApplicationScope
    public InteractorDB<CommentWithAttachments> providesDBInteractorComments(InteractorDBComments interactor){
        return interactor;
    }
    @Provides@ApplicationScope
       public InteractorDB<NewsWithAttachments> providesDBInteractorNews(InteractorDBNews interactor){
        return interactor;
    }
}
