package com.example.ryan.weekendassignment3.injection.modules;

import android.app.Application;
import android.content.Context;

import com.example.ryan.weekendassignment3.data.AppDataManager;
import com.example.ryan.weekendassignment3.data.IDataManager;
import com.example.ryan.weekendassignment3.data.network.ApiHelper;
import com.example.ryan.weekendassignment3.data.network.AppApiHelper;
import com.example.ryan.weekendassignment3.injection.scope.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ryan on 03/12/2017.
 */

@Module
public class ApplicationModule {

    /**
     * Application Context
     * DataManager
     * ApiHelper
     *
     * Stuff used in application (classes)
     */

    Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @ApplicationContext
    Context getContext(){
        return application;
    }

    @Provides
    Application getApplication(){
        return application;
    }

    @Provides
    @Singleton
    IDataManager getDataManager(AppDataManager appDataManager){
        return appDataManager;
    }

    @Provides
    @Singleton
    ApiHelper getApiHelper(AppApiHelper appApiHelper){
        return appApiHelper;
    }
}
