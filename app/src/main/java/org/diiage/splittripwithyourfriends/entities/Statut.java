package org.diiage.splittripwithyourfriends.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Statuts")
public class Statut {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "sid")
    private long id;

    @ColumnInfo(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }
    public Statut(@NonNull String name) {
        this.name = name;
    }
}
