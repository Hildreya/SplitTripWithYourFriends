package org.diiage.splittripwithyourfriends.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

@Entity(tableName = "Participations",
        primaryKeys = { "spendingId", "participantId" },
        foreignKeys = {@ForeignKey(entity = Participant.class, parentColumns = "pid",childColumns = "participantId"),
        @ForeignKey(entity = Spending.class, parentColumns = "spid",childColumns = "spendingId")},
        indices = {@Index("participantId"), @Index("spendingId")})
public class Participation {
    private long spendingId;
    private long participantId;

    public Participation(long spendingId, long participantId) {
        this.spendingId = spendingId;
        this.participantId = participantId;
    }

    public long getSpendingId() {
        return spendingId;
    }

    public long getParticipantId() {
        return participantId;
    }
}
