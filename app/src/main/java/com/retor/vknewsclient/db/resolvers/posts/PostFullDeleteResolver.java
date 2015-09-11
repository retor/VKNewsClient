package com.retor.vknewsclient.db.resolvers.posts;

import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.operations.delete.DeleteResolver;
import com.pushtorefresh.storio.sqlite.operations.delete.DeleteResult;
import com.retor.vknewsclient.db.model.CommentWithAttachments;
import com.retor.vknewsclient.db.model.PostWithAttachmentAndComments;
import com.retor.vknewsclient.db.resolvers.comments.CommentsDeleteResolver;
import com.retor.vknewsclient.db.tables.AttachNewsTable;
import com.retor.vknewsclient.db.tables.NewsTable;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

/**
 * Created by retor on 08.09.2015.
 */
public class PostFullDeleteResolver extends DeleteResolver<PostWithAttachmentAndComments> {
    @NonNull
    @Override
    public DeleteResult performDelete(final StorIOSQLite storIOSQLite, final PostWithAttachmentAndComments object) {
        // We can even reuse StorIO methods
        storIOSQLite
                .delete()
                .objects(asList(object.getNews(), object.getAttachmentList()))
                .prepare() // BTW: it will use transaction!
                .executeAsBlocking();

        CommentsDeleteResolver deleteResolver = new CommentsDeleteResolver();
        for (CommentWithAttachments commentWithAttachments:object.getComments()){
            deleteResolver.performDelete(storIOSQLite, commentWithAttachments);
        }
        final Set<String> affectedTables = new HashSet<String>(2);

        affectedTables.add(NewsTable.TABLE);
        affectedTables.add(AttachNewsTable.TABLE);

        return DeleteResult.newInstance(2, affectedTables);
    }
}
