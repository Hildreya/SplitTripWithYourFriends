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
    private DaoTripParticipation mTripParticipantDao;

    public ParticipantRepository(Application application) {
        SplitTripDatabase db = SplitTripDatabase.getDatabase(application);
        mParticipantDao= db.daoParticipant();
        mTripParticipantDao = db.daoTripParticipant();
    }

    public LiveData<List<Participant>> getAllParticipants(){
        return mParticipantDao.getAllParticipants();
    }

    public LiveData<List<Participant>> getAllParticipants(long tripId){
        return mParticipantDao.getAllParticipants(tripId);
    }

    public LiveData<List<Participant>> getUnregisteredParticipants(long tripId){
        return mParticipantDao.getUnregisteredParticipants(tripId);
    }

    public void insert(Participant participant){
        new insertAsyncTask(mParticipantDao).execute(participant);
    }

    public void registerParticipant(long participantId, long tripId) {
        TripParticipantJoin tripParticipantJoin = new TripParticipantJoin(tripId,participantId);
        mTripParticipantDao.insert(tripParticipantJoin);
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
