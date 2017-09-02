package com.dashfornavhindtimes.ui.util;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dashfornavhindtimes.app.DashApplication;
import com.dashfornavhindtimes.injection.app.ApplicationComponent;

/**
 * Created by Lucas on 19/06/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent(DashApplication.getAppComponent(this));
    }

    protected abstract void setupActivityComponent(ApplicationComponent applicationComponent);
}
