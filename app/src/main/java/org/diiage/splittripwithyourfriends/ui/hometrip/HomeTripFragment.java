package org.diiage.splittripwithyourfriends.ui.hometrip;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.diiage.splittripwithyourfriends.R;
import org.diiage.splittripwithyourfriends.adapters.ParticipantAdapter;
import org.diiage.splittripwithyourfriends.databinding.FragmentMainBinding;
import org.diiage.splittripwithyourfriends.databinding.HomeTripFragmentBinding;
import org.diiage.splittripwithyourfriends.entities.Participant;
import org.diiage.splittripwithyourfriends.ui.main.MainFragmentViewModel;

import java.util.List;

public class HomeTripFragment extends Fragment {

    private HomeTripViewModel hViewModel;
    private RecyclerView recyclerView;
    private ParticipantAdapter participantAdapter;
    private long tripId;

    public static HomeTripFragment newInstance() {
        return new HomeTripFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        HomeTripFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.home_trip_fragment, container, false);
        hViewModel = ViewModelProviders.of(this).get(HomeTripViewModel.class);
        this.tripId = getArguments().getLong("ParamTripId");
        final TextView tripNameED = binding.getRoot().findViewById(R.id.tvTrip_Name);
        tripNameED.setText(getArguments().getString("ParamTripName"));
        recyclerView = binding.getRoot().findViewById(R.id.participantList);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.participantAdapter = new ParticipantAdapter();
        hViewModel.getAllParticipants(tripId).observe(this, participants -> {
            participantAdapter.setParticipantsList(participants);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(this.participantAdapter);
    }

}
