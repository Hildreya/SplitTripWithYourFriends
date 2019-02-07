package org.diiage.splittripwithyourfriends.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import org.diiage.splittripwithyourfriends.database.SplitTripDatabase;
import org.diiage.splittripwithyourfriends.entities.Participant;
import org.diiage.splittripwithyourfriends.entities.TripParticipantJoin;
import org.diiage.splittripwithyourfriends.interfaces.DaoParticipant;
import org.diiage.splittripwithyourfriends.interfaces.DaoTripParticipation;

import java.util.List;

public class ParticipantRepository {
    private DaoParticipant mParticipantDao;

    public ParticipantRepository(Application application) {
        SplitTripDatabase db = SplitTripDatabase.getDatabase(application);
        mParticipantDao= db.daoParticipant();
    }

    public LiveData<List<Participant>> getAllParticipants(){
        return mParticipantDao.getAllParticipants();
    }

    public LiveData<List<Participant>> getAllParticipants(long tripId){
        return mParticipantDao.getAllParticipants(tripId);
    }

    public List<Participant> getAllParticipantsNoLiveData(long tripId) {
        return mParticipantDao.getAllParticipantsNoLiveData(tripId);
    }

    public LiveData<List<Participant>> getUnregisteredParticipants(long tripId){
        return mParticipantDao.getUnregisteredParticipants(tripId);
    }

    public Participant getParticipantByName(String name){
        return mParticipantDao.findParticipantByName(name);
    }

    public Participant getParticipant(long partcipantId) {
        return mParticipantDao.findParticipantById(partcipantId);
    }

    public void insert(Participant participant){
        new insertAsyncTask(mParticipantDao).execute(participant);
    }

    private static class insertAsyncTask extends AsyncTask<Participant, Void, Void> {

        private DaoParticipant mAsyncTaskDao;

        insertAsyncTask(DaoParticipant dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Participant ... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
