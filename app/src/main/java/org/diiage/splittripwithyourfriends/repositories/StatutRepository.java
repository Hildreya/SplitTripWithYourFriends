package org.diiage.splittripwithyourfriends.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import org.diiage.splittripwithyourfriends.database.SplitTripDatabase;
import org.diiage.splittripwithyourfriends.entities.Statut;
import org.diiage.splittripwithyourfriends.interfaces.DaoStatut;

import java.util.List;

public class StatutRepository {
    private DaoStatut daoStatut;
    private LiveData<List<Statut>> mAllStatuts;

    public StatutRepository(Application application) {
        SplitTripDatabase db = SplitTripDatabase.getDatabase(application);
        daoStatut= db.daoStatut();
        mAllStatuts= daoStatut.getAllStatuts();
    }

    public LiveData<List<Statut>> getAllStatuts(){
        return mAllStatuts;
    }

    public void insert(Statut statut){
        new insertAsyncTask(daoStatut).execute(statut);
    }

    private static class insertAsyncTask extends AsyncTask<Statut, Void, Void> {

        private DaoStatut mAsyncTaskDao;

        insertAsyncTask(DaoStatut dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Statut ... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
