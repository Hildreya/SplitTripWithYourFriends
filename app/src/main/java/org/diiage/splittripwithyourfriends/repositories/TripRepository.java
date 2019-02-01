package org.diiage.splittripwithyourfriends.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.os.AsyncTask;

import org.diiage.splittripwithyourfriends.database.SplitTripDatabase;
import org.diiage.splittripwithyourfriends.entities.Trip;
import org.diiage.splittripwithyourfriends.interfaces.DaoTrip;

import java.util.ArrayList;
import java.util.List;

public class TripRepository {
    private DaoTrip mTripDao;
    private LiveData<List<Trip>> mAllTrips;

    public TripRepository(Application application) {
        SplitTripDatabase db = SplitTripDatabase.getDatabase(application);
        mTripDao= db.daoTrip();
        mAllTrips= mTripDao.getAllTrips();
    }

    public LiveData<List<Trip>> getAllTrips(){
        return mAllTrips;
    }

    public void insert(Trip trip){
        new insertAsyncTask(mTripDao).execute(trip);
    }

    private static class insertAsyncTask extends AsyncTask<Trip, Void, Void> {

        private DaoTrip mAsyncTaskDao;

        insertAsyncTask(DaoTrip dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Trip ... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
