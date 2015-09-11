package com.retor.vknewsclient.di.modules;

import com.retor.vknewsclient.di.app.ApplicationComponent;
import com.retor.vknewsclient.di.scope.ApplicationScope;
import com.retor.vknewsclient.presenter.impl.AuthPresenter;
import com.retor.vknewsclient.presenter.impl.CommentsPresenter;
import com.retor.vknewsclient.presenter.impl.NewsPresenter;
import com.retor.vknewsclient.ui.MainActivity;
import com.retor.vknewsclient.ui.NewsDetailsDialog;
import com.retor.vknewsclient.ui.NewsFragment;

import dagger.Component;

/**
 * Created by retor on 03.09.2015.
 */
@ApplicationScope
@Component(modules = {PresenterModules.class},
dependencies = {ApplicationComponent.class})
public interface PresentersComponent {
    void inject(AuthPresenter presenter);
    void inject(NewsPresenter presenter);
    void inject(CommentsPresenter presenter);
    void inject(MainActivity activity);
    void inject(NewsFragment fragment);
    void inject(NewsDetailsDialog newsDetailsDialog);
}
