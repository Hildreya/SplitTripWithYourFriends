package org.diiage.splittripwithyourfriends;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.diiage.splittripwithyourfriends.adapters.TripAdapter;
import org.diiage.splittripwithyourfriends.databinding.FragmentMainBinding;
import org.diiage.splittripwithyourfriends.repositories.SpendingRepository;
import org.diiage.splittripwithyourfriends.repositories.StatutRepository;
import org.diiage.splittripwithyourfriends.ui.main.MainFragmentViewModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    RecyclerView recyclerView;
    MainFragmentViewModel mainFragmentViewModel;
    TripAdapter tripAdapter;

    public MainActivityFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentMainBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        mainFragmentViewModel = ViewModelProviders.of(this).get(MainFragmentViewModel.class);
        recyclerView = binding.getRoot().findViewById(R.id.tripList);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StatutRepository statutRepository= new StatutRepository(getActivity().getApplication());
        SpendingRepository spendingRepository= new SpendingRepository(getActivity().getApplication());
        this.tripAdapter = new TripAdapter(statutRepository, spendingRepository);
        mainFragmentViewModel.getmAllTrips().observe(this, trips -> {
            tripAdapter.setTrips(trips);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(this.tripAdapter);
    }
}
