package org.diiage.splittripwithyourfriends;

import android.databinding.DataBindingUtil;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.diiage.splittripwithyourfriends.databinding.CreateTripActivityBinding;
import org.diiage.splittripwithyourfriends.ui.createtrip.CreateTripFragment;

public class CreateTripActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CreateTripActivityBinding binding = DataBindingUtil.setContentView(this,R.layout.create_trip_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, CreateTripFragment.newInstance())
                    .commitNow();
        }
    }
}
