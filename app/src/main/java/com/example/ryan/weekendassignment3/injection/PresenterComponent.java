package com.example.ryan.weekendassignment3.injection;

import com.example.ryan.weekendassignment3.MapsFragment;

import dagger.Component;

/**
 * Created by Ryan on 04/12/2017.
 */

@Component(modules = {PresenterModule.class})
public interface PresenterComponent {

    void inject(MapsFragment mapsFragment);
}
