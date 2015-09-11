package com.retor.vknewsclient.presenter;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.retor.vklib.authorizator.interfaces.IAuthListener;
import com.retor.vknewsclient.view.ProgressView;

/**
 * Created by retor on 28.08.2015.
 */
public abstract class LoginPresenter extends Presenter<FragmentActivity, ProgressView> implements IAuthListener {
    public abstract void login();
    public abstract void logout();
    public abstract void onResult(FragmentActivity activity, int resultcode, Intent data);
    public abstract FragmentActivity getActivity();
}
