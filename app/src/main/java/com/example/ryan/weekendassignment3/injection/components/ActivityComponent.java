package com.example.ryan.weekendassignment3.injection.components;

import com.example.ryan.weekendassignment3.MapsFragment;
import com.example.ryan.weekendassignment3.injection.modules.ActivityModule;
import com.example.ryan.weekendassignment3.injection.scope.PerActivity;

import dagger.Component;

/**
 * Created by Ryan on 03/12/2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MapsFragment mapsFragment);


}
