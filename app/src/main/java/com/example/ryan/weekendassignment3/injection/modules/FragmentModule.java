//package com.example.ryan.weekendassignment3.injection.modules;
//
//import com.example.ryan.weekendassignment3.data.AppDataManager;
//import com.example.ryan.weekendassignment3.data.IDataManager;
//import com.example.ryan.weekendassignment3.views.ParkingSpots.IParkingSpotsMvpPresenter;
//import com.example.ryan.weekendassignment3.views.ParkingSpots.ParkingSpotsMvpView;
//import com.example.ryan.weekendassignment3.views.ParkingSpots.ParkingSpotsPresenter;
//import com.example.ryan.weekendassignment3.views.ui.utils.rx.AppSchedulerProvider;
//import com.example.ryan.weekendassignment3.views.ui.utils.rx.SchedulerProvider;
//
//import javax.inject.Singleton;
//
//import dagger.Module;
//import dagger.Provides;
//import io.reactivex.disposables.CompositeDisposable;
//
///**
// * Created by Ryan on 04/12/2017.
// */
//
//@Module
//public class FragmentModule {
//
//    @Provides
//    @Singleton
//    IDataManager getDataManager(AppDataManager appDataManager){
//        return appDataManager;
//    }
//
//    @Provides
//    SchedulerProvider getAppSchedulerProvider(){
//        return new AppSchedulerProvider();
//    }
//
//    @Provides
//    CompositeDisposable getCompositeDisposable(){
//        return new CompositeDisposable();
//    }
//
//    @Provides
//    IParkingSpotsMvpPresenter<ParkingSpotsMvpView> getParkingSpotListPresenter(ParkingSpotsPresenter<ParkingSpotsMvpView> parkingSpotsPresenter){
//        return parkingSpotsPresenter;
//    }
//
//
//}
