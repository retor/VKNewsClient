package com.retor.vknewsclient.db.resolvers.comments;

import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.operations.put.PutResolver;
import com.pushtorefresh.storio.sqlite.operations.put.PutResult;
import com.pushtorefresh.storio.sqlite.operations.put.PutResults;
import com.retor.vknewsclient.db.model.CommentWithAttachments;
import com.retor.vknewsclient.db.tables.AttachCommentsTable;
import com.retor.vknewsclient.db.tables.CommentsTable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by retor on 08.09.2015.
 */
public class CommentsPutResolver extends PutResolver<CommentWithAttachments> {
    @NonNull
    @Override
    public PutResult performPut(final StorIOSQLite storIOSQLite, final CommentWithAttachments object) {
        final PutResults<Object> putResults = storIOSQLite
                .put()
                .objects(createList(object))
                .prepare() // BTW: it will use transaction!
                .executeAsBlocking();

        final Set<String> affectedTables = new HashSet<String>(2);

        affectedTables.add(CommentsTable.TABLE);
        affectedTables.add(AttachCommentsTable.TABLE);

        // Actually, it's not very clear what PutResult should we return here…
        // Because there is no table for this pair of tweet and user
        // So, let's just return Update Result
        return PutResult.newUpdateResult(putResults.numberOfUpdates(), affectedTables);
    }

    @NonNull
    private List<Object> createList(final CommentWithAttachments object) {
        List<Object> list = new ArrayList<Object>();
        list.add(object.getComment());
        if (object.getAttachmentList() != null)
            list.addAll(object.getAttachmentList());
        return list;
    }
}
