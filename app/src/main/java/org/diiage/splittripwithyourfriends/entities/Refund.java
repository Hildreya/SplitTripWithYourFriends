package org.diiage.splittripwithyourfriends.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Refunds",
        foreignKeys = { @ForeignKey(entity = Trip.class, parentColumns = "tid", childColumns = "tripId"),
        @ForeignKey(entity = Participant.class, parentColumns = "pid", childColumns = "emetteurId"),
        @ForeignKey(entity = Participant.class, parentColumns = "pid", childColumns = "receveurId")},
        indices = {@Index("emetteurId"), @Index("receveurId"), @Index("tripId")})
public class Refund {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rid")
    private long id;

    @ColumnInfo(name = "emetteurId")
    private long emetteurId;

    @ColumnInfo(name = "receveurId")
    private long receveurId;

    @ColumnInfo(name = "amount")
    private String amount;

    @ColumnInfo(name = "tripId")
    private long tripId;

    public long getEmetteurId() {
        return emetteurId;
    }

    public void setEmetteurId(long emetteurId) {
        this.emetteurId = emetteurId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String montant) {
        this.amount = montant;
    }

    public long getReceveurId() {
        return receveurId;
    }
    public void setReceveurId(long receveurId) {
        this.receveurId = receveurId;
    }

    public long getTripId() {
        return tripId;
    }
    public void setTripId(long tripId) {
        this.tripId = tripId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
