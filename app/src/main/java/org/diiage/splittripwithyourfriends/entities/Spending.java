package org.diiage.splittripwithyourfriends.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;


@Entity(tableName = "Spendings", foreignKeys = {
        @ForeignKey(entity = Trip.class, parentColumns = "tid", childColumns = "tripId"),
        @ForeignKey(entity = Participant.class, parentColumns = "id", childColumns = "payerId")
})
public class Spending {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "total")
    private String total;

    @ColumnInfo(name = "date")
    private Date date;

    @ColumnInfo(name = "photo")
    private String photo;

    @ColumnInfo(name = "payerId")
    private int payerId;

    @ColumnInfo(name = "tripId")
    private int tripId;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
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

    public int getPayerId() {
        return payerId;
    }

    public void setPayerId(int payerId) {
        this.payerId = payerId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public void setId(int id) {
        this.id = id;
    }
}
