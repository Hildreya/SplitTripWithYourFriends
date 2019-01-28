package org.diiage.splittripwithyourfriends;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.diiage.splittripwithyourfriends.ui.createtrip.CreateTripFragment;

public class CreateTripActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_trip_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, CreateTripFragment.newInstance())
                    .commitNow();
        }
    }
}
