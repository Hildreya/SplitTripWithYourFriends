package org.diiage.splittripwithyourfriends.interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import org.diiage.splittripwithyourfriends.entities.TripParticipantJoin;

import java.util.List;

@Dao
public interface DaoTripParticipation {
    @Query("SELECT * FROM tripparticipant WHERE participantId = :id LIMIT 1")
    TripParticipantJoin findTripParticipantById(int id);

    @Insert(onConflict = OnConflictStrategy.FAIL)
    long insert(TripParticipantJoin articipant);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TripParticipantJoin... participants);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(TripParticipantJoin participant);

    @Query("DELETE FROM tripparticipant")
    void deleteAll();

    @Query("DELETE FROM tripparticipant WHERE tripId = :id")
    void deleteWithTripId(long id);

    @Query("DELETE FROM tripparticipant WHERE participantId = :id")
    void deleteWithParticipantId(long id);

    @Delete
    void delete(TripParticipantJoin tripParticipantJoin);

    @Query("SELECT * FROM tripparticipant")
    LiveData<List<TripParticipantJoin>> getAllTripParticipants();
}
