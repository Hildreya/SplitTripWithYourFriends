package org.diiage.splittripwithyourfriends.adapters;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.diiage.splittripwithyourfriends.HomeTripActivity;
import org.diiage.splittripwithyourfriends.R;
import org.diiage.splittripwithyourfriends.entities.Trip;
import org.diiage.splittripwithyourfriends.repositories.SpendingRepository;
import org.diiage.splittripwithyourfriends.repositories.StatutRepository;
import org.diiage.splittripwithyourfriends.repositories.TripRepository;
import org.diiage.splittripwithyourfriends.ui.main.MainDeleteDialogFragment;
import org.diiage.splittripwithyourfriends.ui.main.MainSaveDialogFragment;

import java.util.List;

public class TripAdapter extends RecyclerView.Adapter {

    public static class TripViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView txtTripName;
        public final TextView txtTripStatutName;
        public final TextView txtDateTrip;

        public TripViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            this.txtTripName = itemView.findViewById(R.id.txtTripName);
            this.txtTripStatutName = itemView.findViewById(R.id.txtTripStatutName);
            this.txtDateTrip = itemView.findViewById(R.id.tvDateTrip);
        }
    }

    private List<Trip> lstTrips;
    private StatutRepository statutRepository;
    private SpendingRepository spendingRepository;
    public TripAdapter(StatutRepository statutRepository, SpendingRepository spendingRepository) {
        this.statutRepository=statutRepository;
        this.spendingRepository=spendingRepository;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_item, parent, false);
        return new TripViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TripViewHolder) {
            ((TripViewHolder) holder).txtTripName.setText(this.lstTrips.get(position).getName());
            long i = this.lstTrips.get(position).getStatutId();
            String s = statutRepository.getStatutNameById(i);
            ((TripViewHolder) holder).txtTripStatutName.setText(s);
            String date = ((TripViewHolder) holder).view.getResources().getString(R.string.from_the) + ": "+ this.lstTrips.get(position).getBeginDate() +" "+((TripViewHolder) holder).view.getResources().getString(R.string.to_the)+" "+ this.lstTrips.get(position).getEndDate();
            ((TripViewHolder) holder).txtDateTrip.setText(date);
            String tripName= this.lstTrips.get(position).getName();
            long tripId= this.lstTrips.get(position).getId();

            Button btnUpdate = holder.itemView.findViewById(R.id.btnUpdateTripDialog);
            btnUpdate.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //Show the dialog
                    Context ctx = v.getContext();
                    FragmentManager fm = ((AppCompatActivity)ctx).getFragmentManager();
                    MainSaveDialogFragment dialogFragment;
                    dialogFragment = MainSaveDialogFragment.newInstance(tripName, tripId);
                    dialogFragment.show(fm, "dialog_trip_save");
                }
            });

            Button btnDelete = holder.itemView.findViewById(R.id.btnDeleteTrip);
            btnDelete.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(spendingRepository.getNbSpendingOnTrip(tripId) >= 1) {
                        Toast.makeText(v.getContext(),R.string.trip_foreign_key, Toast.LENGTH_LONG).show();
                    } else {
                        //Show the dialog
                        Context ctx = v.getContext();
                        FragmentManager fm = ((AppCompatActivity)ctx).getFragmentManager();
                        MainDeleteDialogFragment dialogFragment;
                        dialogFragment = MainDeleteDialogFragment.newInstance(tripName, tripId);
                        dialogFragment.show(fm, "dialog_trip_delete");
                    }
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Go to HomeTripActivity with parameters
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
