package com.retor.vknewsclient.db.interactor;

import com.pushtorefresh.storio.sqlite.operations.put.PutResults;
import com.pushtorefresh.storio.sqlite.queries.RawQuery;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by retor on 07.09.2015.
 */
public interface InteractorDB<T> {
    List<T> loadData(RawQuery query,  Subscriber<List<T>> subscriber);
    Observable<PutResults<T>> save(List<T> items);
    void clear(List<T> items);
}
