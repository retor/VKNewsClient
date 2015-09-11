package com.retor.vklib.api;

import android.content.Context;

import com.retor.vklib.authorizator.utils.PreferencesManager;
import com.retor.vklib.model.TokenKey;
import com.retor.vklib.services.RequestService;

/**
 * Created by retor on 27.08.2015.
 */
public class SDK {
    private static TokenKey key;

    public static void setKey(final TokenKey key) {
        SDK.key = key;
    }

    public static void removeKey() {
        SDK.key = null;
    }

    public static boolean isKey(Context context){
        return key!=null && PreferencesManager.isContainsKey(context);
    }

    public static VKNewsApi getNewsApi(){
        return new VKNewsApi(new RequestService());
    }

    static TokenKey getKey() {
        return key;
    }
}
