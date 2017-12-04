package com.example.ryan.weekendassignment3.views.ParkingSpots;

import android.widget.Toast;

import com.example.ryan.weekendassignment3.data.IDataManager;
import com.example.ryan.weekendassignment3.data.network.model.ParkingSpot;
import com.example.ryan.weekendassignment3.data.network.model.ParkingSpotDetails;
import com.example.ryan.weekendassignment3.views.ui.base.BasePresenter;
import com.example.ryan.weekendassignment3.views.ui.base.MvpView;
import com.example.ryan.weekendassignment3.views.ui.utils.rx.SchedulerProvider;
import com.google.android.gms.maps.model.Marker;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ryan on 02/12/2017.
 */

public class ParkingSpotsPresenter<V extends ParkingSpotsMvpView>
        extends BasePresenter<V> implements IParkingSpotsMvpPresenter<V>{

    @Inject
    public ParkingSpotsPresenter(IDataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared() {

        getDataManager().getParkingSpots().observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Consumer<List<ParkingSpot>>() {
            @Override
            public void accept(List<ParkingSpot> parkingSpots) throws Exception {

                getMvpView().onFetchDataSuccess(parkingSpots);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
                getMvpView().onFetchDataError("An error has occurred, please try again.");
            }
        });

    }

    public void getSpotDetails(String id, final Marker marker){

        getDataManager().getParkingSpotDetails(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ParkingSpotDetails>() {
                    @Override
                    public void accept(ParkingSpotDetails parkingSpotDetails) throws Exception {

                        getMvpView().onFetchDetails(parkingSpotDetails, marker);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getMvpView().onFetchDataError("An error has occurred please try again");
                    }
                });
    }

    public void reserveSpot(final Marker marker){

        getDataManager().reserveSpot(marker.getTitle(), "reserve")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ParkingSpotDetails>() {
                    @Override
                    public void accept(ParkingSpotDetails parkingSpotDetails) throws Exception {

                        getMvpView().reserveParkingSpot(marker, parkingSpotDetails);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        getMvpView().onFetchDataError("An error has occurred please try again");
                    }
                });

    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }
}
