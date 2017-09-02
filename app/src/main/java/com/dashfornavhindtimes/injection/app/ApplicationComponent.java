package com.dashfornavhindtimes.injection.app;

import android.app.Application;

import com.dashfornavhindtimes.injection.main.MainComponent;
import com.dashfornavhindtimes.data.api.NTApiModule;
import com.dashfornavhindtimes.injection.main.MainModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Lucas on 19/06/16.
 */

@Singleton
@Component(
        modules = {ApplicationModule.class,
                NTApiModule.class
        }
)
public interface ApplicationComponent {

    Application getApplication();

    MainComponent plus(MainModule mainModule);

}
