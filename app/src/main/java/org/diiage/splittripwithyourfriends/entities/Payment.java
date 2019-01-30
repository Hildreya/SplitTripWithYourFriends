package org.diiage.splittripwithyourfriends.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "Payments", foreignKeys = {
        @ForeignKey(entity = Spending.class, parentColumns = "id", childColumns = "spendingId"),
        @ForeignKey(entity = Participant.class, parentColumns = "id", childColumns = "payerId")
})
public class Payment {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "montant")
    private  String montant;

    @ColumnInfo(name = "date")
    @TypeConverters(DateConverter.class)
    private Date date;

    @ColumnInfo(name = "payerId")
    private int payerId;

    @ColumnInfo(name = "spendingId")
    private int spendingId;

    public int getId() {
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

    public int getPayerId() {
        return payerId;
    }

    public void setPayerId(int payerId) {
        this.payerId = payerId;
    }

    public int getSpendingId() {
        return spendingId;
    }

    public void setSpendingId(int spendingId) {
        this.spendingId = spendingId;
    }

    public void setId(int id) {
        this.id = id;
    }
}
