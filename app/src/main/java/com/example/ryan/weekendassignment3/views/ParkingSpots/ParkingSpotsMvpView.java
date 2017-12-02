package com.example.ryan.weekendassignment3.views.ParkingSpots;

import com.example.ryan.weekendassignment3.data.network.model.ParkingSpot;
import com.example.ryan.weekendassignment3.data.network.model.ParkingSpotDetails;
import com.example.ryan.weekendassignment3.views.ui.base.MvpView;
import com.google.android.gms.maps.model.Marker;

import java.util.List;

/**
 * Created by Ryan on 02/12/2017.
 */

public interface ParkingSpotsMvpView extends MvpView{

    void onFetchDataSuccess(List<ParkingSpot> parkingSpots);
    void onFetchDataError(String message);
    void onFetchDetails(ParkingSpotDetails parkingSpotDetails, Marker marker);
    void reserveParkingSpot(Marker marker, ParkingSpotDetails parkingSpotDetails);

}
