package org.diiage.splittripwithyourfriends.ui.createspending;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import org.diiage.splittripwithyourfriends.entities.Participant;
import org.diiage.splittripwithyourfriends.entities.Participation;
import org.diiage.splittripwithyourfriends.entities.Spending;
import org.diiage.splittripwithyourfriends.repositories.ParticipantRepository;
import org.diiage.splittripwithyourfriends.repositories.ParticipationRepository;
import org.diiage.splittripwithyourfriends.repositories.SpendingRepository;

import java.util.List;

public class CreateSpendingViewModel extends AndroidViewModel {

    private ParticipantRepository participantRepository;
    private SpendingRepository spendingRepository;
    private ParticipationRepository participationRepository;

    public CreateSpendingViewModel(@NonNull Application application) {
        super(application);
        participantRepository = new ParticipantRepository(application);
        spendingRepository = new SpendingRepository(application);
        participationRepository = new ParticipationRepository(application);
    }

    public LiveData<List<Participant>> getAllParticipants(long id) {
        return participantRepository.getAllParticipants(id);
    }

    public Participant getParticipantByName(String name) {
        return participantRepository.getParticipantByName(name);
    }

    public long addSpending(Spending spending) {
        return spendingRepository.insertAndRetrieveId(spending);
    }

    public void addParticipation(Participation participation) {
        participationRepository.insert(participation);
    }
}
