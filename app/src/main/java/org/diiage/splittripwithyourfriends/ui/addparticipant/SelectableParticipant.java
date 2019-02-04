package org.diiage.splittripwithyourfriends.ui.addparticipant;

import android.support.annotation.NonNull;

import org.diiage.splittripwithyourfriends.entities.Participant;

public class SelectableParticipant extends Participant {

    private boolean isSelected = false;

    public SelectableParticipant(Participant participant, boolean isSelected) {
        super(participant.getName());
        this.isSelected = isSelected();
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
