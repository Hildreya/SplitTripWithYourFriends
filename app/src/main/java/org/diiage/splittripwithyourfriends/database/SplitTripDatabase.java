package org.diiage.splittripwithyourfriends.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import org.diiage.splittripwithyourfriends.interfaces.DaoParticipant;
import org.diiage.splittripwithyourfriends.interfaces.DaoParticipation;
import org.diiage.splittripwithyourfriends.interfaces.DaoSpending;
import org.diiage.splittripwithyourfriends.interfaces.DaoStatut;
import org.diiage.splittripwithyourfriends.interfaces.DaoTrip;
import org.diiage.splittripwithyourfriends.entities.*;
import org.diiage.splittripwithyourfriends.interfaces.DaoTripParticipation;

@Database(entities = {Trip.class, Statut.class, Participant.class, TripParticipantJoin.class,
        Participation.class,Spending.class,Payment.class, Refund.class}, version = 8)
@TypeConverters({Converters.class})
public abstract class SplitTripDatabase extends RoomDatabase {
    public abstract DaoTrip daoTrip();
    public abstract DaoStatut daoStatut();
    public abstract DaoParticipant daoParticipant();
    public abstract DaoTripParticipation daoTripParticipant();
    public abstract DaoSpending daoSpending();
    public abstract DaoParticipation daoParticipation();

    private static Context _context;
    private static volatile SplitTripDatabase INSTANCE;

    //Singleton
    public static SplitTripDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SplitTripDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SplitTripDatabase.class, "SplitTrip_database")
                            .addCallback(sTripDatabaseCallBack)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        _context = context;
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
        private final DaoTripParticipation tpDao;
        private final DaoSpending spDao;
        private final DaoParticipation partDao;

        PopulateDbAsync(SplitTripDatabase db) {
            tDao = db.daoTrip();
            sDao= db.daoStatut();
            pDao = db.daoParticipant();
            tpDao = db.daoTripParticipant();
            spDao=db.daoSpending();
            partDao=db.daoParticipation();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            SharedPreferences mPreferences = _context.getSharedPreferences("first_time", Context.MODE_PRIVATE);
            Boolean firstTime = mPreferences.getBoolean("firstTime", true);

            if (firstTime) {
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putBoolean("firstTime", false);
                editor.commit();


                tpDao.deleteAll();
                partDao.deleteAll();
                spDao.deleteAll();
                pDao.deleteAll();
                tDao.deleteAll();
                sDao.deleteAll();

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
                String stName1 = sDao.findStatutById(sidOne).getName();
                String stName2 = sDao.findStatutById(sidOne).getName();
                String stName3 = sDao.findStatutById(sidOne).getName();

                Trip tR = new Trip("Romane Trip", sidOne);
                Trip tM = new Trip("Matthew Trip", sidOne);
                Trip tS = new Trip("Summer Trip", sidTwo);
                Trip tE = new Trip("English Trip", sidThree);

                //Récupération des id des trips insérés
                int tidOne = (int) tDao.insert(tR);
                int tidTwo = (int) tDao.insert(tM);
                int tidThree = (int) tDao.insert(tS);
                int tidFour = (int) tDao.insert(tE);

                //Récupération des id des participants insérés
                int pidOne = (int) pDao.insert(p1);
                int pidTwo = (int) pDao.insert(p2);
                int pidThree = (int) pDao.insert(p3);
                int pidFour = (int) pDao.insert(p4);
                int pidFive = (int) pDao.insert(p5);

                TripParticipantJoin tpR1 = new TripParticipantJoin(tidOne, pidOne);
                TripParticipantJoin tpR2 = new TripParticipantJoin(tidOne, pidTwo);
                TripParticipantJoin tpR3 = new TripParticipantJoin(tidOne, pidFive);

                TripParticipantJoin tpM1 = new TripParticipantJoin(tidTwo, pidOne);
                TripParticipantJoin tpM2 = new TripParticipantJoin(tidTwo, pidTwo);
                TripParticipantJoin tpM3 = new TripParticipantJoin(tidTwo, pidThree);
                TripParticipantJoin tpM4 = new TripParticipantJoin(tidTwo, pidFour);
                TripParticipantJoin tpM5 = new TripParticipantJoin(tidTwo, pidFive);

                TripParticipantJoin tpS1 = new TripParticipantJoin(tidThree, pidThree);

                tpDao.insert(tpR1, tpR2, tpR3, tpM1, tpM2, tpM3, tpM4, tpM5, tpS1);

                Spending s1 = new Spending("Courses", 150.45, null, pidFive, tidOne);
                Spending s2 = new Spending("Location", 860.60, null, pidOne, tidOne);

                //Récupération des id des participants insérés
                int spidOne = (int) spDao.insert(s1);
                int spidTwo = (int) spDao.insert(s2);

                Participation part1 = new Participation(spidOne, pidOne);
                Participation part2 = new Participation(spidOne, pidFive);
                Participation part3 = new Participation(spidTwo, pidOne);
                Participation part4 = new Participation(spidTwo, pidTwo);
                Participation part5 = new Participation(spidTwo, pidFive);

                partDao.insert(part1, part2, part3, part4, part5);

            }

            return null;
        }
    }

}