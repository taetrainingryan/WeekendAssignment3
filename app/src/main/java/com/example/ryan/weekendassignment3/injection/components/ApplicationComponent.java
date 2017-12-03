package com.example.ryan.weekendassignment3.injection.components;

import android.app.Application;
import android.content.Context;

import com.example.ryan.weekendassignment3.MyApp;
import com.example.ryan.weekendassignment3.data.IDataManager;
import com.example.ryan.weekendassignment3.data.network.ApiHelper;
import com.example.ryan.weekendassignment3.injection.modules.ApplicationModule;
import com.example.ryan.weekendassignment3.injection.scope.ApplicationContext;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ryan on 03/12/2017.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(MyApp app);

    @ApplicationContext
    Context context();

    Application application();

    IDataManager getDataManager();

}
