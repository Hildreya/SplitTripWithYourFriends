package org.diiage.splittripwithyourfriends.adapters;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.diiage.splittripwithyourfriends.R;
import org.diiage.splittripwithyourfriends.entities.Trip;

import java.util.ArrayList;
import java.util.List;

public class TripAdapter extends RecyclerView.Adapter {

    public static class TripViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView txtTripName;

        public TripViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            txtTripName = itemView.findViewById(R.id.txtTripName);
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
