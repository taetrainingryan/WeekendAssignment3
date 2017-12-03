package com.example.ryan.weekendassignment3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ryan.weekendassignment3.data.database.dbModel.RealmReservation;

import java.util.List;

import javax.annotation.Nullable;

/**
 * Created by Ryan on 03/12/2017.
 */

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.MyViewHolder>{

    private List<RealmReservation> reservations;
    private int row_reservations;
    private Context applicationContext;

    public ViewAdapter(List<RealmReservation> reservations, int row_reservations, Context applicationContext) {
        this.reservations = reservations;
        this.row_reservations = row_reservations;
        this.applicationContext = applicationContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(applicationContext).inflate(row_reservations, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        holder.id.setText(reservations.get(position).getId());
        holder.name.setText(reservations.get(position).getName());
        holder.reservedUntil.setText(reservations.get(position).getReservedUntil());

    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id, name, reservedUntil;

        public MyViewHolder(View itemView) {
            super(itemView);

            id = (TextView) itemView.findViewById(R.id.tvId);
            name = (TextView) itemView.findViewById(R.id.tvName);
            reservedUntil = (TextView) itemView.findViewById(R.id.tvReservedUntil);

        }
    }
}
