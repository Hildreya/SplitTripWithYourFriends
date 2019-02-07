package org.diiage.splittripwithyourfriends.ui.spending;

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
import org.diiage.splittripwithyourfriends.entities.Spending;
import org.diiage.splittripwithyourfriends.interfaces.DaoParticipation;
import org.diiage.splittripwithyourfriends.interfaces.DaoSpending;

import java.util.List;

public class SpendingDeleteDialogFragment extends DialogFragment {
    private Context context;
    private String spendingNameExtra;
    private long spendingIdExtra;

    public static SpendingDeleteDialogFragment newInstance(final String spendingName, long spendingId){
        SpendingDeleteDialogFragment fragment = new SpendingDeleteDialogFragment();
        Bundle args = new Bundle();
        args.putString("spendingNameToUpdate", spendingName);
        args.putLong("spendingIdToUpdate", spendingId);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_delete_spending, null);
        Bundle args = getArguments();
        spendingNameExtra = args.getString("spendingNameToUpdate");
        spendingIdExtra = args.getLong("spendingIdToUpdate");

        alertDialogBuilder.setView(view)
                .setTitle(getString(R.string.dialog_spending_title_delete))
                .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteSpending(SpendingDeleteDialogFragment.this, spendingNameExtra);
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

    public static void deleteSpending(SpendingDeleteDialogFragment spendingDeleteDialogFragment, String spendingName){
        Log.d("Spending à supprimer : " ,spendingName);
        if (TextUtils.isEmpty(spendingName)) {
            return;
        }
        spendingDeleteDialogFragment.context = spendingDeleteDialogFragment.getActivity().getApplicationContext();
        SplitTripDatabase db = SplitTripDatabase.getDatabase(spendingDeleteDialogFragment.context);
        DaoSpending spendingDao = db.daoSpending();
        DaoParticipation participationdao = db.daoParticipation();

        if (spendingDeleteDialogFragment.spendingNameExtra != null) {
            // clicked on item row -> delete
            Spending spendingToDelete = spendingDao.findSpendingById(spendingDeleteDialogFragment.spendingIdExtra);
            if (spendingToDelete != null) {
                if (spendingToDelete.getName().equals(spendingName)) {
                    participationdao.deleteWithSpendingId(spendingToDelete.getId());
                    spendingDao.delete(spendingToDelete);
                }
            }
        }
    }
}
