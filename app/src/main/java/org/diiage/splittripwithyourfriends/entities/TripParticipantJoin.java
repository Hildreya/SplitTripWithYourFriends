package org.diiage.splittripwithyourfriends.entities;

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
        private long tripId;
        private long participantId;

        public TripParticipantJoin(long tripId, long participantId) {
                this.tripId = tripId;
                this.participantId = participantId;
        }

        public long getTripId() {
                return tripId;
        }

        public long getParticipantId() {
                return participantId;
        }
}
