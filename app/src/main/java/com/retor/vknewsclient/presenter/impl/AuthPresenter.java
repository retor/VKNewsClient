package com.retor.vknewsclient.presenter.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.retor.vklib.api.SDK;
import com.retor.vklib.authorizator.interfaces.IAuth;
import com.retor.vklib.model.TokenKey;
import com.retor.vknewsclient.application.BaseApplication;
import com.retor.vknewsclient.R;
import com.retor.vknewsclient.di.modules.DaggerPresentersComponent;
import com.retor.vknewsclient.di.modules.InteractorsModule;
import com.retor.vknewsclient.di.modules.PresenterModules;
import com.retor.vknewsclient.presenter.LoginPresenter;
import com.retor.vknewsclient.ui.NewsFragment;
import com.retor.vknewsclient.utils.CheckUtils;

import javax.inject.Inject;

/**
 * Created by retor on 28.08.2015.
 */
public class AuthPresenter extends LoginPresenter {
    @Inject
    protected IAuth authorizator;
    @Inject
    protected CheckUtils checkUtils;
    @Inject
    protected StorIOSQLite storIOSQLite;

    private final String ACTION_TAG = "action";
    private final String FRAGMENT_TAG = "news_list";
    private FragmentActivity activity;

    @Inject
    public AuthPresenter(BaseApplication application) {
        //TODO Inject here
        DaggerPresentersComponent.builder()
                .applicationComponent(application.getComponent())
                .interactorsModule(new InteractorsModule())
                .presenterModules(new PresenterModules())
                .build().inject(this);
        this.authorizator.setListener(this);
    }

    @Override
    public void onCreate(final FragmentActivity activity, final Bundle savedState) {
        setActivity(activity);
//        if(!SDK.isKey(activity.getApplication()))
            login();
/*        else
            doOnLogin();*/
    }

    @Override
    public void onResume(final FragmentActivity activity) {
        setActivity(activity);
    }

    @Override
    public void login() {
        if (!isInAction()) {
            getView().showProgress();
            setInAction(true);
            authorizator.authorization(getActivity());
        }
    }

    @Override
    public void onResult(final FragmentActivity activity, final int resultcode, final Intent data) {
        setActivity(activity);
        authorizator.onResult(activity, resultcode, data);
    }

    @Override
    public void logout() {
        if (!isInAction())// && isAuthorized())
            authorizator.logout(getActivity());
        doOnLogout();
    }

    @Override
    public void onAccepted(final TokenKey key) {
        getView().closeProrgess();
        setInAction(false);
        doOnLogin();
    }

    @Override
    public void onError(final String message) {
        getView().closeProrgess();
        setInAction(false);
        getView().onError(new NullPointerException(message));
    }

    @Override
    public FragmentActivity getActivity() {
        return activity;
    }

    @Override
    public void onSaveState(final Bundle outState) {
        if (isInAction())
            outState.putBoolean(ACTION_TAG, isInAction());
    }

    @Override
    public void onRestoreState(final Bundle savedState) {
        setInAction(savedState.getBoolean(ACTION_TAG));
    }

    @Override
    public void onDestroy() {
        authorizator = null;
        setView(null);
        setActivity(null);
    }

    private boolean isAuthorized() {
        return SDK.isKey(getActivity().getApplicationContext());
    }

    private void setActivity(final FragmentActivity activity) {
        this.activity = activity;
    }

    private void doOnLogin() {
        //TODO Create new ListFragment with presenter and add to ontainer
        FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
        if (supportFragmentManager.getFragments() != null && supportFragmentManager.findFragmentByTag(FRAGMENT_TAG) != null)
            FRAGMENT_TAG.toString();
//            supportFragmentManager.beginTransaction().show(supportFragmentManager.findFragmentByTag(FRAGMENT_TAG)).commit();
//            supportFragmentManager.beginTransaction().replace(R.id.container, supportFragmentManager.findFragmentByTag(FRAGMENT_TAG), FRAGMENT_TAG).commit();
        else
            supportFragmentManager.beginTransaction().add(R.id.container, new NewsFragment(), FRAGMENT_TAG).commit();

    }

    private void doOnLogout() {
        FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
        if (supportFragmentManager.getFragments() != null) {
            supportFragmentManager.beginTransaction().remove(supportFragmentManager.findFragmentByTag(FRAGMENT_TAG)).commit();
        }
    }
}
