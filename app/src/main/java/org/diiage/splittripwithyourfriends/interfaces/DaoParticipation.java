package org.diiage.splittripwithyourfriends.interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import org.diiage.splittripwithyourfriends.entities.Participation;
import org.diiage.splittripwithyourfriends.entities.Spending;

import java.util.List;

@Dao
public interface DaoParticipation {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    long insert(Participation participation);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Participation... participations);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Participation participation);

    @Query("DELETE FROM participations")
    void deleteAll();

    @Query("SELECT * FROM participations")
    LiveData<List<Participation>> getAllParticipations();

    @Query("select * from participations where spendingId= :spendingId")
    LiveData<List<Participation>> getParticipationsBySpending(long spendingId);
}