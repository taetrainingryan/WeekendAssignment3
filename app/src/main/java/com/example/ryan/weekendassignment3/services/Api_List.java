package com.example.ryan.weekendassignment3.services;

/**
 * Created by Ryan on 01/12/2017.
 */

public class Api_List {

    //http://ridecellparking.herokuapp.com/api/v1/parkinglocations/{{id}}/reserve

    //http://ridecellparking.herokuapp.com/api/v1/parkinglocations/{{id}}
    public static final String BASE_URL = "http://ridecellparking.herokuapp.com/api/v1/";
    public static final String PARKING_SPOTS = "parkinglocations";
    public static final String SPOT_DETAILS = "parkinglocations/{id}";
    public static final String SPOT_RESERVE = "parkinglocations/{id}/{reserve}";

}
