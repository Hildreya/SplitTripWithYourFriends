package org.diiage.splittripwithyourfriends;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import org.diiage.splittripwithyourfriends.databinding.HomeTripActivityBinding;
import org.diiage.splittripwithyourfriends.entities.Spending;
import org.diiage.splittripwithyourfriends.ui.hometrip.HomeTripFragment;
import org.diiage.splittripwithyourfriends.ui.spending.SpendingsFragment;

public class HomeTripActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private boolean FRAGMENT_SHOWN = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeTripActivityBinding binding = DataBindingUtil.setContentView(this,R.layout.home_trip_activity);
        Bundle args = getIntent().getExtras();
        Bundle b = new Bundle();
        b.putLong("ParamTripId", args.getLong("ParamTripId"));
        b.putString("ParamTripName",args.getString("ParamTripName") );
        HomeTripFragment fragment = HomeTripFragment.newInstance();
        fragment.setArguments(b);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow();

        if (savedInstanceState == null) {

            Log.d("TripId", " Value :"+args.getLong("ParamTripId"));
            BottomNavigationView navigation = findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_participants:
                            FRAGMENT_SHOWN = true;
                            HomeTripFragment fragment = HomeTripFragment.newInstance();
                            fragment.setArguments(b);
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container, fragment)
                                    .commitNow();
                            return true;
                        case R.id.navigation_spendings:
                            FRAGMENT_SHOWN = false;
                            SpendingsFragment fragmentB = SpendingsFragment.newInstance();
                            fragmentB.setArguments(b);
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container, fragmentB)
                                    .commitNow();
                            return true;
                    }
                    return false;
                }
            });
        }
    }

    public void showAddMenu(View view) {
        PopupMenu popup = new PopupMenu(this, view);

        // This activity implements OnMenuItemClickListener
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.menu_add);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Bundle args = getIntent().getExtras();
        Bundle b = new Bundle();
        b.putLong("ParamTripId", args.getLong("ParamTripId"));
        b.putString("ParamTripName",args.getString("ParamTripName") );
        switch (item.getItemId()) {
            case R.id.action_add_participant:
                // Go to add participant activity
                Intent iAddParticipant = new Intent(this, AddParticipantActivity.class);
                iAddParticipant.putExtras(b);
                startActivity(iAddParticipant);
                return true;
            case R.id.action_create_spending:
                Intent iCreateParticipant = new Intent(this, CreateSpendingActivity.class);
                iCreateParticipant.putExtras(b);
                startActivity(iCreateParticipant);
                return true;
            default:
                return false;
        }
    }
}
