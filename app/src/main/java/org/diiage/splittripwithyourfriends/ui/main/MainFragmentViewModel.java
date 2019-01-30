package org.diiage.splittripwithyourfriends.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Pair;

import org.diiage.splittripwithyourfriends.entities.Trip;
import org.diiage.splittripwithyourfriends.repositories.TripRepository;

import java.util.List;

public class MainFragmentViewModel extends AndroidViewModel {

    private MutableLiveData<List<Pair<String, String>>> tripList;
    // On définie ici nos repositories
    private TripRepository tripRepository;
    private LiveData<List<Trip>> mAllTrips;

    public MainFragmentViewModel(Application application) {
        // On instancie les repositories
        super(application);
        tripRepository = new TripRepository(application);
        mAllTrips= tripRepository.getAllTrips();
        // On ajoute les sources à obsever (avec MediatorLiveData)
    }

    public MutableLiveData<List<Pair<String, String>>> getTripList() {
        // A partir des résultats retournés par le repository on renvoie notre liste
        return tripList;
    }

    public LiveData<List<Trip>> getmAllTrips() {return mAllTrips; }
}
