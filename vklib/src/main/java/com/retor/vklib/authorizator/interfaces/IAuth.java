package com.retor.vklib.authorizator.interfaces;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by retor on 22.06.2015.
 */
public interface IAuth {
    void setListener(final IAuthListener listener);
    void authorization(Activity activity);
    void logout(Activity activity);
    void onResult(Activity activity, int resultCode, Intent intent);
}
