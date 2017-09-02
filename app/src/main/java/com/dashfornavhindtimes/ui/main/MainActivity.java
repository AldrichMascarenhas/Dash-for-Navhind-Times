package com.dashfornavhindtimes.ui.main;

import android.os.Bundle;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.dashfornavhindtimes.R;
import com.dashfornavhindtimes.app.DashApplication;
import com.dashfornavhindtimes.injection.app.ApplicationComponent;
import com.dashfornavhindtimes.injection.main.MainComponent;
import com.dashfornavhindtimes.injection.main.MainModule;
import com.dashfornavhindtimes.ui.util.BaseActivity;
import com.dashfornavhindtimes.ui.widget.ContainersLayout;
import com.dashfornavhindtimes.ui.widget.CustomAppBar;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainContract.View {

    @Inject
    MainPresenter presenter;
    @Inject
    MainContract.Navigator navigator;


    //save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;

    @BindView(R.id.activity_main__custom_appbar)
    CustomAppBar customAppBar;
    @BindView(R.id.activity_main__containers_layout)
    ContainersLayout containersLayout;

    private MainComponent mainComponent;

    //Firebase Analytics
    private FirebaseAnalytics firebaseAnalytics;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        customAppBar.setOnNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.openDrawer();

            }
        });

        presenter.attachView(this);
        if (savedInstanceState == null) {
            presenter.defaultCategory();
        }

        firebaseAnalytics = FirebaseAnalytics.getInstance(this);



        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(false)
                .withHeaderBackground(R.drawable.header)
                .withSavedInstance(savedInstanceState)
                .build();

        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .withActionBarDrawerToggle(false)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.menu_new_posts).withIcon(FontAwesome.Icon.faw_fire).withIdentifier(0),

                        new SectionDrawerItem().withName(R.string.drawer_item_section_header),

                        new SecondaryDrawerItem().withName(R.string.menu_goa_news).withIcon(FontAwesome.Icon.faw_tag).withIdentifier(1),
                        new SecondaryDrawerItem().withName(R.string.menu_national_news).withIcon(FontAwesome.Icon.faw_tag).withIdentifier(2),
                        new SecondaryDrawerItem().withName(R.string.menu_world_news).withIcon(FontAwesome.Icon.faw_tag).withIdentifier(3),
                        new SecondaryDrawerItem().withName(R.string.menu_business).withIcon(FontAwesome.Icon.faw_tag).withIdentifier(6),
                        new SecondaryDrawerItem().withName(R.string.menu_breaking_news).withIcon(FontAwesome.Icon.faw_tag).withIdentifier(21),
                        new SecondaryDrawerItem().withName(R.string.menu_zest).withIcon(FontAwesome.Icon.faw_tag).withIdentifier(8),
                        new SecondaryDrawerItem().withName(R.string.menu_panorama).withIcon(FontAwesome.Icon.faw_tag).withIdentifier(9),
                        new SecondaryDrawerItem().withName(R.string.menu_buzz).withIcon(FontAwesome.Icon.faw_tag).withIdentifier(12),
                        new SecondaryDrawerItem().withName(R.string.menu_supplements).withIcon(FontAwesome.Icon.faw_tag).withIdentifier(7),
                        new SecondaryDrawerItem().withName(R.string.menu_b_and_c).withIcon(FontAwesome.Icon.faw_tag).withIdentifier(11),
                        new SecondaryDrawerItem().withName(R.string.menu_editorial).withIcon(FontAwesome.Icon.faw_tag).withIdentifier(25),
                        new SecondaryDrawerItem().withName(R.string.menu_kurio_city).withIcon(FontAwesome.Icon.faw_tag).withIdentifier(10),
                        new SecondaryDrawerItem().withName(R.string.menu_commentary).withIcon(FontAwesome.Icon.faw_tag).withIdentifier(4),
                        new SecondaryDrawerItem().withName(R.string.menu_video_news).withIcon(FontAwesome.Icon.faw_tag).withIdentifier(16),

                        new SectionDrawerItem().withName("Settings"),

                        new SecondaryDrawerItem().withName("About").withIcon(FontAwesome.Icon.faw_cog).withIdentifier(999)




                        )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        if(drawerItem != null){
                            String categoryName = "";
                            if (drawerItem instanceof Nameable) {
                                categoryName = (((Nameable) drawerItem).getName().getText(MainActivity.this));
                            }

                            Bundle bundle = new Bundle();
                            bundle.putString("NEWS_CATEGORY_NAME", categoryName);
                            bundle.putInt("NEWS_CATEGORY_ID", (int)drawerItem.getIdentifier());
                            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "CATEGORY_CLICK");
                            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


                            switch ((int)drawerItem.getIdentifier()){
                                case 0:
                                    presenter.clickedCategory(1, categoryName);
                                case 1:
                                    presenter.clickedCategory(1, categoryName);
                                    break;
                                case 2:
                                    presenter.clickedCategory(2, categoryName);
                                    break;
                                case 3:
                                    presenter.clickedCategory(3, categoryName);
                                    break;
                                case 4:
                                    presenter.clickedCategory(4, categoryName);
                                    break;
                                case 6:
                                    presenter.clickedCategory(6, categoryName);
                                    break;
                                case 7:
                                    presenter.clickedCategory(7, categoryName);
                                    break;
                                case 8:
                                    presenter.clickedCategory(8, categoryName);
                                    break;
                                case 9:
                                    presenter.clickedCategory(9, categoryName);
                                    break;
                                case 10:
                                    presenter.clickedCategory(10, categoryName);
                                    break;
                                case 11:
                                    presenter.clickedCategory(11, categoryName);
                                    break;
                                case 12:
                                    presenter.clickedCategory(12, categoryName);
                                    break;
                                case 16:
                                    presenter.clickedCategory(16, categoryName);
                                    break;
                                case 21:
                                    presenter.clickedCategory(21, categoryName);
                                    break;
                                case 25:
                                    presenter.clickedCategory(25, categoryName);
                                    break;
                                case 999:
                                    presenter.clickedAppSetting();

                            }
                        }

                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();






    }

    @Override
    protected void setupActivityComponent(ApplicationComponent applicationComponent) {

        mainComponent = DashApplication.getAppComponent(this).plus(new MainModule(this));
        mainComponent.inject(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void onBackPressed() {
        if (!navigator.onBackPressed()) {
            //handle the back press :D close the drawer first and if the drawer is closed close the activity
            if (result != null && result.isDrawerOpen()) {
                result.closeDrawer();
            } else {
                super.onBackPressed();
            }


        }




    }




    public CustomAppBar getCustomAppBar() {
        return customAppBar;
    }

    public ContainersLayout getContainersLayout() {
        return containersLayout;
    }

    public MainContract.Navigator getNavigator() {
        return navigator;
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }

}
