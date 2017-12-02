package com.example.ryan.weekendassignment3.data.network;

import com.example.ryan.weekendassignment3.data.network.model.ParkingSpot;
import com.example.ryan.weekendassignment3.data.network.model.ParkingSpotDetails;
import com.example.ryan.weekendassignment3.services.Api_List;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Ryan on 02/12/2017.
 */

public interface ApiHelper {

    Observable<List<ParkingSpot>> getParkingSpots();

    Observable<ParkingSpotDetails> getParkingSpotDetails(@Path("id") String id);

    Observable<ParkingSpotDetails>reserveSpot(@Path("id") String id, @Path("reserve") String reserve);
}
