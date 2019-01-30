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

import java.util.List;

public class TripAdapter extends RecyclerView.Adapter {

    private static class TripViewHolder extends RecyclerView.ViewHolder {
        TextView txtTripName;
        //TextView txtNbParticipants;


        private TripViewHolder(View itemView) {
            super(itemView);
            txtTripName = itemView.findViewById(R.id.txtTripName);
            //txtNbParticipants = itemView.findViewById(R.id.txtTripNbParticipants);
        }
    }

    private Context context;
    private List<Trip> lstTrips;

    public TripAdapter(Context context, List<Trip> lstTrips) {
        this.context = context.getApplicationContext();
        this.lstTrips = lstTrips;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.trip_item, parent, false);
        return new TripViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TripViewHolder) {
            ((TripViewHolder) holder).txtTripName.setText(this.lstTrips.get(position).toString());
        }
    }

    @Override
    public int getItemCount() {
        return this.lstTrips.size();
    }

    public void setTrips(List<Trip> lstTrips) {
        this.lstTrips = lstTrips;
    }
}
