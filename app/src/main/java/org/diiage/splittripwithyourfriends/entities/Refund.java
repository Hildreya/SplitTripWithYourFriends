package org.diiage.splittripwithyourfriends.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Refunds", foreignKeys = {
        @ForeignKey(entity = Trip.class, parentColumns = "tid", childColumns = "tripId"),
        @ForeignKey(entity = Participant.class, parentColumns = "id", childColumns = "emetteurId"),
        @ForeignKey(entity = Participant.class, parentColumns = "id", childColumns = "receveurId")
})
public class Refund {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "emetteurId")
    private int emetteurId;

    @ColumnInfo(name = "receveurId")
    private int receveurId;

    @ColumnInfo(name = "amount")
    private String amount;

    @ColumnInfo(name = "tripId")
    private int tripId;

    public int getEmetteurId() {
        return emetteurId;
    }

    public void setEmetteurId(int emetteurId) {
        this.emetteurId = emetteurId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String montant) {
        this.amount = montant;
    }

    public int getReceveurId() {
        return receveurId;
    }
    public void setReceveurId(int receveurId) {
        this.receveurId = receveurId;
    }

    public int getTripId() {
        return tripId;
    }
    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
