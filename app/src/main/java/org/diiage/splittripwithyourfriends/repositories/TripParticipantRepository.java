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

public class TripParticipantRepository {
    private DaoTripParticipation mTripParticipantDao;

    public TripParticipantRepository(Application application) {
        SplitTripDatabase db = SplitTripDatabase.getDatabase(application);
        mTripParticipantDao = db.daoTripParticipant();
    }

    public LiveData<List<TripParticipantJoin>> getAllTripParticipants(){
        return mTripParticipantDao.getAllTripParticipants();
    }

    public void insert(TripParticipantJoin tripParticipantJoin){
        new insertAsyncTask(mTripParticipantDao).execute(tripParticipantJoin);
    }

    public void registerParticipant(long participantId, long tripId) {
        TripParticipantJoin tripParticipantJoin = new TripParticipantJoin(tripId,participantId);
        mTripParticipantDao.insert(tripParticipantJoin);
    }

    private static class insertAsyncTask extends AsyncTask<TripParticipantJoin, Void, Void> {

        private DaoTripParticipation mAsyncTaskDao;

        insertAsyncTask(DaoTripParticipation dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TripParticipantJoin ... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
