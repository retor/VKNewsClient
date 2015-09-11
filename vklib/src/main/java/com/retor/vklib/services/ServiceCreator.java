package com.retor.vklib.services;

/**
 * Created by retor on 02.08.2015.
 */
public interface ServiceCreator {
    <S> S createService(Class<S> serviceClass);
}
