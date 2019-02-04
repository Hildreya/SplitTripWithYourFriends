package org.diiage.splittripwithyourfriends.ui.addparticipant;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.ClipData;
import android.support.annotation.NonNull;

import org.diiage.splittripwithyourfriends.entities.Participant;
import org.diiage.splittripwithyourfriends.repositories.ParticipantRepository;
import org.diiage.splittripwithyourfriends.repositories.TripParticipantRepository;

import java.util.List;

public class AddParticipantViewModel extends AndroidViewModel {

    private ParticipantRepository participantRepository;
    private TripParticipantRepository tripParticipantRepository;

    public AddParticipantViewModel(Application application) {
        super(application);
        participantRepository = new ParticipantRepository(application);
        tripParticipantRepository = new TripParticipantRepository(application);
    }

    public LiveData<List<Participant>> getmUnregisteredParticipants(long tripId) {
        return participantRepository.getUnregisteredParticipants(tripId);
    }

    public LiveData<List<Participant>> getAllParticipants(long id) {
        return participantRepository.getAllParticipants(id);
    }

    public void registerParticipant(long participantId, long tripId) {
        tripParticipantRepository.registerParticipant(participantId,tripId);
    }
}

