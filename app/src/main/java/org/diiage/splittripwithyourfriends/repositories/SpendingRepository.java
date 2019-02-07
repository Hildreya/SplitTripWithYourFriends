package org.diiage.splittripwithyourfriends.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import org.diiage.splittripwithyourfriends.database.SplitTripDatabase;
import org.diiage.splittripwithyourfriends.entities.Participant;
import org.diiage.splittripwithyourfriends.entities.Participation;
import org.diiage.splittripwithyourfriends.entities.Spending;
import org.diiage.splittripwithyourfriends.interfaces.DaoParticipant;
import org.diiage.splittripwithyourfriends.interfaces.DaoSpending;

import java.util.List;

public class SpendingRepository {
    private DaoSpending mSpendingDao;

    public SpendingRepository(Application application) {
        SplitTripDatabase db = SplitTripDatabase.getDatabase(application);
        mSpendingDao= db.daoSpending();
    }

    public LiveData<List<Spending>> getAllSpendings(){
        return mSpendingDao.getAllSpendings();
    }

    public LiveData<List<Spending>> getSpendingByTrip(long tripId){
        return mSpendingDao.getSpendingByTrip(tripId);
    }

    public int getNbSpendingOnTrip(long tripId) {
        return mSpendingDao.getNbSpendingOnTrip(tripId);
    }

//    public LiveData<List<Spending>> getSpendingByParticipation(long participationId, long tripId){
//        LiveData<List<Spending>> spendings = mSpendingDao.getSpendingByTrip(tripId);
//        spendings.observe();
//    }

    public long insertAndRetrieveId(Spending spending) {
        return mSpendingDao.insert(spending);
    }

    public void insert(Spending spending){
        new insertAsyncTask(mSpendingDao).execute(spending);
    }

    private static class insertAsyncTask extends AsyncTask<Spending, Void, Void> {

        private DaoSpending mAsyncTaskDao;

        insertAsyncTask(DaoSpending dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Spending ... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
