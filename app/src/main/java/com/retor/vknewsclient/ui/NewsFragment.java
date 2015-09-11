package com.retor.vknewsclient.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.retor.vknewsclient.R;
import com.retor.vknewsclient.adapters.BaseAdapter;
import com.retor.vknewsclient.adapters.NewsAdapter;
import com.retor.vknewsclient.application.BaseApplication;
import com.retor.vknewsclient.db.model.NewsWithAttachments;
import com.retor.vknewsclient.di.modules.DaggerPresentersComponent;
import com.retor.vknewsclient.di.modules.InteractorsModule;
import com.retor.vknewsclient.di.modules.PresenterModules;
import com.retor.vknewsclient.presenter.ListPresenter;
import com.retor.vknewsclient.view.ListView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import icepick.Icepick;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewsFragment extends Fragment implements ListView<List<NewsWithAttachments>> {
    @Inject
    protected ListPresenter<List<NewsWithAttachments>> presenter;
    @Inject
    protected BaseAdapter<NewsAdapter.NewsHolder, NewsWithAttachments> adapter;
    @Bind(R.id.recycler)
    RecyclerView recyclerView;
    @Bind(R.id.swipe)
    SwipeRefreshLayout swiper;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        DaggerPresentersComponent.builder()
                .applicationComponent(((BaseApplication) getActivity().getApplication()).getComponent())
                .interactorsModule(new InteractorsModule())
                .presenterModules(new PresenterModules())
                .build().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Icepick.restoreInstanceState(this, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);
        presenter.setView(this);
        presenter.onCreate(this, savedInstanceState);
/*        if (adapter.getList() == null || adapter.getList().isEmpty())
            presenter.onRefresh();*/
        initRecycler();
        initSwipeRefresh();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ButterKnife.bind(this, getView());
        presenter.onResume(this);
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public void onViewStateRestored(final Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        presenter.onRestoreState(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        ButterKnife.unbind(this);
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
                        Log.d("Fragment", ((NewsWithAttachments) adapter.getList().get(childAdapterPosition)).getNews().toString());
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

    private void swipeActivate(RecyclerView recyclerView) {
        int topRowVerticalPosition =
                (recyclerView == null || recyclerView.getChildCount() == 0) ?
                        0 : recyclerView.getChildAt(0).getTop();
        swiper.setEnabled(topRowVerticalPosition >= 0);
    }

    @Override
    public void setData(final List<NewsWithAttachments> items) {
        adapter.clear();
        adapter.addPosts(items);
        recyclerView.invalidate();
    }

    @Override
    public void addData(final List<NewsWithAttachments> items) {
        adapter.addPosts(items);
    }

    @Override
    public void showDetailDialog(final DialogFragment dialogFragment) {
        dialogFragment.show(getActivity().getSupportFragmentManager(), "news_detail");
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
    public void onError(final Throwable throwable) {
        new AlertDialog.Builder(this.getActivity()).setMessage(throwable.getLocalizedMessage()).create().show();
    }
}
