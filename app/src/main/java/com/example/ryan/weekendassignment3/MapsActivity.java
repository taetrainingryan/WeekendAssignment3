package com.example.ryan.weekendassignment3;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ryan.weekendassignment3.model.ParkingSpot;
import com.example.ryan.weekendassignment3.model.ParkingSpotDetails;
import com.example.ryan.weekendassignment3.services.RequestInterface;
import com.example.ryan.weekendassignment3.services.ServerConnection;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_GREEN;
import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_RED;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private RequestInterface requestInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        requestInterface = ServerConnection.getServerConnection();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        getData();

        LatLng sydney = new LatLng(37.793388, -122.42133);
        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(sydney, 16.0f));
        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(37.774074, -122.422068);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {

                marker.hideInfoWindow();

                requestInterface.getParkingSpotDetails(marker.getTitle())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Consumer<ParkingSpotDetails>() {
                            @Override
                            public void accept(ParkingSpotDetails parkingSpotDetails) throws Exception {

                                marker.setSnippet("Is Reserved? " + parkingSpotDetails.getIsReserved()
                                + "\nCost per minute: " + parkingSpotDetails.getCostPerMinute());
                                //marker.setSnippet("Cost per minute: " + );

                                marker.showInfoWindow();

                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                throwable.printStackTrace();

                            }
                        });

                return false;
            }
        });
    }



    public void addMarkers(ParkingSpot parkingSpot, GoogleMap googleMap){

        //double lat= Double.parseDouble(parkingSpot.getLat());
       // double lng= Double.parseDouble(parkingSpot.getLng());

        LatLng location = new LatLng(parkingSpot.getLat(), parkingSpot.getLng());

        CustomInfoWindowAdapter adapter = new CustomInfoWindowAdapter(MapsActivity.this);
        mMap.setInfoWindowAdapter(adapter);


        if(parkingSpot.getIsReserved()==true){

            mMap.addMarker(new MarkerOptions().position(location).title(parkingSpot.getId().toString())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))).hideInfoWindow();
        }

        else{

            mMap.addMarker(new MarkerOptions().position(location).title(parkingSpot.getId().toString())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))).hideInfoWindow();
        }
    }

    public void getData(){

        requestInterface.getParkingSpots()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<ParkingSpot>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ParkingSpot> value) {

                        for (ParkingSpot r : value) {
                            addMarkers(r, mMap);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                        e.printStackTrace();

                    }

                    @Override
                    public void onComplete() {


                    }
                });
    }
}
