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
        //setContentView(R.layout.create_trip_activity);
        binding.setStrBeginDate("Date de début");
        binding.setStrEndDate("Date de fin");
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, CreateTripFragment.newInstance())
                    .commitNow();
        }
    }

    public void showDatePickerBegin(View v) {
        DialogFragment newFragment = new CreateTripFragment();
        newFragment.show(getSupportFragmentManager(),"datePickerBegin");
        //binding.textViewbeginDate.setText("29/01/2019");
    }

    public void showDatePickerEnd(View v) {
        DialogFragment newFragment = new CreateTripFragment();
        newFragment.show(getSupportFragmentManager(),"datePickerEnd");
        //binding.textViewEndDate.setText("29/02/2019");
    }
}
