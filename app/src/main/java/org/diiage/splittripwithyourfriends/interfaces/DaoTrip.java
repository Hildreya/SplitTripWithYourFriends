package org.diiage.splittripwithyourfriends.interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import org.diiage.splittripwithyourfriends.entities.*;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DaoTrip {
    @Insert (onConflict = OnConflictStrategy.FAIL)
    long insert(Trip trip);

    @Insert(onConflict = OnConflictStrategy.FAIL)
    void insert(Trip... trips);

    @Query("SELECT * FROM trips WHERE name = :title")
    Trip findTripByTitle(String title);

    @Query("SELECT * FROM trips WHERE tid = :id")
    Trip findTripById(long id);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Trip trip);

    @Delete
    void delete(Trip trip);

    @Query("DELETE FROM Trips")
    void deleteAll();

    @Query("SELECT * FROM Trips")
    LiveData<List<Trip>> getAllTrips();
}
