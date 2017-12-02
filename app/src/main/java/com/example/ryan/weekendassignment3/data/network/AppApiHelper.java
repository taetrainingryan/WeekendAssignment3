package com.example.ryan.weekendassignment3.data.network;

import com.example.ryan.weekendassignment3.data.network.model.ParkingSpot;
import com.example.ryan.weekendassignment3.data.network.model.ParkingSpotDetails;
import com.example.ryan.weekendassignment3.data.network.service.RequestInterface;
import com.example.ryan.weekendassignment3.data.network.service.ServerConnection;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Ryan on 02/12/2017.
 */

public class AppApiHelper implements ApiHelper{

    public RequestInterface requestInterface;

    public AppApiHelper() {

        requestInterface = ServerConnection.getServerConnection();
    }

    @Override
    public Observable<List<ParkingSpot>> getParkingSpots() {
        return requestInterface.getParkingSpots();
    }

    @Override
    public Observable<ParkingSpotDetails> getParkingSpotDetails(String id) {
        return requestInterface.getParkingSpotDetails(id);
    }

    @Override
    public Observable<ParkingSpotDetails> reserveSpot(String id, String reserve) {
        return requestInterface.reserveSpot(id, reserve);
    }
}
