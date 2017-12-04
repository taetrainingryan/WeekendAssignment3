package com.example.ryan.weekendassignment3.injection;

import com.example.ryan.weekendassignment3.data.AppDataManager;
import com.example.ryan.weekendassignment3.data.IDataManager;
import com.example.ryan.weekendassignment3.views.ParkingSpots.ParkingSpotsPresenter;
import com.example.ryan.weekendassignment3.views.ui.utils.rx.AppSchedulerProvider;
import com.example.ryan.weekendassignment3.views.ui.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Ryan on 04/12/2017.
 */

@Module
public class PresenterModule {

    @Provides
    ParkingSpotsPresenter getParkingSpotsPresenter() {
        return new ParkingSpotsPresenter(
                new AppDataManager(),
                new AppSchedulerProvider(),
                new CompositeDisposable());
    }
}
