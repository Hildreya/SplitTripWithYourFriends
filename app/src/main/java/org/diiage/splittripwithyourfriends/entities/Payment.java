package org.diiage.splittripwithyourfriends.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Payments", foreignKeys = {
        @ForeignKey(entity = Spending.class, parentColumns = "spid", childColumns = "spendingId"),
        @ForeignKey(entity = Participant.class, parentColumns = "pid", childColumns = "payerId")
}, indices = {@Index("payerId"), @Index("spendingId")})
public class Payment {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "payId")
    private long id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "montant")
    private  String montant;

    @ColumnInfo(name = "date")
    private Date date;

    @ColumnInfo(name = "payerId")
    private long payerId;

    @ColumnInfo(name = "spendingId")
    private long spendingId;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getPayerId() {
        return payerId;
    }

    public void setPayerId(long payerId) {
        this.payerId = payerId;
    }

    public long getSpendingId() {
        return spendingId;
    }

    public void setSpendingId(long spendingId) {
        this.spendingId = spendingId;
    }

    public void setId(long id) {
        this.id = id;
    }
}
