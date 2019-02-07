package org.diiage.splittripwithyourfriends.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import org.diiage.splittripwithyourfriends.database.SplitTripDatabase;
import org.diiage.splittripwithyourfriends.entities.Participation;
import org.diiage.splittripwithyourfriends.entities.Spending;
import org.diiage.splittripwithyourfriends.interfaces.DaoParticipation;
import org.diiage.splittripwithyourfriends.interfaces.DaoSpending;

import java.util.List;

public class ParticipationRepository {
    private DaoParticipation mParticipationDao;

    public ParticipationRepository(Application application) {
        SplitTripDatabase db = SplitTripDatabase.getDatabase(application);
        mParticipationDao= db.daoParticipation();
    }

    public LiveData<List<Participation>> getAllParticipations(){
        return mParticipationDao.getAllParticipations();
    }

    public List<Participation> getParticipationBySpending(long spendingId){
        return mParticipationDao.getParticipationsBySpending(spendingId);
    }

    public long insertAndRetrieveId(Participation participation) {
        return mParticipationDao.insert(participation);
    }

    public void insert(Participation participation){
        new insertAsyncTask(mParticipationDao).execute(participation);
    }

    private static class insertAsyncTask extends AsyncTask<Participation, Void, Void> {

        private DaoParticipation mAsyncTaskDao;

        insertAsyncTask(DaoParticipation dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Participation ... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
