package org.diiage.splittripwithyourfriends;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.diiage.splittripwithyourfriends.databinding.FragmentMainBinding;
import org.diiage.splittripwithyourfriends.ui.main.MainFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static class TripViewHolder extends RecyclerView.ViewHolder {
        TextView txtTripName;
        TextView txtNbParticipants;

        private TripViewHolder(View itemView) {
            super(itemView);
            txtTripName = itemView.findViewById(R.id.txtTripName);
            txtNbParticipants = itemView.findViewById(R.id.txtTripNbParticipants);
        }
    }

    RecyclerView recyclerView;
    MainFragmentViewModel mainFragmentViewModel;

    public MainActivityFragment() {
        mainFragmentViewModel = new MainFragmentViewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        FragmentMainBinding binding = DataBindingUtil.setContentView(this.getActivity(), R.layout.fragment_main);
        binding.setStrHelloWorld("Test binding");

        recyclerView = view.findViewById(R.id.tripList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = inflater.inflate(R.layout.trip_item, parent, false);
                return new TripViewHolder(v);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                List<Pair<String, String>> trip = mainFragmentViewModel.getTripList();
                if (holder instanceof TripViewHolder) {
                    ((TripViewHolder) holder).txtNbParticipants.setText(trip);
                    ((TripViewHolder) holder).txtTripName.setText(trip);
                }
            }

            @Override
            public int getItemCount() {
                return strings.size();
            }
        });

        return view;
    }


}
