package org.diiage.splittripwithyourfriends.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;


@Entity(tableName = "Spendings", foreignKeys = {
        @ForeignKey(entity = Trip.class, parentColumns = "tid", childColumns = "tripId"),
        @ForeignKey(entity = Participant.class, parentColumns = "pid", childColumns = "payerId"),},
        indices = {@Index("payerId"), @Index("tripId")})
public class Spending {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "spid")
    private long id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "total")
    private Double total;

    @ColumnInfo(name = "date")
    private Date date;

    @ColumnInfo(name = "photo")
    private String photo;

    @ColumnInfo(name = "payerId")
    private long payerId;

    @ColumnInfo(name = "tripId")
    private long tripId;


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public long getPayerId() {
        return payerId;
    }

    public void setPayerId(long payerId) {
        this.payerId = payerId;
    }

    public long getTripId() {
        return tripId;
    }

    public void setTripId(long tripId) {
        this.tripId = tripId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Spending(String libelle, Double montant, Date dateS, long payerId, long tripId) {
        this.name = libelle;
        this.total=montant;
        this.date=dateS;
        this.payerId=payerId;
        this.tripId= tripId;
    }

    public Spending(){}
}
