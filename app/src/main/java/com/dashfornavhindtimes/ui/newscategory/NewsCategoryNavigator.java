package com.dashfornavhindtimes.ui.newscategory;

import com.dashfornavhindtimes.data.model.Post.Post;
import com.dashfornavhindtimes.ui.main.MainContract;

import javax.inject.Inject;

/**
 * Created by Lucas on 17/01/2017.
 */

public class NewsCategoryNavigator implements NewsCategoryContract.Navigator {

    private MainContract.Navigator mainNavigator;

    @Inject
    public NewsCategoryNavigator(MainContract.Navigator mainNavigator) {
        this.mainNavigator = mainNavigator;
    }


    @Override
    public void goToPostDetails(Post post) {
        mainNavigator.goToPostDetails(post);

    }
}
