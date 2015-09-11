package com.retor.vknewsclient.db.resolvers.comments;

import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.operations.delete.DeleteResolver;
import com.pushtorefresh.storio.sqlite.operations.delete.DeleteResult;
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
public class CommentsDeleteResolver extends DeleteResolver<CommentWithAttachments> {
    @NonNull
    @Override
    public DeleteResult performDelete(final StorIOSQLite storIOSQLite, final CommentWithAttachments object) {
        List<Object> objectList = new ArrayList<Object>();
        objectList.add(object.getComment());
        objectList.addAll(object.getAttachmentList());
        storIOSQLite
                .delete()
                .objects(objectList)
                .prepare() // BTW: it will use transaction!
                .executeAsBlocking();

        final Set<String> affectedTables = new HashSet<String>(2);

        affectedTables.add(CommentsTable.TABLE);
        affectedTables.add(AttachCommentsTable.TABLE);

        return DeleteResult.newInstance(2, affectedTables);
    }
}
