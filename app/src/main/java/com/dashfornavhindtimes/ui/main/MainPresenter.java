package com.dashfornavhindtimes.ui.main;

import javax.inject.Inject;

/**
 * Created by Lucas on 02/01/2017.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private MainContract.Navigator navigator;

    @Inject
    public MainPresenter(MainContract.Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void attachView(MainContract.View view) {
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
    public void defaultCategory() {

        navigator.goToDefaultNewPosts();
    }

    @Override
    public void clickedCategory(int categoryNumber, String categoryName) {
        navigator.goToCategory(categoryNumber, categoryName);
    }

    @Override
    public void clickedAppSetting() {
        navigator.goToAppSetting();

    }
}
