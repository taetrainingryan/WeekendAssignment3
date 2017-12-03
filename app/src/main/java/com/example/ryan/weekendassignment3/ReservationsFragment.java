package com.example.ryan.weekendassignment3;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryan.weekendassignment3.data.database.dbModel.RealmController;
import com.example.ryan.weekendassignment3.data.database.dbModel.RealmReservation;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReservationsFragment extends Fragment {

    private List<RealmReservation> reservations;
    private RecyclerView recyclerView;
    private ViewAdapter viewAdapter;


    public ReservationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reservations, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeRecyclerView();
    }

    public void initializeRecyclerView() {

        reservations = RealmController.getInstance().getreservations();

        recyclerView = (RecyclerView) getView().findViewById(R.id.rvReservations);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        viewAdapter = new ViewAdapter(reservations, R.layout.row, getActivity().getApplicationContext());

        recyclerView.setAdapter(viewAdapter);
    }

}
