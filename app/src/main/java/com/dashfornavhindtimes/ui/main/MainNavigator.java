package com.dashfornavhindtimes.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.dashfornavhindtimes.R;
import com.dashfornavhindtimes.data.model.Post.Post;
import com.dashfornavhindtimes.ui.about.AboutFragment;
import com.dashfornavhindtimes.ui.newscategory.NewsCategoryFragment;
import com.dashfornavhindtimes.ui.newsitemdetail.NewsItemDetailFragment;

import javax.inject.Inject;

/**
 * Created by Lucas on 02/01/2017.
 */

public class MainNavigator implements MainContract.Navigator {

    private static final String TAG_DETAILS = "tag_details";
    private static final String TAG_MASTER = "tag_master";
    private MainActivity mainActivity;

    public enum State {
        SINGLE_COLUMN_MASTER, SINGLE_COLUMN_DETAILS, TWO_COLUMNS_EMPTY, TWO_COLUMNS_WITH_DETAILS
    }

    @Inject
    public MainNavigator(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    private boolean clearDetails() {
        final Fragment details = mainActivity.getSupportFragmentManager().findFragmentByTag(TAG_DETAILS);
        if (details != null) {
            mainActivity.getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .remove(details)
                    .commitNow();
            return true;
        }
        return false;
    }


    @Override
    public void goToAppSetting() {
        clearDetails();
        mainActivity.getCustomAppBar().setState(State.SINGLE_COLUMN_MASTER);
        mainActivity.getContainersLayout().setState(State.SINGLE_COLUMN_MASTER);
        AboutFragment master = new AboutFragment();
        mainActivity.getSupportFragmentManager().beginTransaction().replace(com.dashfornavhindtimes.R.id.activity_main__frame_master, master, TAG_MASTER).commitNow();
    }

    @Override
    public void goToCategory(int categoryNumber, String categoryName) {
        clearDetails();
        mainActivity.getCustomAppBar().setState(State.TWO_COLUMNS_EMPTY);
        mainActivity.getContainersLayout().setState(State.TWO_COLUMNS_EMPTY);
        NewsCategoryFragment master = NewsCategoryFragment.newInstance(categoryNumber, categoryName);
        mainActivity.getSupportFragmentManager().beginTransaction().replace(com.dashfornavhindtimes.R.id.activity_main__frame_master, master, TAG_MASTER).commitNow();
    }

    @Override
    public void goToDefaultNewPosts() {
        clearDetails();
        mainActivity.getCustomAppBar().setState(State.TWO_COLUMNS_EMPTY);
        mainActivity.getContainersLayout().setState(State.TWO_COLUMNS_EMPTY);
        NewsCategoryFragment master = NewsCategoryFragment.newInstance(1, mainActivity.getString(R.string.menu_new_posts));
        mainActivity.getSupportFragmentManager().beginTransaction().replace(com.dashfornavhindtimes.R.id.activity_main__frame_master, master, TAG_MASTER).commitNow();
    }



    @Override
    public void goToPostDetails(Post post) {
        mainActivity.getCustomAppBar().setState(State.TWO_COLUMNS_WITH_DETAILS);
        mainActivity.getContainersLayout().setState(State.TWO_COLUMNS_WITH_DETAILS);
        NewsItemDetailFragment fragment = NewsItemDetailFragment.newInstance(post);
        mainActivity.getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(com.dashfornavhindtimes.R.id.activity_main__frame_details, fragment, TAG_DETAILS)
                .commitNow();
    }

    @Override
    public boolean onBackPressed() {
        State state = mainActivity.getContainersLayout().getState();
        if (state.equals(State.TWO_COLUMNS_WITH_DETAILS) && !mainActivity.getContainersLayout().hasTwoColumns()) {
            if (clearDetails()) {
                mainActivity.getContainersLayout().setState(State.TWO_COLUMNS_EMPTY);
                return true;
            }
        }
        return false;
    }
}
