package com.retor.vknewsclient.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hannesdorfmann.fragmentargs.FragmentArgs;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.retor.vknewsclient.R;
import com.retor.vknewsclient.adapters.BaseAdapter;
import com.retor.vknewsclient.adapters.CommentsAdapter;
import com.retor.vknewsclient.application.BaseApplication;
import com.retor.vknewsclient.db.model.CommentWithAttachments;
import com.retor.vknewsclient.db.model.NewsWithAttachments;
import com.retor.vknewsclient.di.modules.DaggerPresentersComponent;
import com.retor.vknewsclient.di.modules.InteractorsModule;
import com.retor.vknewsclient.di.modules.PresenterModules;
import com.retor.vknewsclient.pictures.PicLoader;
import com.retor.vknewsclient.presenter.ListPresenter;
import com.retor.vknewsclient.view.ListView;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import icepick.Icepick;

/**
 * Created by retor on 11.09.2015.
 */
public class NewsDetailsDialog extends DialogFragment implements ListView<List<CommentWithAttachments>> {
    @Inject
    protected PicLoader loader;
    @Inject
    protected ListPresenter<List<CommentWithAttachments>> presenter;
    @Inject
    protected BaseAdapter<CommentsAdapter.CommentsHolder, CommentWithAttachments> adapter;
    @Arg
    NewsWithAttachments news;
    @Bind(R.id.news_detail_photo)
    ImageView photo;
    @Bind(R.id.news_detail_author)
    TextView author;
    @Bind(R.id.news_detail_text)
    TextView text;
    @Bind(R.id.news_detail_content)
    LinearLayout contentLayout;
    @Bind(R.id.news_detail_footer_date)
    TextView date;
    @Bind(R.id.news_detail_footer_likes)
    TextView likes;
    @Bind(R.id.news_comments_recycler)
    RecyclerView recyclerView;
    @Bind(R.id.news_comments_swipe)
    SwipeRefreshLayout swiper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO inject here
        setRetainInstance(true);
        setCancelable(true);
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        DaggerPresentersComponent.builder()
                .applicationComponent(((BaseApplication) getActivity().getApplication()).getComponent())
                .interactorsModule(new InteractorsModule())
                .presenterModules(new PresenterModules())
                .build().inject(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this,outState);
        presenter.onSaveState(outState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentArgs.inject(this);
        if (savedInstanceState==null) {
            savedInstanceState = new Bundle();
            savedInstanceState.putParcelable("news", news);
        }else {
            savedInstanceState.putParcelable("news", news);
        }

        View view = inflater.inflate(R.layout.dialog_news, container, false);
        ButterKnife.bind(this, view);
        presenter.setView(this);
        presenter.onCreate(this, savedInstanceState);
        initRecycler();
        initSwipeRefresh();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.setView(this);
        author.setText(news.getNews().getAuthor());
        loader.loadPic(news.getNews().getAuthor_pic(), photo);
        text.setText(Html.fromHtml(news.getNews().getText()));
        Linkify.addLinks(text, Linkify.ALL);
        if (news.getAttachmentList()!=null && !news.getAttachmentList().isEmpty()){
            ImageView pic = new ImageView(view.getContext());
            loader.loadPic(news.getAttachmentList().get(0).getUrl(), pic);
            contentLayout.addView(pic, contentLayout.getLayoutParams());
        }
        contentLayout.setScrollContainer(true);
        date.setText(DateFormat.getInstance().format(new Date(news.getNews().getDate() * 1000)));
        likes.setText(String.valueOf(news.getNews().getLikes_count()));
        view.setScrollContainer(true);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
        presenter.onRestoreState(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.onResume(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance())
            getDialog().setDismissMessage(null);
        super.onDestroyView();
    }

    @Override
    public void setData(List<CommentWithAttachments> items) {
        if (items.isEmpty())
            swiper.setVisibility(View.GONE);
        adapter.clear();
        adapter.addPosts(items);
        recyclerView.invalidate();
    }

    @Override
    public void addData(List<CommentWithAttachments> items) {
        adapter.addPosts(items);
    }

    @Override
    public void showDetailDialog(final DialogFragment dialogFragment) {

    }

    @Override
    public void showProgress() {
        swiper.setSize(SwipeRefreshLayout.LARGE);
        swiper.setRefreshing(true);
    }

    @Override
    public void closeProrgess() {
        if (swiper != null) {
            swiper.setSize(SwipeRefreshLayout.DEFAULT);
            swiper.setRefreshing(false);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        new AlertDialog.Builder(this.getActivity()).setMessage(throwable.getLocalizedMessage()).create().show();
    }

    private void initRecycler() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        RecyclerView.ItemAnimator im = new DefaultItemAnimator();
        im.setAddDuration(1000);
        recyclerView.setItemAnimator(im);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            FloatingActionButton floatingActionButton = ButterKnife.findById(getActivity(), R.id.fab);

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && floatingActionButton.isShown()) {
                    floatingActionButton.hide();
                } else {
                    floatingActionButton.show();
                }
                int visibleCount = linearLayoutManager.getChildCount();
                int totalCount = linearLayoutManager.getItemCount();
                int firstVisible = linearLayoutManager.findFirstVisibleItemPosition();
                int visibleThreshold = 5;
                if ((totalCount - visibleCount) <= (firstVisible + visibleThreshold)) {
                    presenter.onEndScrolled();
                }
            }
        });
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            int prevAction;

            @Override
            public boolean onInterceptTouchEvent(final RecyclerView rv, final MotionEvent e) {
                int action = e.getAction();
                if (action == MotionEvent.ACTION_UP && prevAction == MotionEvent.ACTION_DOWN) {
                    View childViewUnder = rv.findChildViewUnder(e.getX(), e.getY());
                    int childAdapterPosition = rv.getChildAdapterPosition(childViewUnder);
                    if (adapter.getList() != null) {
//                        Log.d("Dialog", ((CommentWithAttachments) adapter.getList().get(childAdapterPosition)).getComment().toString());
                        presenter.onClick(childAdapterPosition);
                    }
                }
                prevAction = e.getAction();
                return false;
            }

            @Override
            public void onTouchEvent(final RecyclerView rv, final MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(final boolean disallowIntercept) {

            }
        });
    }

    private void initSwipeRefresh() {
        swiper.setVerticalScrollBarEnabled(true);
        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swiper.setRefreshing(false);
                presenter.onRefresh();
            }
        });
    }
}
