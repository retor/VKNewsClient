package com.retor.vknewsclient.db.resolvers.news;

import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.operations.put.PutResolver;
import com.pushtorefresh.storio.sqlite.operations.put.PutResult;
import com.pushtorefresh.storio.sqlite.operations.put.PutResults;
import com.retor.vknewsclient.db.model.NewsWithAttachments;
import com.retor.vknewsclient.db.tables.AttachNewsTable;
import com.retor.vknewsclient.db.tables.NewsTable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by retor on 10.09.2015.
 */
public class NewsPutResolver extends PutResolver<NewsWithAttachments> {
    @NonNull
    @Override
    public PutResult performPut(final StorIOSQLite storIOSQLite, final NewsWithAttachments object) {
        final PutResults<Object> putResults = storIOSQLite
                .put()
                .objects(createList(object))
                .prepare() // BTW: it will use transaction!
                .executeAsBlocking();

        final Set<String> affectedTables = new HashSet<String>(4);
        affectedTables.add(NewsTable.TABLE);
        affectedTables.add(AttachNewsTable.TABLE);

        return PutResult.newUpdateResult(putResults.numberOfUpdates(), affectedTables);
    }

    @NonNull
    private List<Object> createList(final @NonNull NewsWithAttachments object) {
        List<Object> list = new ArrayList<Object>();
        if (object.getAttachmentList()==null) {
            list.add(object.getNews());
        }else {
            list.add(object.getNews());
            list.addAll(object.getAttachmentList());
        }
        return list;
    }
}
