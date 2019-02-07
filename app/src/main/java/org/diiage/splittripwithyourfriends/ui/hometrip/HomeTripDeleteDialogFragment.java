package org.diiage.splittripwithyourfriends.ui.hometrip;

import android.app.Dialog;
import android.app.DialogFragment;
import android.arch.lifecycle.LiveData;
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
import org.diiage.splittripwithyourfriends.entities.Participant;
import org.diiage.splittripwithyourfriends.interfaces.DaoParticipant;
import org.diiage.splittripwithyourfriends.interfaces.DaoTripParticipation;

import java.util.List;

public class HomeTripDeleteDialogFragment extends DialogFragment {
    private Context context;
    private String participantNameExtra;
    private long participantIdExtra;

    public static HomeTripDeleteDialogFragment newInstance(final String participantName, long participantId){
        HomeTripDeleteDialogFragment fragment = new HomeTripDeleteDialogFragment();
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
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_delete_participant, null);
        Bundle args = getArguments();
        participantNameExtra = args.getString("participantNameToUpdate");
        participantIdExtra = args.getLong("participantIdToUpdate");

        alertDialogBuilder.setView(view)
                .setTitle(getString(R.string.dialog_participant_title_delete))
                .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteParticipant(HomeTripDeleteDialogFragment.this, participantNameExtra);
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

    public static void deleteParticipant(HomeTripDeleteDialogFragment homeTripDeleteDialogFragment, String participantName){
        Log.d("Participant à supprimer : " ,participantName);
        if (TextUtils.isEmpty(participantName)) {
            return;
        }
        homeTripDeleteDialogFragment.context = homeTripDeleteDialogFragment.getActivity().getApplicationContext();
        SplitTripDatabase db = SplitTripDatabase.getDatabase(homeTripDeleteDialogFragment.context);
        DaoParticipant participantDao = db.daoParticipant();
        DaoTripParticipation participationdao = db.daoTripParticipant();

        if (homeTripDeleteDialogFragment.participantNameExtra != null) {
            // clicked on item row -> delete
            Participant participantToDelete = participantDao.findParticipantById(homeTripDeleteDialogFragment.participantIdExtra);
            if (participantToDelete != null) {
                if (participantToDelete.getName().equals(participantName)) {
                    participationdao.deleteWithParticipantId(participantToDelete.getId());
//                    LiveData<List<Participant>> p = participantDao.getAllParticipants();
//                    participantDao.delete(participantToDelete);
                }
            }
        }
    }
}
