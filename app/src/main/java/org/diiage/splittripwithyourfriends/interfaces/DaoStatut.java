package org.diiage.splittripwithyourfriends.interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import org.diiage.splittripwithyourfriends.entities.Statut;

import java.util.List;

@Dao
public interface DaoStatut {
    @Query("SELECT * FROM statuts WHERE sid = :id LIMIT 1")
    Statut findStatutById(int id);

    @Query("SELECT * FROM statuts WHERE name = :name LIMIT 1")
    Statut findStatutByName(String name);

    @Insert(onConflict = OnConflictStrategy.FAIL)
    long insert(Statut statut);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Statut... statuts);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Statut statut);

    @Query("DELETE FROM statuts")
    void deleteAll();

    @Query("SELECT * FROM statuts ORDER BY name ASC")
    LiveData<List<Statut>> getAllStatuts();

    @Query("SELECT name FROM statuts WHERE sid= :id LIMIT 1")
    String getStatutNameById(long id);
}
