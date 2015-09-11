package com.retor.vklib.authorizator.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.retor.vklib.authorizator.Constants;
import com.retor.vklib.model.TokenKey;

import java.util.concurrent.TimeUnit;

/**
 * Created by retor on 27.08.2015.
 */
public class PreferencesManager {

    public static TokenKey loadKey(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return new TokenKey.Builder().buildToken(preferences.getString(Constants.PREF_TOKEN, null));
    }

    public static void saveKey(Context context, TokenKey key){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString(Constants.PREF_TOKEN, key.toString())
                .putLong(Constants.PREF_TIME, TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis())).apply();
    }

    public static boolean isContainsKey(Context context){
        long result = (TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis())-PreferenceManager.getDefaultSharedPreferences(context).getLong(Constants.PREF_TIME,0));
        return (PreferenceManager.getDefaultSharedPreferences(context).contains(Constants.PREF_TOKEN)
                && result < 1400);
    }

    public static void deleteKey(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().remove(Constants.PREF_TOKEN).apply();
    }
}
