package com.retor.vknewsclient.db.interactor;

/**
 * Created by retor on 05.09.2015.
 */
public class DBInteractor {//implements InteractorDB {
    /*private StorIOSQLite storIOSQLite;

    @Inject
    public DBInteractor(StorIOSQLite storIOSQLite) {
        this.storIOSQLite = storIOSQLite;
    }

    @Override
    public List<PostWithAttachmentAndComments> loadData(final Subscriber subscriber) {
        return storIOSQLite.get()
                .listOfObjects(PostWithAttachmentAndComments.class)
                .withQuery(RawQuery.builder()
                        .query("SELECT * FROM " + NewsTable.TABLE
                                + " JOIN " + AttachNewsTable.TABLE

                                + " ON " + NewsTable.TABLE + "." + NewsTable.COLUMN_ID
                                + " = " + AttachNewsTable.TABLE + "." + AttachNewsTable.COLUMN_NEWS_ID)
                        .build())
                .prepare()
                .executeAsBlocking();

*//*                new PostRelations(storIOSQLite)
                .postsFullGet();*//*
*//*                .get()
                .listOfObjects(PostWithAttachmentAndComments.class)
                .withQuery(Query.builder().table(NewsTable.TABLE).build())
                .prepare()//TODO executeAsBlocking
                .executeAsBlocking();*//*
                *//*RawQuery.builder().query("SELECT * FROM " + NewsTable.TABLE
                + " JOIN " + AttachNewsTable.TABLE
                + " ON " + NewsTable.TABLE + "." + NewsTable.COLUMN_ID
                + " = " + AttachNewsTable.TABLE + "." + AttachNewsTable.COLUMN_NEWS_ID).build()*//*
    }

    @Override
    public void saveData(List<News> newses, List<CommentWithAttachments> comments, List<AttachmentNews> newsAttachments) {
//        storIOSQLite.executeSQL().withQuery(RawQuery.builder().query("DROP postsDatabase").build()).prepare().executeAsBlocking();
    }

    @Override
    public Observable<PutResults<PostWithAttachmentAndComments>> save(List<PostWithAttachmentAndComments> newsPosts) {
        return storIOSQLite.put()
                .objects(newsPosts)
                .prepare()
                .createObservable();
*//*                .subscribe(new Observer<PutResults<PostWithAttachmentAndComments>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(final Throwable e) {
                        e.printStackTrace();
                        e.toString();
                    }

                    @Override
                    public void onNext(final PutResults<PostWithAttachmentAndComments> postWithAttachmentAndCommentsPutResults) {
                        postWithAttachmentAndCommentsPutResults.toString();
                    }
                });*//*
    }*/
}
