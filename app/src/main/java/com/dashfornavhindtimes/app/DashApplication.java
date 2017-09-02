package com.dashfornavhindtimes.app;

import android.app.Application;
import android.content.Context;

import com.dashfornavhindtimes.injection.app.ApplicationComponent;
import com.dashfornavhindtimes.data.api.NTApiModule;
import com.dashfornavhindtimes.injection.app.ApplicationModule;
import com.dashfornavhindtimes.injection.app.DaggerApplicationComponent;


/**
 * Created by Lucas on 02/01/2017.
 */

public class DashApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .nTApiModule(new NTApiModule())
                .build();


    }

    public static ApplicationComponent getAppComponent(Context context) {
        return ((DashApplication) context.getApplicationContext()).applicationComponent;
    }



}
