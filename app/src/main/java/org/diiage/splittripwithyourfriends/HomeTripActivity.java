package org.diiage.splittripwithyourfriends;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import org.diiage.splittripwithyourfriends.databinding.HomeTripActivityBinding;
import org.diiage.splittripwithyourfriends.ui.hometrip.HomeTripFragment;

public class HomeTripActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeTripActivityBinding binding = DataBindingUtil.setContentView(this,R.layout.home_trip_activity);
        if (savedInstanceState == null) {
            Bundle args = getIntent().getExtras();
            Bundle b = new Bundle();
            b.putLong("ParamTripId", args.getLong("ParamTripId"));
            b.putString("ParamTripName",args.getString("ParamTripName") );
            Log.d("TripId", " Value :"+args.getLong("ParamTripId"));
            HomeTripFragment fragment = HomeTripFragment.newInstance();
            fragment.setArguments(b);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commitNow();
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
        switch (item.getItemId()) {
            case R.id.action_add_participant:
                // Go to add participant activity
                Intent i = new Intent(this, AddParticipantActivity.class);
                startActivity(i);
                return true;
            default:
                return false;
        }
    }
}
