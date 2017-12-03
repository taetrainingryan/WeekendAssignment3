package com.example.ryan.weekendassignment3;

import android.app.Application;

import com.example.ryan.weekendassignment3.injection.components.ApplicationComponent;
import com.example.ryan.weekendassignment3.injection.modules.ApplicationModule;

/**
 * Created by Ryan on 03/12/2017.
 */

public class MyApp extends Application {

    ApplicationComponent applicationComponent;

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public void setApplicationComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

//        applicationComponent = DaggerApplicationComponent.builder()
//                .applicationModule(new ApplicationModule(this))
//                .build();
    }
}
