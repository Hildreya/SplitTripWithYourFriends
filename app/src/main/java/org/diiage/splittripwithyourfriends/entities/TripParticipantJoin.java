package org.diiage.splittripwithyourfriends.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

@Entity(tableName = "TripParticipant",
        primaryKeys = { "tripId", "participantId" },
        foreignKeys = {
                @ForeignKey(entity = Trip.class,
                        parentColumns = "tid",
                        childColumns = "tripId"),
                @ForeignKey(entity = Participant.class,
                        parentColumns = "pid",
                        childColumns = "participantId")
        },
        indices = {@Index("tripId"), @Index("participantId")})
public class TripParticipantJoin {

    @ColumnInfo(name="tripId")
    private long tripId;

    @ColumnInfo(name = "participantId")
    private long participantId;

    public long getTripId() {
            return tripId;
    }

    public long getParticipantId() {
            return participantId;
    }

    public TripParticipantJoin(long tripId, long participantId) {
        this.tripId = tripId;
        this.participantId = participantId;
    }


}
