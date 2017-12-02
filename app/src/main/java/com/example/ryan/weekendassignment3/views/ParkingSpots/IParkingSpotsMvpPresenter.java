package com.example.ryan.weekendassignment3.views.ParkingSpots;

import com.example.ryan.weekendassignment3.views.ui.base.MvpPresenter;

/**
 * Created by Ryan on 02/12/2017.
 */

public interface IParkingSpotsMvpPresenter <V extends ParkingSpotsMvpView> extends MvpPresenter<V>{

    void onViewPrepared();
}
