package com.example.ryan.weekendassignment3;

import android.app.Application;

import com.example.ryan.weekendassignment3.injection.DaggerPresenterComponent;
import com.example.ryan.weekendassignment3.injection.PresenterComponent;

/**
 * Created by Ryan on 04/12/2017.
 */

public class MyApp extends Application{

    PresenterComponent presenterComponent;
    public static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        presenterComponent = DaggerPresenterComponent.create();
    }

    public PresenterComponent getPresenterComponent() {
        return presenterComponent;
    }

    public void setPresenterComponent(PresenterComponent presenterComponent) {
        this.presenterComponent = presenterComponent;
    }
}
