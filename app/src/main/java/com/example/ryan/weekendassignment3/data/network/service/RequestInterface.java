package com.example.ryan.weekendassignment3.data.network.service;

import com.example.ryan.weekendassignment3.data.network.model.ParkingSpot;
import com.example.ryan.weekendassignment3.data.network.model.ParkingSpotDetails;
import com.example.ryan.weekendassignment3.services.Api_List;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Ryan on 01/12/2017.
 */

public interface RequestInterface {

    @GET(Api_List.PARKING_SPOTS)
    Observable<List<ParkingSpot>> getParkingSpots();

    @GET(Api_List.SPOT_DETAILS)
    Observable<ParkingSpotDetails> getParkingSpotDetails(@Path("id") String id);

    @POST(Api_List.SPOT_RESERVE)
    Observable<ParkingSpotDetails>reserveSpot(@Path("id") String id, @Path("reserve") String reserve);

}
