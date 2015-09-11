package com.retor.vknewsclient.db.resolvers.posts;

import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.operations.put.PutResolver;
import com.pushtorefresh.storio.sqlite.operations.put.PutResult;
import com.pushtorefresh.storio.sqlite.operations.put.PutResults;
import com.retor.vknewsclient.db.model.PostWithAttachmentAndComments;
import com.retor.vknewsclient.db.tables.AttachCommentsTable;
import com.retor.vknewsclient.db.tables.AttachNewsTable;
import com.retor.vknewsclient.db.tables.CommentsTable;
import com.retor.vknewsclient.db.tables.NewsTable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by retor on 08.09.2015.
 */
public class PostFullPutResolver extends PutResolver<PostWithAttachmentAndComments> {

    @NonNull
    @Override
    public PutResult performPut(@NonNull final StorIOSQLite storIOSQLite, @NonNull final PostWithAttachmentAndComments object) {
        // We can even reuse StorIO methods
        final PutResults<Object> putResults = storIOSQLite
                .put()
                .objects(createList(object))
                .prepare() // BTW: it will use transaction!
                .executeAsBlocking();

        final Set<String> affectedTables = new HashSet<String>(4);
        affectedTables.add(NewsTable.TABLE);
        affectedTables.add(AttachNewsTable.TABLE);
        affectedTables.add(AttachCommentsTable.TABLE);
        affectedTables.add(CommentsTable.TABLE);

        // Actually, it's not very clear what PutResult should we return here…
        // Because there is no table for this pair of tweet and user
        // So, let's just return Update Result
        return PutResult.newUpdateResult(putResults.numberOfUpdates(), affectedTables);
    }

    @NonNull
    private List<Object> createList(final @NonNull PostWithAttachmentAndComments object) {
        List<Object> list = new ArrayList<Object>();
        if (object.getComments()==null) {
            list.add(object.getNews());
            list.addAll(object.getAttachmentList());
        }else {
            list.add(object.getNews());
            list.addAll(object.getAttachmentList());
            list.addAll(object.getComments());
        }
        return list;
    }
}
