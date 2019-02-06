package org.diiage.splittripwithyourfriends.ui.spending;

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
import org.diiage.splittripwithyourfriends.entities.Spending;
import org.diiage.splittripwithyourfriends.interfaces.DaoSpending;

public class SpendingSaveDialogFragment extends DialogFragment {
    private Context context;
    private String spendingNameExtra;
    private long spendingIdExtra;

    public static SpendingSaveDialogFragment newInstance(final String spendingName, long spendingId){
        SpendingSaveDialogFragment fragment = new SpendingSaveDialogFragment();

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
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_spending, null);
        Bundle args = getArguments();
        spendingNameExtra = args.getString("spendingNameToUpdate");
        spendingIdExtra = args.getLong("spendingIdToUpdate");

        final EditText spendingEditText = view.findViewById(R.id.etSpendingName);
        if (spendingNameExtra != null) {
            spendingEditText.setText(spendingNameExtra);
            spendingEditText.setSelection(spendingNameExtra.length());
        }

        alertDialogBuilder.setView(view)
                .setTitle(getString(R.string.dialog_spending_title))
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveSpending(spendingEditText.getText().toString());
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

    private void saveSpending(String spendingName) {
        if (TextUtils.isEmpty(spendingName)) {
            return;
        }
        context = getActivity().getApplicationContext();
        SplitTripDatabase db = SplitTripDatabase.getDatabase(context);
        DaoSpending spendingDao = db.daoSpending();

        if (spendingNameExtra != null) {
            // clicked on item row -> update
            Spending spendingToUpdate = spendingDao.findSpendingById(spendingIdExtra);
            if (spendingToUpdate != null) {
                if (!spendingToUpdate.getName().equals(spendingName)) {
                    spendingToUpdate.setName(spendingName);
                    spendingDao.update(spendingToUpdate);
                }
            }
        }
    }
}
