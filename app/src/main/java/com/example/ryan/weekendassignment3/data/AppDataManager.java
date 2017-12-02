package com.example.ryan.weekendassignment3.data;

import com.example.ryan.weekendassignment3.data.network.ApiHelper;
import com.example.ryan.weekendassignment3.data.network.AppApiHelper;
import com.example.ryan.weekendassignment3.data.network.model.ParkingSpot;
import com.example.ryan.weekendassignment3.data.network.model.ParkingSpotDetails;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Ryan on 02/12/2017.
 */

public class AppDataManager implements IDataManager {

    private ApiHelper apiHelper;


    public AppDataManager() {
        apiHelper = new AppApiHelper();
    }

    @Override
    public Observable<List<ParkingSpot>> getParkingSpots() {
        return apiHelper.getParkingSpots();
    }

    @Override
    public Observable<ParkingSpotDetails> getParkingSpotDetails(String id) {
        return apiHelper.getParkingSpotDetails(id);
    }

    @Override
    public Observable<ParkingSpotDetails> reserveSpot(String id, String reserve) {
        return apiHelper.reserveSpot(id, reserve);
    }
}
