package com.dashfornavhindtimes.ui.newscategory;

import com.dashfornavhindtimes.data.model.Post.Post;
import com.dashfornavhindtimes.ui.util.BaseNavigator;
import com.dashfornavhindtimes.ui.util.BasePresenter;
import com.dashfornavhindtimes.ui.util.BaseView;

import java.util.List;

/**
 * Created by Lucas on 04/01/2017.
 */

public interface NewsCategoryContract {

    interface Navigator extends BaseNavigator {

        void goToPostDetails(Post post);
    }

    interface View extends BaseView {

        void showListofPosts(List<Post> postList);

        void showProgress();

        void hideProgress();

        void showError();

        void onFailure();


    }

    interface Presenter extends BasePresenter<NewsCategoryContract.View> {

        void onInitialListRequested(int categoryId, int currentPage);

        void onListEndReached(int categoryId, int currentPage);

        void onClickPost(Post post);

    }
}
