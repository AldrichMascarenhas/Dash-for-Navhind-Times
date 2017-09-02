package com.dashfornavhindtimes.ui.newsitemdetail;

import com.dashfornavhindtimes.ui.util.BasePresenter;
import com.dashfornavhindtimes.ui.util.BaseView;

/**
 * Created by Aldrich on 21-Aug-17.
 */

public interface NewsItemDetailContract {


    interface View extends BaseView {


        void showImage(String sourceUrl);

        void showImageFailure();

    }

    interface Presenter extends BasePresenter<NewsItemDetailContract.View> {

        void loadImage(int mediaId);

    }
}
