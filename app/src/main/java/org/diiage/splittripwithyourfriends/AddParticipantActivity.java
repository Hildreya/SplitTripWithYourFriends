package org.diiage.splittripwithyourfriends;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.diiage.splittripwithyourfriends.databinding.AddParticipantActivityBinding;
import org.diiage.splittripwithyourfriends.ui.addparticipant.AddParticipantFragment;
import org.diiage.splittripwithyourfriends.ui.hometrip.HomeTripFragment;

public class AddParticipantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AddParticipantActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.add_participant_activity);
        if (savedInstanceState == null) {
            Bundle args = getIntent().getExtras();
            Bundle b = new Bundle();
            b.putLong("ParamTripId", args.getLong("ParamTripId"));
            b.putString("ParamTripName",args.getString("ParamTripName") );
            Log.d("TripId", " Value AddParticipant :"+args.getLong("ParamTripId"));
            AddParticipantFragment fragment = AddParticipantFragment.newInstance();
            fragment.setArguments(b);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commitNow();
        }
    }
}
