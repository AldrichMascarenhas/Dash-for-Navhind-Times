package com.dashfornavhindtimes.injection.newsitemdetail;

import com.dashfornavhindtimes.injection.FragmentScope;
import com.dashfornavhindtimes.ui.newsitemdetail.NewsItemDetailContract;
import com.dashfornavhindtimes.ui.newsitemdetail.NewsItemDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Aldrich on 20-Aug-17.
 */
@Module
public class NewsItemDetailModule {

    public NewsItemDetailModule() {
    }

    @Provides
    @FragmentScope
    NewsItemDetailContract.Presenter provideNewsCategoryPresenter(NewsItemDetailPresenter presenter) {
        return presenter;
    }

    //TODO: how?
}
