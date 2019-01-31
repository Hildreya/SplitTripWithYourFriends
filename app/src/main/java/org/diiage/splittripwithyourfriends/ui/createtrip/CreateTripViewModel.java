package org.diiage.splittripwithyourfriends.ui.createtrip;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import org.diiage.splittripwithyourfriends.database.SplitTripDatabase;
import org.diiage.splittripwithyourfriends.entities.Trip;
import org.diiage.splittripwithyourfriends.repositories.TripRepository;

import java.util.List;

public class CreateTripViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private static final String DATABASE_NAME = "trips_db";
    private SplitTripDatabase tripDatabase;
    private TripRepository mRepository;

    public String AddTrip(String name){
        return "Yes";
    }

    public CreateTripViewModel(Application application) {
        super(application);
        mRepository = new TripRepository(application);
    }

    public void insert(Trip trip){mRepository.insert(trip);}
}
