package com.example.ryan.weekendassignment3.data.database.dbModel;

/**
 * Created by Ryan on 03/12/2017.
 */

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Ryan on 25/11/2017.
 */

public class RealmController {

    private static RealmController instance = null;

    public static RealmController getInstance(){

        synchronized (RealmController.class){

            if(instance == null){

                synchronized (RealmController.class){

                    instance = new RealmController(Realm.getDefaultInstance());

                }
            }
        }

        return instance;
    }

    private Realm realm;

    public RealmController(Realm realm) {

        this.realm = realm;
    }

    public void saveReservation(final RealmReservation realmReservation){

        realm.executeTransaction(new Realm.Transaction() {

            @Override
            public void execute(Realm realm) {

                realm.copyToRealm(realmReservation);

            }
        });
    }

    /*
    Returns list of all customers.
     */

    public List<RealmReservation> getreservations(){

        List<RealmReservation> reservations = new ArrayList<>();

        RealmResults<RealmReservation> realmTracksRealmResults = realm.where(RealmReservation.class).findAll();

        for(RealmReservation realmReservation: realmTracksRealmResults){
            reservations.add(realmReservation);
        }

        return reservations;

    }

}
