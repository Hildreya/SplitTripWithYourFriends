package org.diiage.splittripwithyourfriends.ui.spending;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.diiage.splittripwithyourfriends.R;
import org.diiage.splittripwithyourfriends.adapters.ParticipantAdapter;
import org.diiage.splittripwithyourfriends.adapters.SpendingAdapter;
import org.diiage.splittripwithyourfriends.databinding.HomeTripFragmentBinding;
import org.diiage.splittripwithyourfriends.databinding.SpendingsFragmentBinding;
import org.diiage.splittripwithyourfriends.repositories.ParticipantRepository;
import org.diiage.splittripwithyourfriends.ui.hometrip.HomeTripViewModel;

public class SpendingsFragment extends Fragment {

    private SpendingsViewModel spViewModel;
    private RecyclerView recyclerView;
    private SpendingAdapter spendingAdapter;
    private long tripId;

    public static SpendingsFragment newInstance() {
        return new SpendingsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        SpendingsFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.spendings_fragment, container, false);
        spViewModel = ViewModelProviders.of(this).get(SpendingsViewModel.class);
        this.tripId = getArguments().getLong("ParamTripId");
        final TextView tripNameED = binding.getRoot().findViewById(R.id.tvTrip_Name);
        tripNameED.setText(getArguments().getString("ParamTripName"));
        recyclerView = binding.getRoot().findViewById(R.id.spendingList);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.spendingAdapter = new SpendingAdapter(new ParticipantRepository(getActivity().getApplication()));
        spViewModel.getAllSpendings(tripId).observe(this, spendings -> {
            spendingAdapter.setSpendingsList(spendings);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(this.spendingAdapter);
    }

}
