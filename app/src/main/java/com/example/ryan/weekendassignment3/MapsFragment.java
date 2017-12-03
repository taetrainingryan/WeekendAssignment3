package com.example.ryan.weekendassignment3;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ryan.weekendassignment3.data.AppDataManager;
import com.example.ryan.weekendassignment3.data.database.dbModel.RealmController;
import com.example.ryan.weekendassignment3.data.database.dbModel.RealmReservation;
import com.example.ryan.weekendassignment3.data.network.model.ParkingSpot;
import com.example.ryan.weekendassignment3.data.network.model.ParkingSpotDetails;
import com.example.ryan.weekendassignment3.views.ParkingSpots.ParkingSpotsMvpView;
import com.example.ryan.weekendassignment3.views.ParkingSpots.ParkingSpotsPresenter;
import com.example.ryan.weekendassignment3.views.ui.utils.rx.AppSchedulerProvider;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

/**
 * A fragment that launches other parts of the demo application.
 */
public class MapsFragment extends Fragment implements OnMapReadyCallback, ParkingSpotsMvpView {

    MapView mMapView;
    private GoogleMap googleMap;
    private ParkingSpotsPresenter parkingSpotsPresenter;
    private RealmController realmController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflat and return the layout
        View v = inflater.inflate(R.layout.fragment_maps, container,
                false);
        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        initializePresenter();
        Realm.init(getContext());
        realmController = RealmController.getInstance();


        mMapView.onResume();// needed to get the map to display immediately

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapView);
        if (mapFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.mapView, mapFragment).commit();
        }

        if(mapFragment!=null) {

            mapFragment.getMapAsync(this);
        }

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return v;
    }

    @Override
    public void onMapReady(GoogleMap gMap) {

        googleMap = gMap;

        setUpMap();

        getData();

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {

                marker.hideInfoWindow();

                parkingSpotsPresenter.getSpotDetails(marker.getTitle(), marker);

                return false;

            }

        });

    }

    private void getData() {

        parkingSpotsPresenter.onViewPrepared();
    }

    public void initializePresenter(){

        parkingSpotsPresenter = new ParkingSpotsPresenter<>(new AppDataManager(), new AppSchedulerProvider(), new CompositeDisposable());
        parkingSpotsPresenter.onAttach(this);

    }

    public void setUpMap(){

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        LatLng sydney = new LatLng(37.793388, -122.42133);
        googleMap.moveCamera( CameraUpdateFactory.newLatLngZoom(sydney, 16.0f));

        setStyle();

    }

    public void addMarkers(ParkingSpot parkingSpot, GoogleMap googleMap){

        //double lat= Double.parseDouble(parkingSpot.getLat());
        // double lng= Double.parseDouble(parkingSpot.getLng());

        LatLng location = new LatLng(parkingSpot.getLat(), parkingSpot.getLng());

        CustomInfoWindowAdapter adapter = new CustomInfoWindowAdapter(getActivity());
        googleMap.setInfoWindowAdapter(adapter);


        if(parkingSpot.getIsReserved()){

            googleMap.addMarker(new MarkerOptions().position(location).title(parkingSpot.getId().toString())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))).hideInfoWindow();
        }

        else{

            googleMap.addMarker(new MarkerOptions().position(location).title(parkingSpot.getId().toString())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))).hideInfoWindow();
        }
    }

    @Override
    public void onFetchDataSuccess(List<ParkingSpot> parkingSpots) {

        for (ParkingSpot r : parkingSpots) {
            addMarkers(r, googleMap);
        }
    }

    @Override
    public void onFetchDataError(String message) {

    }

    @Override
    public void onFetchDetails(ParkingSpotDetails parkingSpotDetails, Marker marker) {

        marker.setSnippet("Is Reserved? " + parkingSpotDetails.getIsReserved()
                + "\nCost per minute: " + parkingSpotDetails.getCostPerMinute());
        //marker.setSnippet("Cost per minute: " + );

        marker.showInfoWindow();

        if(parkingSpotDetails.getIsReserved()!=true){

            displaySnackbar(marker);

        }
    }

    @Override
    public void reserveParkingSpot(Marker marker, ParkingSpotDetails parkingSpotDetails) {

        Toast.makeText(getActivity(), "Spot reserved", Toast.LENGTH_SHORT).show();

        marker.remove();
        googleMap.addMarker(new MarkerOptions().position(marker.getPosition()).title(marker.getTitle())
                .snippet("Is Reserved: " + parkingSpotDetails.getIsReserved() + "\nReserved until: " + parkingSpotDetails.getReservedUntil())
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))).showInfoWindow();

        RealmReservation reservation = new RealmReservation(marker.getTitle(), parkingSpotDetails.getName(), parkingSpotDetails.getReservedUntil());

        realmController.saveReservation(reservation);

    }

    private void displaySnackbar(final Marker marker) {

        View container = getActivity().findViewById(android.R.id.content);

        // Define the click listener as a member
        View.OnClickListener snackOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Spot reserved", Toast.LENGTH_SHORT).show();

                parkingSpotsPresenter.reserveSpot(marker);
            }
        };

        if(container!=null) {
            Snackbar.make(container, R.string.snackbar_text, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.snackbar_action, snackOnClickListener)
                    .show();
        }
    }

    private void setStyle() {

        MapStyleOptions style;

        style = MapStyleOptions.loadRawResourceStyle(getActivity(), R.raw.mapstyle);
        googleMap.setMapStyle(style);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void openActivityOnTokenExpire() {

    }

    @Override
    public void onError(int resId) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int resId) {

    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void hideKeyboard() {

    }
}