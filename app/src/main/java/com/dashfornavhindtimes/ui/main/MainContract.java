package com.dashfornavhindtimes.ui.main;


import com.dashfornavhindtimes.data.model.Post.Post;
import com.dashfornavhindtimes.ui.util.BasePresenter;
import com.dashfornavhindtimes.ui.util.BaseView;
import com.dashfornavhindtimes.ui.util.BaseNavigator;

/**
 * Created by Lucas on 12/06/16.
 */
public interface MainContract {

    interface Navigator extends BaseNavigator {

        void goToDefaultNewPosts();

        void goToPostDetails(Post post);

        void goToCategory(int categoryNumber, String categoryName);

        boolean onBackPressed();

        void goToAppSetting();
    }

    interface View extends BaseView {

//        void closeDrawer();
//
//        void openDrawer();


    }

    interface Presenter extends BasePresenter<View> {

        void defaultCategory();

        void clickedCategory(int categoryNumber, String categoryName);

        void clickedAppSetting();


    }
}
