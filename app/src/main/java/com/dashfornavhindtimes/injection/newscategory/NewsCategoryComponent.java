package com.dashfornavhindtimes.injection.newscategory;

import com.dashfornavhindtimes.ui.newscategory.NewsCategoryFragment;
import com.dashfornavhindtimes.injection.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Lucas on 04/01/2017.
 */
@FragmentScope
@Subcomponent(
        modules = {NewsCategoryModule.class}
)
public interface NewsCategoryComponent {

    void inject(NewsCategoryFragment fragment);


}
