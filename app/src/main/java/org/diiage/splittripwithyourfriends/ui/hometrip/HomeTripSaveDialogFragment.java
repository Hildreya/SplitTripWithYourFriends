package org.diiage.splittripwithyourfriends.ui.hometrip;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import org.diiage.splittripwithyourfriends.R;
import org.diiage.splittripwithyourfriends.database.SplitTripDatabase;
import org.diiage.splittripwithyourfriends.entities.Participant;
import org.diiage.splittripwithyourfriends.entities.Trip;
import org.diiage.splittripwithyourfriends.interfaces.DaoParticipant;
import org.diiage.splittripwithyourfriends.interfaces.DaoTrip;

public class HomeTripSaveDialogFragment extends DialogFragment {
    private Context context;
    private String participantNameExtra;
    private long participantIdExtra;

    public static HomeTripSaveDialogFragment newInstance(final String participantName, long participantId){
        HomeTripSaveDialogFragment fragment = new HomeTripSaveDialogFragment();

        Bundle args = new Bundle();
        args.putString("participantNameToUpdate", participantName);
        args.putLong("participantIdToUpdate", participantId);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_participant, null);
        Bundle args = getArguments();
        participantNameExtra = args.getString("participantNameToUpdate");
        participantIdExtra = args.getLong("participantIdToUpdate");

        final EditText participantEditText = view.findViewById(R.id.etParticipantName);
        if (participantNameExtra != null) {
            participantEditText.setText(participantNameExtra);
            participantEditText.setSelection(participantNameExtra.length());
        }

        alertDialogBuilder.setView(view)
                .setTitle(getString(R.string.dialog_participant_title_update))
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveParticipant(participantEditText.getText().toString());
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

    private void saveParticipant(String participantName) {
        if (TextUtils.isEmpty(participantName)) {
            return;
        }
        context = getActivity().getApplicationContext();
        SplitTripDatabase db = SplitTripDatabase.getDatabase(context);
        DaoParticipant participantDao = db.daoParticipant();

        if (participantNameExtra != null) {
            // clicked on item row -> update
            Participant participantToUpdate = participantDao.findParticipantById(participantIdExtra);
            if (participantToUpdate != null) {
                if (!participantToUpdate.getName().equals(participantName)) {
                    participantToUpdate.setName(participantName);
                    participantDao.update(participantToUpdate);
                }
            }
        }
    }
}
