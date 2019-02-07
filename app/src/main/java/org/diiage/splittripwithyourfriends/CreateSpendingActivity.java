package org.diiage.splittripwithyourfriends;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.diiage.splittripwithyourfriends.databinding.CreateSpendingActivityBinding;
import org.diiage.splittripwithyourfriends.ui.addparticipant.AddParticipantFragment;
import org.diiage.splittripwithyourfriends.ui.createspending.CreateSpendingFragment;

public class CreateSpendingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CreateSpendingActivityBinding binding = DataBindingUtil.setContentView(this,R.layout.create_spending_activity);
        if (savedInstanceState == null) {
            Bundle args = getIntent().getExtras();
            Bundle b = new Bundle();
            b.putLong("ParamTripId", args.getLong("ParamTripId"));
            b.putString("ParamTripName",args.getString("ParamTripName") );
            Log.d("TripId", " Value CreateSpending :"+args.getLong("ParamTripId"));
            CreateSpendingFragment fragment = CreateSpendingFragment.newInstance();
            fragment.setArguments(b);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commitNow();
        }
    }
}
