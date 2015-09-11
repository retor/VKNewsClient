package com.retor.vklib.model;

import com.retor.vklib.authorizator.utils.StringUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by retor on 27.08.2015.
 */
public class TokenKey implements Serializable {
    private String token;
    private long expired;
    private int user_id;

    private TokenKey() {
    }

    private TokenKey(Builder builder) {
        token = builder.token;
        expired = builder.expired;
        user_id = builder.user_id;
    }

    public String getToken() {
        return token;
    }

    public static final class Builder {
        private String token;
        private long expired;
        private int user_id;

        public Builder() {
        }

        public TokenKey buildToken(String val) throws NullPointerException {
            Map<String, String> stringStringMap = StringUtils.explodeQueryString(val);
            token = stringStringMap.get("access_token");
            expired = Long.parseLong(stringStringMap.get("expires_in"));
            user_id = Integer.parseInt(stringStringMap.get("user_id"));
            return new TokenKey(this);
        }
    }

    @Override
    public String toString() {
        return "access_token=" + token + "&" + "expires_in=" + expired + "&" + "user_id=" + user_id;
    }
}
