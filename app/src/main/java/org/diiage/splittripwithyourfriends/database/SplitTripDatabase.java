package org.diiage.splittripwithyourfriends.database;

        import android.arch.persistence.db.SupportSQLiteDatabase;
        import android.arch.persistence.room.Database;
        import android.arch.persistence.room.Room;
        import android.arch.persistence.room.RoomDatabase;
        import android.arch.persistence.room.TypeConverters;
        import android.content.Context;
        import android.os.AsyncTask;
        import android.support.annotation.NonNull;

        import org.diiage.splittripwithyourfriends.interfaces.DaoParticipant;
        import org.diiage.splittripwithyourfriends.interfaces.DaoStatut;
        import org.diiage.splittripwithyourfriends.interfaces.DaoTrip;
        import org.diiage.splittripwithyourfriends.entities.*;
        import org.diiage.splittripwithyourfriends.repositories.TripRepository;

@Database(entities = {Trip.class, Statut.class, Participant.class, TripParticipantJoin.class,
        Participation.class,Spending.class,Payment.class, Refund.class}, version = 4)
@TypeConverters({Converters.class})
public abstract class SplitTripDatabase extends RoomDatabase {
    public abstract DaoTrip daoTrip();
    public abstract DaoStatut daoStatut();
    public abstract DaoParticipant daoParticipant();

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

        private final DaoTrip tDao;
        private final DaoStatut sDao;
        private final DaoParticipant pDao;

        PopulateDbAsync(SplitTripDatabase db) {
            tDao = db.daoTrip();
            sDao= db.daoStatut();
            pDao = db.daoParticipant();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            Statut statutV = new Statut("VALIDE");
            Statut statutC = new Statut("CLOS");
            Statut statutA = new Statut("ANNULE");

            Participant p1 = new Participant("Romane");
            Participant p2 = new Participant("Matthew");
            Participant p3 = new Participant("Baboon");
            Participant p4 = new Participant("Dylan");
            Participant p5 = new Participant("Teepi");

            //Récupération des id des statuts insérés
            int sidOne = (int) sDao.insert(statutV);
            int sidTwo = (int) sDao.insert(statutC);
            int sidThree = (int) sDao.insert(statutA);

            Trip tR = new Trip("Romane Trip",sidOne);
            Trip tM = new Trip("Matthew Trip",sidOne);
            Trip tS = new Trip("Summer Trip",sidTwo);
            Trip tE = new Trip("English Trip",sidThree);

            //Récupération des id des trips insérés
            int tidOne = (int) tDao.insert(tR);
            int tidTwo = (int) tDao.insert(tM);
            int tidThree = (int) tDao.insert(tS);
            int tidFour = (int) tDao.insert(tE);

            //Récupération des id des participants insérés
            int pidOne = (int) pDao.insert(p1);
            int pidTwo=(int) pDao.insert(p2);
            int pidThree=(int) pDao.insert(p3);
            int pidFour=(int) pDao.insert(p4);
            int pidFive=(int) pDao.insert(p5);

            TripParticipantJoin tpR1 = new TripParticipantJoin(tidOne, pidOne);
            TripParticipantJoin tpR2 = new TripParticipantJoin(tidOne, pidTwo);
            TripParticipantJoin tpR3 = new TripParticipantJoin(tidOne, pidFive);

            TripParticipantJoin tpM1 = new TripParticipantJoin(tidTwo, pidOne);
            TripParticipantJoin tpM2 = new TripParticipantJoin(tidTwo, pidTwo);
            TripParticipantJoin tpM3 = new TripParticipantJoin(tidTwo, pidThree);
            TripParticipantJoin tpM4 = new TripParticipantJoin(tidTwo, pidFour);
            TripParticipantJoin tpM5 = new TripParticipantJoin(tidTwo, pidFive);

            TripParticipantJoin tpS1 = new TripParticipantJoin(tidTwo, pidThree);
            return null;
        }
    }

}