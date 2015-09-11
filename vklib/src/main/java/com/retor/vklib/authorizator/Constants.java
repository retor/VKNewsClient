package com.retor.vklib.authorizator;

/**
 * Created by retor on 27.08.2015.
 */
public final class Constants {
    private static final String VERSION = "5.37";
    private static final String RESPONSE_TYPE = "token";
    private static final String CLIENT_ID = "5048359";
    private static final String MAIN_URL = "https://oauth.vk.com/authorize";
    public static final String URL_REDIRECT = "https://oauth.vk.com/blank.html";
    public static final String SCOPES = "wall, status, photos, friends";

    public static final String AUTH_URL = MAIN_URL + "?client_id=" + CLIENT_ID + "&display=mobile&redirect_uri=" + URL_REDIRECT + "&scope=" + SCOPES + "&response_type=" + RESPONSE_TYPE + "&v=" + VERSION;

    public static final String PREF_TOKEN = "token";
    public static final String PREF_TIME = "token_time";
    public static final String KEY_INTENT = "token";
    public static final int REQUEST_CODE_OK = 5670;

}
