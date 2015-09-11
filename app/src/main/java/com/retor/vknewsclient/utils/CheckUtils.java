package com.retor.vknewsclient.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;

/**
 * Created by retor on 03.09.2015.
 */
public class CheckUtils {
    private Context context;

    @Inject
    public CheckUtils(final Context context) {
        this.context = context;
    }

    public boolean isNetworkConnected() {
        NetworkInfo ni = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return ni != null && ni.isConnected();
    }
}
