package org.diiage.splittripwithyourfriends.interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import org.diiage.splittripwithyourfriends.entities.Spending;

import java.util.List;

@Dao
public interface DaoSpending {
    @Query("SELECT * FROM spendings WHERE spid = :id LIMIT 1")
    Spending findSpendingById(int id);

    @Query("SELECT * FROM spendings WHERE name = :name LIMIT 1")
    Spending findSpendingByName(String name);

    @Insert(onConflict = OnConflictStrategy.FAIL)
    long insert(Spending spending);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Spending... spendings);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Spending spending);

    @Query("DELETE FROM spendings")
    void deleteAll();

    @Query("SELECT * FROM spendings ORDER BY name ASC")
    LiveData<List<Spending>> getAllSpendings();

    @Query("select * from spendings where tripId= :tripId")
    LiveData<List<Spending>> getSpendingByTrip(long tripId);
}
