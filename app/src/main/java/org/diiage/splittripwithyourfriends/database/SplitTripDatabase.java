package org.diiage.splittripwithyourfriends.database;

        import android.arch.persistence.db.SupportSQLiteDatabase;
        import android.arch.persistence.room.Database;
        import android.arch.persistence.room.Room;
        import android.arch.persistence.room.RoomDatabase;
        import android.content.Context;
        import android.os.AsyncTask;
        import android.support.annotation.NonNull;

        import org.diiage.splittripwithyourfriends.interfaces.DaoTrip;
        import org.diiage.splittripwithyourfriends.entities.*;

@Database(entities = {Trip.class, Statut.class, Participant.class, TripParticipantJoin.class,
        Participation.class,Spending.class,Payment.class, Refund.class}, version = 1)
public abstract class SplitTripDatabase extends RoomDatabase {
    public abstract DaoTrip daoAccess();

    private static volatile SplitTripDatabase INSTANCE;

    //Singleton
    public static SplitTripDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SplitTripDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SplitTripDatabase.class, "SplitTrip_database").addCallback(sTripDatabaseCallBack)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sTripDatabaseCallBack = new RoomDatabase.Callback(){

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final DaoTrip mDao;

        PopulateDbAsync(SplitTripDatabase db) {
            mDao = db.daoAccess();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            Trip t = new Trip("Romane Trip");
            mDao.insertTrip(t);
            t = new Trip("Vacances été");
            mDao.insertTrip(t);
            return null;
        }
    }

}