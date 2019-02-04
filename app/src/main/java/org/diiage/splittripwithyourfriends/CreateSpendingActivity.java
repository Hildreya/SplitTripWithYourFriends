package org.diiage.splittripwithyourfriends;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.diiage.splittripwithyourfriends.ui.createspending.CreateSpendingFragment;

public class CreateSpendingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_spending_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, CreateSpendingFragment.newInstance())
                    .commitNow();
        }
    }
}
