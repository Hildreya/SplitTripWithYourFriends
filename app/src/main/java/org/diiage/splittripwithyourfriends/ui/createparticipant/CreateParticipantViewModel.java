package org.diiage.splittripwithyourfriends.ui.createparticipant;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import org.diiage.splittripwithyourfriends.entities.Participant;
import org.diiage.splittripwithyourfriends.repositories.ParticipantRepository;

public class CreateParticipantViewModel extends AndroidViewModel {

    private ParticipantRepository repository;

    public CreateParticipantViewModel(@NonNull Application application) {
        super(application);
        repository = new ParticipantRepository(application);
    }

    public void insert(Participant participant) {
        repository.insert(participant);
    }

}
