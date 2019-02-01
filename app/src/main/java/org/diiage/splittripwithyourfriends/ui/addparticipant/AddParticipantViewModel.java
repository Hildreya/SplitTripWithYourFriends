package org.diiage.splittripwithyourfriends.ui.addparticipant;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import org.diiage.splittripwithyourfriends.entities.Participant;

public class AddParticipantViewModel extends AndroidViewModel {


    public AddParticipantViewModel(@NonNull Application application) {
        super(application);
    }

    public void insert(Participant participant) {
        // TODO insert avec repository
    }
}
