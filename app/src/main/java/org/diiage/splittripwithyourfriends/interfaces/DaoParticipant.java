package org.diiage.splittripwithyourfriends.interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import org.diiage.splittripwithyourfriends.entities.Participant;
import org.diiage.splittripwithyourfriends.entities.Statut;

import java.util.List;

@Dao
public interface DaoParticipant {
    @Query("SELECT * FROM participants WHERE pid = :id LIMIT 1")
    Participant findParticipantById(int id);

    @Query("SELECT * FROM participants WHERE name = :name LIMIT 1")
    Participant findParticipantByName(String name);

    @Insert(onConflict = OnConflictStrategy.FAIL)
    long insert(Participant articipant);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Participant... participants);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Participant participant);

    @Query("DELETE FROM participants")
    void deleteAll();

    @Query("SELECT * FROM participants ORDER BY name ASC")
    LiveData<List<Participant>> getAllParticipants();
}
