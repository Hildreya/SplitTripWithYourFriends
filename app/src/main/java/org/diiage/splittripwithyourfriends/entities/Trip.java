package org.diiage.splittripwithyourfriends.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Entity(tableName = "Trips",
        foreignKeys = @ForeignKey(entity = Statut.class,
                parentColumns = "sid",
                childColumns = "statutId"),
        indices = {@Index("name"), @Index("statutId")})
public class Trip {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tid")
    private long id;

    @ColumnInfo(name = "name")
    @NonNull
    private String name;

    @ColumnInfo(name = "begin_date")
    private String beginDate;

    @ColumnInfo(name = "end_date")
    private String endDate;

    @ColumnInfo(name = "statutId")
    private long statutId;

    public Trip(String name, long statutId){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        this.name= name;
        this.statutId = statutId;
        this.beginDate = sdf.format(new Date());
        c.add(Calendar.DATE, 1);
        this.endDate = sdf.format(c.getTime());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public long getStatutId() {
        return statutId;
    }

    public void setStatutId(long statutId) {
        this.statutId = statutId;
    }
}

