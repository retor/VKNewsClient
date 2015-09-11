package com.retor.vknewsclient.db.resolvers.news;

import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.operations.delete.DeleteResolver;
import com.pushtorefresh.storio.sqlite.operations.delete.DeleteResult;
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
public class NewsDeleteResolver extends DeleteResolver<NewsWithAttachments> {
    @NonNull
    @Override
    public DeleteResult performDelete(final StorIOSQLite storIOSQLite, final NewsWithAttachments object) {
        List<Object> objectList = new ArrayList<Object>();
        objectList.add(object.getNews());
        objectList.addAll(object.getAttachmentList());
        storIOSQLite
                .delete()
                .objects(objectList)
                .prepare()
                .executeAsBlocking();

        final Set<String> affectedTables = new HashSet<String>(2);

        affectedTables.add(NewsTable.TABLE);
        affectedTables.add(AttachNewsTable.TABLE);

        return DeleteResult.newInstance(2, affectedTables);
    }
}
