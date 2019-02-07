package org.diiage.splittripwithyourfriends.ui.main;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import org.diiage.splittripwithyourfriends.R;
import org.diiage.splittripwithyourfriends.database.SplitTripDatabase;
import org.diiage.splittripwithyourfriends.entities.Trip;
import org.diiage.splittripwithyourfriends.interfaces.DaoTrip;
import org.diiage.splittripwithyourfriends.interfaces.DaoTripParticipation;

public class MainDeleteDialogFragment extends DialogFragment {

    private Context context;
    private String tripNameExtra;
    private long tripIdExtra;

    public static MainDeleteDialogFragment newInstance(final String tripName, long tripId){
        MainDeleteDialogFragment fragment = new MainDeleteDialogFragment();
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
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_delete_trip, null);
        Bundle args = getArguments();
        tripNameExtra = args.getString("tripNameToUpdate");
        tripIdExtra = args.getLong("tripIdToUpdate");

        alertDialogBuilder.setView(view)
                .setTitle(getString(R.string.dialog_trip_title))
                .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteTrip(MainDeleteDialogFragment.this, tripNameExtra);
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

    public static void deleteTrip(MainDeleteDialogFragment mainDeleteDialogFragment, String tripName){
        Log.d("Trip à supprimer : " ,tripName);
        if (TextUtils.isEmpty(tripName)) {
            return;
        }
        mainDeleteDialogFragment.context = mainDeleteDialogFragment.getActivity().getApplicationContext();
        SplitTripDatabase db = SplitTripDatabase.getDatabase(mainDeleteDialogFragment.context);
        DaoTrip tripDao = db.daoTrip();
        DaoTripParticipation tripParticipationdao = db.daoTripParticipant();

        if (mainDeleteDialogFragment.tripNameExtra != null) {
            // clicked on item row -> delete
            Trip tripToDelete = tripDao.findTripById(mainDeleteDialogFragment.tripIdExtra);
            if (tripToDelete != null) {
                if (tripToDelete.getName().equals(tripName)) {
                    tripParticipationdao.deleteWithTripId(tripToDelete.getId());
                    tripDao.delete(tripToDelete);
                }
            }
        }
    }
}
