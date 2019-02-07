package org.diiage.splittripwithyourfriends.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Participants", indices = {@Index(value = "name", unique = true)})
public class Participant {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pid")
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

    public Participant(@NonNull String name) {
        this.name = name;
    }

    public Participant(@NonNull String name, long pid) {
        this.name = name;
        this.id = pid;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name;
    }
}
