package org.diiage.splittripwithyourfriends.database;

        import android.arch.persistence.db.SupportSQLiteDatabase;
        import android.arch.persistence.room.Database;
        import android.arch.persistence.room.Room;
        import android.arch.persistence.room.RoomDatabase;
        import android.arch.persistence.room.TypeConverters;
        import android.content.Context;
        import android.os.AsyncTask;
        import android.support.annotation.NonNull;

        import org.diiage.splittripwithyourfriends.interfaces.DaoStatut;
        import org.diiage.splittripwithyourfriends.interfaces.DaoTrip;
        import org.diiage.splittripwithyourfriends.entities.*;

@Database(entities = {Trip.class, Statut.class, Participant.class, TripParticipantJoin.class,
        Participation.class,Spending.class,Payment.class, Refund.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class SplitTripDatabase extends RoomDatabase {
    public abstract DaoTrip daoAccess();
    public abstract DaoStatut daoStatut();

    private static volatile SplitTripDatabase INSTANCE;

    //Singleton
    public static SplitTripDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SplitTripDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SplitTripDatabase.class, "SplitTrip_database").addCallback(sTripDatabaseCallBack)
                            .fallbackToDestructiveMigration()
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
        private final DaoStatut sDao;

        PopulateDbAsync(SplitTripDatabase db) {
            mDao = db.daoAccess();
            sDao= db.daoStatut();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            Statut statutV = new Statut("VALIDE");
            Statut statutC = new Statut("CLOS");
            Statut statutA = new Statut("ANNULE");
            Trip tR = new Trip("Romane Trip",(int) sDao.insert(statutV));
            Trip tM = new Trip("Matthew Trip",(int) sDao.insert(statutV));
            Trip tS = new Trip("Summer Trip",(int) sDao.insert(statutC));
            Trip tE = new Trip("English Trip",(int) sDao.insert(statutA));
            mDao.insert(tR,tM,tS,tE);
            return null;
        }
    }

}