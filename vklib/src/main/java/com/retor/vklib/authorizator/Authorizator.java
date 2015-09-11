package com.retor.vklib.authorizator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.retor.vklib.api.SDK;
import com.retor.vklib.authorizator.interfaces.IAuth;
import com.retor.vklib.authorizator.interfaces.IAuthListener;
import com.retor.vklib.authorizator.utils.PreferencesManager;
import com.retor.vklib.model.TokenKey;

/**
 * Created by retor on 22.06.2015.
 */
public class Authorizator implements IAuth {
    private boolean inProgress = false;
    private IAuthListener listener;

    public Authorizator() {
    }

    public Authorizator(final IAuthListener listener) {
        this.listener = listener;
    }

    public void setListener(final IAuthListener listener) {
        this.listener = listener;
    }

    @Override
    public void authorization(Activity activity) {
        if (!inProgress)
            if (PreferencesManager.isContainsKey(activity.getApplicationContext()))
                tokenAccepted(PreferencesManager.loadKey(activity.getApplicationContext()));
            else {
                if (isNetworkConnected(activity))
                    startActivityToresult(activity);
                else
                    authError("No internet connection");
            }

    }

    @Override
    public void logout(Activity activity) {
        PreferencesManager.deleteKey(activity.getApplicationContext());
        activity.recreate();
        SDK.setKey(null);
        if (inProgress)
            inProgress = false;
    }

    private void startActivityToresult(Activity activity) {
        inProgress = true;
        activity.startActivityForResult(new Intent(activity, AuthActivity.class), Constants.REQUEST_CODE_OK);
    }

    public void onResult(Activity activity, int resultCode, Intent intent) {
        inProgress = false;
        if (resultCode == Constants.REQUEST_CODE_OK && intent.hasExtra(Constants.KEY_INTENT)) {
            try {
                TokenKey key = new TokenKey.Builder().buildToken(intent.getStringExtra(Constants.KEY_INTENT));
                PreferencesManager.saveKey(activity.getApplicationContext(), key);
                tokenAccepted(key);
            } catch (NullPointerException exception) {
                authError(null);
            }
        }
    }

    private void tokenAccepted(TokenKey key) {
        SDK.setKey(key);
        listener.onAccepted(key);
    }

    private void authError(String string) {
        if (string == null)
            listener.onError("Cant take token key");
        else
            listener.onError(string);
    }

    public boolean isNetworkConnected(Context context) {
        NetworkInfo ni = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return ni != null && ni.isConnected();
    }
}
