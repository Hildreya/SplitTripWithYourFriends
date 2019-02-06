package org.diiage.splittripwithyourfriends.interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import org.diiage.splittripwithyourfriends.entities.Participant;

import java.util.List;

@Dao
public interface DaoParticipant {
    @Query("SELECT * FROM participants WHERE pid = :id LIMIT 1")
    Participant findParticipantById(long id);

    @Query("SELECT * FROM participants WHERE pid = :id LIMIT 1")
    LiveData<Participant> findParticipantByIdWithLiveData(long id);

    @Query("SELECT * FROM participants WHERE name = :name LIMIT 1")
    Participant findParticipantByName(String name);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Participant participant);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Participant... participants);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Participant participant);

    @Delete
    void delete(Participant participant);

    @Query("DELETE FROM participants")
    void deleteAll();

    @Query("SELECT * FROM participants")
    LiveData<List<Participant>> getAllParticipants();

    @Query("SELECT * FROM participants inner join tripparticipant on participants.pid= tripparticipant.participantId where tripId= :idTrip")
    LiveData<List<Participant>> getAllParticipants(long idTrip);

    @Query("SELECT * FROM participants inner join tripparticipant on participants.pid= tripparticipant.participantId where tripId= :idTrip")
    List<Participant> getAllParticipantsNoLiveData(long idTrip);

    @Query("SELECT pid, name FROM participants EXCEPT SELECT pid, name FROM participants inner join tripparticipant on participants.pid= tripparticipant.participantId where tripId= :idTrip")
    LiveData<List<Participant>> getUnregisteredParticipants(long idTrip);
}
