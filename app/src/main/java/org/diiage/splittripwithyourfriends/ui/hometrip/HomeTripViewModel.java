package org.diiage.splittripwithyourfriends.ui.hometrip;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Pair;

import org.diiage.splittripwithyourfriends.business.CalculService;
import org.diiage.splittripwithyourfriends.entities.Participant;
import org.diiage.splittripwithyourfriends.entities.Participation;
import org.diiage.splittripwithyourfriends.entities.Spending;
import org.diiage.splittripwithyourfriends.repositories.ParticipantRepository;
import org.diiage.splittripwithyourfriends.repositories.ParticipationRepository;
import org.diiage.splittripwithyourfriends.repositories.SpendingRepository;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class HomeTripViewModel extends AndroidViewModel {
    private ParticipantRepository participantRepository;
    private SpendingRepository spendingRepository;
    private ParticipationRepository participationRepository;
    private CalculService calculService;

    public HomeTripViewModel(Application application) {
        super(application);
        participantRepository = new ParticipantRepository(application);
        spendingRepository = new SpendingRepository(application);
        participationRepository = new ParticipationRepository(application);
        calculService = new CalculService();
    }

    public LiveData<List<Participant>> getAllParticipants(long tripId) { return participantRepository.getAllParticipants(tripId); }
    public LiveData<List<Spending>> getSpendingsByTrip(long tripId) { return spendingRepository.getSpendingByTrip(tripId); }
    public List<Participation> getParticipationBySpending(long spendingId) { return participationRepository.getParticipationBySpending(spendingId); }
    public Participant getParticipant(long participantId) { return participantRepository.getParticipant(participantId); }

    public List<Participant> getParticipantsByParticipation(long spendingId) {

        List<Participation> lstParticipations = getParticipationBySpending(spendingId);
        List<Participant> lstParticipants = new ArrayList<>();

        for(Participation participation : lstParticipations) {
            lstParticipants.add(getParticipant(participation.getParticipantId()));
        }

        return lstParticipants;
    }

    public List<Pair<Participant,Double>> getBalanceForParticipant(long tripId, List<Spending> lstSpendings, List<Participant> lstParticipants) {

        List<Participant> lstSpendingParticipants = new ArrayList<>();

        List<Pair<Participant,Double>> result = new ArrayList<>();
        for (Participant participant: lstParticipants) {
            result.add(new Pair<>(participant, 0.0));
        }

        for(Spending spending : lstSpendings){
            lstSpendingParticipants.addAll(getParticipantsByParticipation(spending.getId()));
            int totalParticipant = lstSpendingParticipants.size();
            Double part = calculService.splitSpending(spending.getTotal(),totalParticipant);
            for (Pair<Participant, Double> participant: result) {
                if(lstSpendingParticipants.stream().anyMatch(p->p.getName().equals(participant.first.getName()))) {
                    if(spending.getPayerId() == participant.first.getId()) {
                        result.set(result.indexOf(participant), new Pair<>(participant.first, (participant.second + spending.getTotal()) - part));
                    } else {
                        result.set(result.indexOf(participant), new Pair<>(participant.first, participant.second - part));
                    }
                }
            }
        }

        return result;
    }
}
