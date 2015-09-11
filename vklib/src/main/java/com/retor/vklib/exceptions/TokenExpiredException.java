package com.retor.vklib.exceptions;

/**
 * Created by retor on 02.09.2015.
 */
public class TokenExpiredException extends Exception {
    public TokenExpiredException() {
    }

    public TokenExpiredException(final String detailMessage) {
        super(detailMessage);
    }

    public TokenExpiredException(final String detailMessage, final Throwable throwable) {
        super(detailMessage, throwable);
    }

    public TokenExpiredException(final Throwable throwable) {
        super(throwable);
    }
}
