package com.dashfornavhindtimes.ui.util;

/**
 * Created by Lucas on 12/06/16.
 */
public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();

    boolean isViewAttached();


//    void checkViewAttached();
//
//    class MvpViewNotAttachedException extends RuntimeException {
//        public MvpViewNotAttachedException() {
//            super("Please call Presenter.attachView(MvpView) before" +
//                    " requesting data to the Presenter");
//        }
//    }

}

