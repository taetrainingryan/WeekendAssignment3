package com.example.ryan.weekendassignment3.data.database.dbModel;

import io.realm.RealmObject;

/**
 * Created by Ryan on 03/12/2017.
 */

public class RealmReservation extends RealmObject{

    String id, name, reservedUntil;

    public RealmReservation() {
    }

    public RealmReservation(String id, String name, String reservedUntil) {
        this.id = id;
        this.name = name;
        this.reservedUntil = reservedUntil;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReservedUntil() {
        return reservedUntil;
    }

    public void setReservedUntil(String reservedUntil) {
        this.reservedUntil = reservedUntil;
    }
}
