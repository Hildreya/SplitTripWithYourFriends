package org.diiage.splittripwithyourfriends.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Pair;

import org.diiage.splittripwithyourfriends.entities.Trip;
import org.diiage.splittripwithyourfriends.repositories.TripRepository;

import java.util.ArrayList;
import java.util.List;

public class MainFragmentViewModel extends AndroidViewModel {

    private TripRepository tripRepository;
    private LiveData<List<Trip>> mAllTrips;

    public MainFragmentViewModel(Application application) {
        super(application);
        tripRepository = new TripRepository(application);
        mAllTrips= tripRepository.getAllTrips();
    }

    public LiveData<List<Trip>> getmAllTrips() { return mAllTrips; }
}
