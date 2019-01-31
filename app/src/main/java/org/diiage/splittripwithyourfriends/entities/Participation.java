package org.diiage.splittripwithyourfriends.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(tableName = "Participations",
        primaryKeys = { "spendingId", "participantId" },
        foreignKeys = {@ForeignKey(entity = Participant.class, parentColumns = "id",childColumns = "participantId"),
        @ForeignKey(entity = Spending.class, parentColumns = "id",childColumns = "spendingId")})
public class Participation {
    private int spendingId;
    private int participantId;

    public Participation(int spendingId, int participantId) {
        this.spendingId = spendingId;
        this.participantId = participantId;
    }

    public int getSpendingId() {
        return spendingId;
    }

    public int getParticipantId() {
        return participantId;
    }
}
