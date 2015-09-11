package com.retor.vklib.authorizator.utils;

import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by retor on 31.08.2015.
 */
public class StringUtils {
    @Nullable
    public static Map<String, String> explodeQueryString(@Nullable String queryString) {
        if (queryString == null) {
            return null;
        }
        String[] keyValuePairs = queryString.split("&");
        HashMap<String, String> parameters = new HashMap<>(keyValuePairs.length);

        for (String keyValueString : keyValuePairs) {
            String[] keyValueArray = keyValueString.split("=");
            parameters.put(keyValueArray[0], keyValueArray[1]);
        }
        return parameters;
    }
}
