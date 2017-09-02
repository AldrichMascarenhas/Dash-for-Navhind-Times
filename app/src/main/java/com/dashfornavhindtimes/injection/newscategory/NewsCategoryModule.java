package com.dashfornavhindtimes.injection.newscategory;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.dashfornavhindtimes.injection.FragmentScope;
;
import com.dashfornavhindtimes.ui.main.MainActivity;
import com.dashfornavhindtimes.ui.newscategory.NewsCategoryContract;
import com.dashfornavhindtimes.ui.newscategory.NewsCategoryListAdapter;
import com.dashfornavhindtimes.ui.newscategory.NewsCategoryNavigator;
import com.dashfornavhindtimes.ui.newscategory.NewsCategoryPresenter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lucas on 04/01/2017.
 */
@Module
public class NewsCategoryModule {
    private int CATEGORY_NUMBER;
    private String CATEGORY_NAME;

    public NewsCategoryModule(int CATEGORY_NUMBER, String CATEGORY_NAME) {
        this.CATEGORY_NUMBER = CATEGORY_NUMBER;
        this.CATEGORY_NAME = CATEGORY_NAME;
    }

    @Provides
    NewsCategoryContract.Navigator provideNewsCategoryNavigator(NewsCategoryNavigator navigator) {
        return navigator;
    }

    @Provides
    @FragmentScope
    NewsCategoryContract.Presenter provideNewsCategoryPresenter(NewsCategoryPresenter presenter) {
        return presenter;
    }

    @Provides
    @FragmentScope
    @Named("CATEGORY_NUMBER")
    int provideCATEGORY_NUMBER() {
        return CATEGORY_NUMBER;
    }

    @Provides
    @FragmentScope
    @Named("CATEGORY_NAME")
    String provideCATEGORY_NAME() {
        return CATEGORY_NAME;
    }


    @Provides
    @FragmentScope
    NewsCategoryListAdapter provideNewsCategoryListAdapter(Context context, @Named("CATEGORY_NUMBER") int CATEGORY_NUMBER, @Named("CATEGORY_NAME") String CATEGORY_NAME) {
        return new NewsCategoryListAdapter(context, CATEGORY_NUMBER, CATEGORY_NAME);
    }

    @Provides
    @FragmentScope
    LinearLayoutManager provideLinearLayoutManager(MainActivity mainActivity) {
        return new LinearLayoutManager(mainActivity);
    }


}
