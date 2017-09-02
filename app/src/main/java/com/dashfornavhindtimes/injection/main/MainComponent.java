package com.dashfornavhindtimes.injection.main;

import com.dashfornavhindtimes.injection.newscategory.NewsCategoryModule;
import com.dashfornavhindtimes.injection.newsitemdetail.NewsItemDetailComponent;
import com.dashfornavhindtimes.injection.newsitemdetail.NewsItemDetailModule;
import com.dashfornavhindtimes.ui.main.MainActivity;
import com.dashfornavhindtimes.injection.ActivityScope;
import com.dashfornavhindtimes.injection.newscategory.NewsCategoryComponent;

import dagger.Subcomponent;

/**
 * Created by Lucas on 12/06/16.
 */
@ActivityScope
@Subcomponent(
        modules = {MainModule.class}
)
public interface MainComponent {

    void inject(MainActivity activity);

    NewsCategoryComponent plus(NewsCategoryModule newsCategoryModule);

    NewsItemDetailComponent plus(NewsItemDetailModule newsItemDetailModule);


}
