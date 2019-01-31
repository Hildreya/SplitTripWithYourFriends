package org.diiage.splittripwithyourfriends.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(tableName = "TripParticipant",
        primaryKeys = { "tripId", "participantId" },
        foreignKeys = {
                @ForeignKey(entity = Trip.class,
                        parentColumns = "id",
                        childColumns = "tripId"),
                @ForeignKey(entity = Participant.class,
                        parentColumns = "id",
                        childColumns = "participantId")
        })
public class TripParticipantJoin {
        private int tripId;
        private int participantId;

        public TripParticipantJoin(int tripId, int participantId) {
                this.tripId = tripId;
                this.participantId = participantId;
        }

        public int getTripId() {
                return tripId;
        }

        public int getParticipantId() {
                return participantId;
        }
}
