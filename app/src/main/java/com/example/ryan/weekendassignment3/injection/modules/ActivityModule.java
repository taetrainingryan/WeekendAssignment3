package com.example.ryan.weekendassignment3.injection.modules;

import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.example.ryan.weekendassignment3.injection.scope.ActivityContext;
import com.example.ryan.weekendassignment3.views.ParkingSpots.IParkingSpotsMvpPresenter;
import com.example.ryan.weekendassignment3.views.ParkingSpots.ParkingSpotsMvpView;
import com.example.ryan.weekendassignment3.views.ParkingSpots.ParkingSpotsPresenter;
import com.example.ryan.weekendassignment3.views.ui.utils.rx.AppSchedulerProvider;
import com.example.ryan.weekendassignment3.views.ui.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Ryan on 03/12/2017.
 */

@Module
public class ActivityModule {

    /**
     * Activity
     * Presenter
     * Scheduler
     * Disposable
     * <p>
     * Stuff used in views.
     */

    AppCompatActivity appCompatActivity;
    FragmentActivity fragmentActivity;

    public ActivityModule(FragmentActivity fragmentactivity) {
        this.fragmentActivity = fragmentactivity;
    }

    @Provides
    @ActivityContext
    Context getActivityContext() {
        return fragmentActivity.getBaseContext();
    }

    @Provides
    FragmentActivity getFragmentActivity() {
        return fragmentActivity;
    }

    @Provides
    SchedulerProvider getAppSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    CompositeDisposable getCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    IParkingSpotsMvpPresenter<ParkingSpotsMvpView> getParkingSpotsPresenter(ParkingSpotsPresenter<ParkingSpotsMvpView> parkingSpotsPresenter){
        return parkingSpotsPresenter;
    }
}
