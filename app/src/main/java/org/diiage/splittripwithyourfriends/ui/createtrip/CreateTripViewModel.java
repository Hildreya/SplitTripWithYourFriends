package org.diiage.splittripwithyourfriends.ui.createtrip;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.databinding.Bindable;

import org.diiage.splittripwithyourfriends.database.SplitTripDatabase;
import org.diiage.splittripwithyourfriends.entities.Trip;
import org.diiage.splittripwithyourfriends.repositories.TripRepository;

import java.util.List;

public class CreateTripViewModel extends AndroidViewModel {

    private TripRepository mRepository;

    public CreateTripViewModel(Application application) {
        super(application);
        mRepository = new TripRepository(application);
    }

    public void insert(Trip trip){ mRepository.insert(trip); }
}
