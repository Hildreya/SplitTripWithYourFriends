package org.diiage.splittripwithyourfriends;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.diiage.splittripwithyourfriends.ui.addparticipant.AddParticipantFragment;

public class AddParticipantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_participant_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, AddParticipantFragment.newInstance())
                    .commitNow();
        }
    }
}
