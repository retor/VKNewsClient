package com.retor.vklib.authorizator.interfaces;

import com.retor.vklib.model.TokenKey;

/**
 * Created by retor on 22.06.2015.
 */
public interface IAuthListener {
    void onAccepted(TokenKey key);
    void onError(String message);
}
