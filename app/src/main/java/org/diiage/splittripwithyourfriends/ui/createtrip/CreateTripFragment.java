package org.diiage.splittripwithyourfriends.ui.createtrip;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.diiage.splittripwithyourfriends.R;

public class CreateTripFragment extends Fragment {

    private CreateTripViewModel mViewModel;

    public static CreateTripFragment newInstance() {
        return new CreateTripFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.create_trip_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CreateTripViewModel.class);
        // TODO: Use the ViewModel
    }

}
