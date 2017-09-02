package com.dashfornavhindtimes.ui.newsitemdetail;

import com.dashfornavhindtimes.data.api.NavhindTimesService;
import com.dashfornavhindtimes.data.model.Media.Media;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Aldrich on 21-Aug-17.
 */

public class NewsItemDetailPresenter implements NewsItemDetailContract.Presenter {

    private NewsItemDetailContract.View view;
    private NavhindTimesService navhindTimesService;

    @Inject
    public NewsItemDetailPresenter(NavhindTimesService navhindTimesService) {
        this.navhindTimesService = navhindTimesService;
    }

    @Override
    public void attachView(NewsItemDetailContract.View view) {
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

    @Override
    public void loadImage(int mediaId) {

        navhindTimesService.getPostMediaById(mediaId).enqueue(new Callback<Media>() {
            @Override
            public void onResponse(Call<Media> call, Response<Media> response) {
                if (response.isSuccessful()) {
                    // tasks available
                    if (!isViewAttached()) {
                        return;
                    }

                    if (response.body() == null) {
                        view.showImageFailure();

                    } else {
                        if (response.body().getMediaDetails().getSizes().getMediumLarge() != null) {

                            view.showImage(response.body().getMediaDetails().getSizes().getMediumLarge().getSourceUrl());

                        } else if (response.body().getMediaDetails().getSizes().getMedium() != null) {
                            view.showImage(response.body().getMediaDetails().getSizes().getMedium().getSourceUrl());

                        } else {
                            view.showImage(response.body().getMediaDetails().getSizes().getLarge().getSourceUrl());

                        }


                    }
                } else {
                    // error response, no access to resource?
                    view.showImageFailure();
                }
            }

            @Override
            public void onFailure(Call<Media> call, Throwable t) {
                if (!isViewAttached()) {
                    return;
                }
                view.showImageFailure();
            }
        });

    }
}
