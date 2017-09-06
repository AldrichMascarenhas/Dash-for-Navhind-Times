package com.dashfornavhindtimes.ui.newscategory;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dashfornavhindtimes.R;
import com.dashfornavhindtimes.data.model.Post.Post;
import com.dashfornavhindtimes.injection.newscategory.NewsCategoryModule;
import com.dashfornavhindtimes.ui.main.MainActivity;
import com.dashfornavhindtimes.ui.util.EndlessRecyclerOnScrollListener;
import com.dashfornavhindtimes.ui.widget.CustomAppBar;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import tr.xip.errorview.ErrorView;

/**
 * Created by Lucas on 04/01/2017.
 */

public class NewsCategoryFragment extends Fragment implements NewsCategoryContract.View, NewsCategoryListAdapter.InteractionListener {

    // region Constants
    public static final String CATEGORY_NUMBER = "CATEGORY_NUMBER";
    public static final String CATEGORY_NAME = "CATEGORY_NAME";
    public static final String PAGE_NO = "PAGE_NO";

    @BindView(R.id.swipe_to_refresh_fragment_news_category)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.recyclerview_fragment_news_category_listofposts)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar_layout)
    ProgressBar progressBar;
    @BindView(R.id.error_view)
    ErrorView errorView;

    @Inject
    NewsCategoryContract.Presenter presenter;
    @Inject
    NewsCategoryListAdapter newsCategoryListAdapter;
    @Inject
    LinearLayoutManager linearLayoutManager;
    private EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;

    private FirebaseAnalytics firebaseAnalytics;

    public NewsCategoryFragment() {
        super();
    }

    public static NewsCategoryFragment newInstance(int categoryNumber, String categoryName) {

        Bundle bundle = new Bundle();
        bundle.putInt(CATEGORY_NUMBER, categoryNumber);
        bundle.putInt(PAGE_NO, 1);
        bundle.putString(CATEGORY_NAME, categoryName);

        NewsCategoryFragment fragment = new NewsCategoryFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_category, container, false);
        ButterKnife.bind(this, view);

        inject();


        presenter.attachView(this);
        initViews();

        newsCategoryListAdapter.setListInteractionListener(this);

        if (newsCategoryListAdapter.isEmpty()) {
            presenter.onInitialListRequested(getArguments().getInt(CATEGORY_NUMBER), 1);
        }

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();

        //On going to WIFI settings and enabling, Check is Adapter is empty and call again.
        if (newsCategoryListAdapter.isEmpty()) {
            presenter.onInitialListRequested(getArguments().getInt(CATEGORY_NUMBER), 1);
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupToolbar();
    }

    private void initViews() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setMotionEventSplittingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(newsCategoryListAdapter);

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                if (newsCategoryListAdapter.addLoadingView()) {
                    presenter.onListEndReached(getArguments().getInt(CATEGORY_NUMBER), current_page);
                }
            }
        };

        recyclerView.addOnScrollListener(endlessRecyclerOnScrollListener);

        swipeRefresh.setProgressBackgroundColorSchemeResource(R.color.colorPrimaryDark);
        swipeRefresh.setColorSchemeResources(R.color.colorAccent);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                endlessRecyclerOnScrollListener.reset();
                newsCategoryListAdapter.removeAll();
                presenter.onInitialListRequested(getArguments().getInt(CATEGORY_NUMBER), 1);
            }
        });

    }


    private void setupToolbar() {
        CustomAppBar appBar = ((MainActivity) getActivity()).getCustomAppBar();
        appBar.setTitle(getArguments().getString(CATEGORY_NAME));
        appBar.setMenuRes(com.dashfornavhindtimes.R.menu.news_category_general, com.dashfornavhindtimes.R.menu.news_category_specific, com.dashfornavhindtimes.R.menu.news_category_merged);
    }


    private void inject() {
        ((MainActivity) getActivity())
                .getMainComponent()
                .plus(new NewsCategoryModule(getArguments().getInt(CATEGORY_NUMBER), getArguments().getString(CATEGORY_NAME))) //PASS TITLE HERE
                .inject(this);
    }


    @Override
    public void onListClick(Post post) {

        Bundle bundle = new Bundle();
        bundle.putString("NEWS_CATEGORY_NAME", getArguments().getString(CATEGORY_NAME));
        bundle.putInt("NEWS_CATEGORY_ID", getArguments().getInt(CATEGORY_NUMBER));
        bundle.putString("ARTICLE_TITLE", post.getTitle().getRendered());
        bundle.putInt("ARTICLE_ID", post.getId());
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "ARTICLE_CLICK");
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        presenter.onClickPost(post);


    }

    @Override
    public void showListofPosts(List<Post> postList) {
        newsCategoryListAdapter.addItems(postList);
        newsCategoryListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        if (newsCategoryListAdapter.isEmpty() && !swipeRefresh.isRefreshing()) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        swipeRefresh.setRefreshing(false);
        progressBar.setVisibility(View.GONE);
        newsCategoryListAdapter.removeLoadingView();
    }

    @Override
    public void showError() {
        progressBar.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        errorView.setTitle(R.string.error_view_title_error);
        errorView.setSubtitle(R.string.error_view_subtitle_error);
        errorView.setRetryText(R.string.error_view_retry_error);
        errorView.setRetryListener(new ErrorView.RetryListener() {
            @Override
            public void onRetry() {
                //Try again
                presenter.onInitialListRequested(getArguments().getInt(CATEGORY_NUMBER), 1);
                errorView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onFailure() {
        progressBar.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        errorView.setTitle(R.string.error_view_title_failure);
        errorView.setSubtitle(R.string.error_view_subtitle_failure);
        errorView.setRetryText(R.string.error_view_retry_failure);
        errorView.setRetryListener(new ErrorView.RetryListener() {
            @Override
            public void onRetry() {
                //Intent to check settings
                Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                startActivity(intent);
                errorView.setVisibility(View.GONE);

            }
        });
    }



    /////
    @Override
    public void onDestroyView() {
        recyclerView.setAdapter(null);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

}
