package org.diiage.splittripwithyourfriends.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.sql.Date;

@Entity(tableName = "Trips", foreignKeys = @ForeignKey(entity = Statut.class, parentColumns = "id", childColumns = "statutId"))
public class Trip {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    @NonNull
    private String name;

    /*@ColumnInfo(name = "begin_date")
    @TypeConverters(DateConverter.class)
    private Date beginDate;

    @ColumnInfo(name = "end_date")
    @TypeConverters(DateConverter.class)
    private Date endDate;*/

    @ColumnInfo(name = "statutId")
    private int statutId;

    public Trip(){

    }
    public Trip(String name){
        this.name= name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    /*public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }*/
    public int getStatutId() {
        return statutId;
    }

    public void setStatutId(int statutId) {
        this.statutId = statutId;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*public String getTripIdId() { return tripId; }
    public void setTripId(String tripId) { this.tripId = tripId; }
    public String getTripNameName() { return tripName; }
    public void setTripNameName (String tripName) { this.tripName = tripName; }*/
}

