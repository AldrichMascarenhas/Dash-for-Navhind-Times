package com.dashfornavhindtimes.ui.newscategory;

import android.content.Context;

import com.dashfornavhindtimes.data.api.NavhindTimesService;
import com.dashfornavhindtimes.data.model.Post.Post;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Lucas on 04/01/2017.
 */

public class NewsCategoryPresenter implements NewsCategoryContract.Presenter {

    private Context context;
    private NewsCategoryContract.View view;
    private NewsCategoryContract.Navigator navigator;
    private NavhindTimesService navhindTimesService;

    @Inject
    public NewsCategoryPresenter(Context context, NewsCategoryContract.Navigator navigator, NavhindTimesService navhindTimesService) {
        this.navigator = navigator;
        this.context = context; //TODO: remove context
        this.navhindTimesService = navhindTimesService;

    }

    @Override
    public void attachView(NewsCategoryContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public boolean isViewAttached() {
        return this.view != null;
    }

    ////////////

    @Override
    public void onInitialListRequested(int CATEGORY_NUMBER, int PAGE_NO) {
        getDatafromAPI(CATEGORY_NUMBER, PAGE_NO);

    }

    @Override
    public void onListEndReached(int CATEGORY_NUMBER, int PAGE_NO) {
        getDatafromAPI(CATEGORY_NUMBER, PAGE_NO);

    }

    @Override
    public void onClickPost(Post post) {
        navigator.goToPostDetails(post);

    }


    public void getDatafromAPI(final int CATEGORY_NUMBER, final int PAGE_NO) {
        if (!isViewAttached()) {
            return;
        }

        view.showProgress();


        navhindTimesService.getPostsByCategory(CATEGORY_NUMBER, PAGE_NO).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    // tasks available
                    if (!isViewAttached()) {
                        return;
                    }
                    view.hideProgress();

                    if (response.body() == null) {
                        view.showError();

                    } else {
                        view.showListofPosts(response.body());

                    }
                } else {
                    // error response, no access to resource?
                    view.showError();
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                if (!isViewAttached()) {
                    return;
                }
                view.onFailure();


            }
        });


    }

}
