package org.diiage.splittripwithyourfriends.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.diiage.splittripwithyourfriends.HomeTripActivity;
import org.diiage.splittripwithyourfriends.R;
import org.diiage.splittripwithyourfriends.entities.Trip;

import java.util.List;

public class TripAdapter extends RecyclerView.Adapter {

    public static class TripViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView txtTripName;
        public final TextView txtTripStatutName;

        public TripViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            this.txtTripName = itemView.findViewById(R.id.txtTripName);
            this.txtTripStatutName = itemView.findViewById(R.id.txtTripStatutName);
        }
    }

    private List<Trip> lstTrips;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_item, parent, false);
        return new TripViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TripViewHolder) {
            ((TripViewHolder) holder).txtTripName.setText(this.lstTrips.get(position).getName());
            long i = this.lstTrips.get(position).getStatutId();
            String s = "Statut : "+i ;
            ((TripViewHolder) holder).txtTripStatutName.setText(s);

            String tripName= this.lstTrips.get(position).getName();
            long tripId= this.lstTrips.get(position).getId();
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), HomeTripActivity.class);
                    Bundle b = new Bundle();
                    b.putString("ParamTripName", tripName); //Your id
                    b.putLong("ParamTripId", tripId); //Your id
                    intent.putExtras(b); //Put your id to your next Intent
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(lstTrips != null) {
            return this.lstTrips.size();
        } else {
            return 0;
        }
    }

    public void setTrips(List<Trip> lstTrips) {
        this.lstTrips = lstTrips;
        notifyDataSetChanged();
    }
}
