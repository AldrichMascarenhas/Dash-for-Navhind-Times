package com.dashfornavhindtimes.injection.newsitemdetail;

import com.dashfornavhindtimes.injection.FragmentScope;
import com.dashfornavhindtimes.ui.newsitemdetail.NewsItemDetailFragment;

import dagger.Subcomponent;


@FragmentScope
@Subcomponent(
        modules = {NewsItemDetailModule.class}
)
public interface NewsItemDetailComponent {

    void inject(NewsItemDetailFragment fragment);
}
