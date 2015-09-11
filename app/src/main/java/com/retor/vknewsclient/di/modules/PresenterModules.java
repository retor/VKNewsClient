package com.retor.vknewsclient.di.modules;

import com.retor.vknewsclient.adapters.BaseAdapter;
import com.retor.vknewsclient.adapters.CommentsAdapter;
import com.retor.vknewsclient.adapters.NewsAdapter;
import com.retor.vknewsclient.db.model.CommentWithAttachments;
import com.retor.vknewsclient.db.model.NewsWithAttachments;
import com.retor.vknewsclient.di.scope.ApplicationScope;
import com.retor.vknewsclient.presenter.ListPresenter;
import com.retor.vknewsclient.presenter.LoginPresenter;
import com.retor.vknewsclient.presenter.impl.AuthPresenter;
import com.retor.vknewsclient.presenter.impl.CommentsPresenter;
import com.retor.vknewsclient.presenter.impl.NewsPresenter;

import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by retor on 03.09.2015.
 */
@Module(includes = {InteractorsModule.class})
public class PresenterModules {
    @Provides@ApplicationScope
    public LoginPresenter providesAuthPresenter(AuthPresenter presenter){
        return presenter;
    }
    @Provides@ApplicationScope
    public ListPresenter<List<NewsWithAttachments>> providesNewsPresenter(NewsPresenter presenter){
        return presenter;
    }

    @Provides@ApplicationScope
    public ListPresenter<List<CommentWithAttachments>> providesCommentsPresenter(CommentsPresenter presenter){
        return presenter;
    }

    @Provides
    @ApplicationScope
    public BaseAdapter<NewsAdapter.NewsHolder, NewsWithAttachments> providesNewsAdapter(NewsAdapter adapter) {
        return adapter;
    }

    @Provides@ApplicationScope
    public BaseAdapter<CommentsAdapter.CommentsHolder, CommentWithAttachments> providesCommentsAdapter(CommentsAdapter adapter){
        return adapter;
    }
}
