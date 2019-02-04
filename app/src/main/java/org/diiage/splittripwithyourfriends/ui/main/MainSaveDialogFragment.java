package org.diiage.splittripwithyourfriends.ui.main;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import org.diiage.splittripwithyourfriends.R;
import org.diiage.splittripwithyourfriends.database.SplitTripDatabase;
import org.diiage.splittripwithyourfriends.entities.Trip;
import org.diiage.splittripwithyourfriends.interfaces.DaoTrip;

public class MainSaveDialogFragment extends DialogFragment {
    private Context context;
    private String tripNameExtra;
    private long tripIdExtra;

    public static MainSaveDialogFragment newInstance(final String tripName, long tripId){
        MainSaveDialogFragment fragment = new MainSaveDialogFragment();

        Bundle args = new Bundle();
        args.putString("tripNameToUpdate", tripName);
        args.putLong("tripIdToUpdate", tripId);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_trip, null);
        Bundle args = getArguments();
        tripNameExtra = args.getString("tripNameToUpdate");
        tripIdExtra = args.getLong("tripIdToUpdate");

        final EditText tripEditText = view.findViewById(R.id.etTripName);
        if (tripNameExtra != null) {
            tripEditText.setText(tripNameExtra);
            tripEditText.setSelection(tripNameExtra.length());
        }

        alertDialogBuilder.setView(view)
                .setTitle(getString(R.string.dialog_trip_title))
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveTrip(tripEditText.getText().toString());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        return alertDialogBuilder.create();
    }

    private void saveTrip(String tripName) {
        if (TextUtils.isEmpty(tripName)) {
            return;
        }
        context = getActivity().getApplicationContext();
        SplitTripDatabase db = SplitTripDatabase.getDatabase(context);
        DaoTrip tripDao = db.daoTrip();

        if (tripNameExtra != null) {
            // clicked on item row -> update
            Trip tripToUpdate = tripDao.findMovieById(tripIdExtra);
            if (tripToUpdate != null) {
                if (!tripToUpdate.getName().equals(tripName)) {
                    tripToUpdate.setName(tripName);
                    tripDao.update(tripToUpdate);
                }
            }
        }
    }
}
