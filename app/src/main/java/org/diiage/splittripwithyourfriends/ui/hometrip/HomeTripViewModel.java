package org.diiage.splittripwithyourfriends.ui.hometrip;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import org.diiage.splittripwithyourfriends.entities.Participant;
import org.diiage.splittripwithyourfriends.entities.TripParticipantJoin;
import org.diiage.splittripwithyourfriends.repositories.ParticipantRepository;

import java.util.List;

public class HomeTripViewModel extends AndroidViewModel {
    private ParticipantRepository participantRepository;

    public HomeTripViewModel(Application application) {
        super(application);
        participantRepository = new ParticipantRepository(application);
    }

    public LiveData<List<Participant>> getAllParticipants() { return participantRepository.getAllParticipants(); }
    public LiveData<List<Participant>> getAllParticipants(long id) { return participantRepository.getAllParticipants(id); }
}
