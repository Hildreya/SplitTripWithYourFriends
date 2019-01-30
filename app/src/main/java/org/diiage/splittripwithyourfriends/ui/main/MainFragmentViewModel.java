package org.diiage.splittripwithyourfriends.ui.main;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Pair;

import java.util.List;

public class MainFragmentViewModel extends ViewModel {

    private MutableLiveData<List<Pair<String, String>>> tripList;
    // On définie ici nos repositories

    public MainFragmentViewModel() {
        // On instancie les repositories

        // On ajoute les sources à obsever (avec MediatorLiveData)


    }

    public MutableLiveData<List<Pair<String, String>>> getTripList() {
        // A partir des résultats retournés par le repository on renvoie notre liste
        return tripList;
    }
}
