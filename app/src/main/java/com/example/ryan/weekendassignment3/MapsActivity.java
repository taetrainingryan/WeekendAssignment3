package com.example.ryan.weekendassignment3;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ryan.weekendassignment3.model.ParkingSpot;
import com.example.ryan.weekendassignment3.services.RequestInterface;
import com.example.ryan.weekendassignment3.services.ServerConnection;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

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

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(37.774074, -122.422068);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void addMarkers(ParkingSpot parkingSpot, GoogleMap googleMap){

        //double lat= Double.parseDouble(parkingSpot.getLat());
       // double lng= Double.parseDouble(parkingSpot.getLng());

        LatLng location = new LatLng(parkingSpot.getLat(), parkingSpot.getLng());
        mMap.addMarker(new MarkerOptions().position(location).title(parkingSpot.getId().toString()).snippet(parkingSpot.getLat() + " " + parkingSpot.getLng()));

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

                        LatLng sydney = new LatLng(37.774074, -122.422068);
                        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(sydney, 12.0f));

                    }
                });
    }
}
