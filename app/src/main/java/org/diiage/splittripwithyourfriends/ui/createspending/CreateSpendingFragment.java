package org.diiage.splittripwithyourfriends.ui.createspending;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.diiage.splittripwithyourfriends.R;
import org.diiage.splittripwithyourfriends.adapters.SelectableParticipantAdapter;
import org.diiage.splittripwithyourfriends.adapters.SelectableParticipantViewHolder;
import org.diiage.splittripwithyourfriends.entities.Participant;
import org.diiage.splittripwithyourfriends.entities.Participation;
import org.diiage.splittripwithyourfriends.entities.Spending;
import org.diiage.splittripwithyourfriends.ui.addparticipant.SelectableParticipant;

import java.util.Date;
import java.util.List;

public class CreateSpendingFragment extends Fragment implements View.OnClickListener, SelectableParticipantViewHolder.OnItemSelectedListener {

    private CreateSpendingViewModel mViewModel;
    private Participant fromParticipant;
    private List<Participant> toParticipants;
    private long tripId;
    private Button btnCreateSpending;
    private RecyclerView recyclerView;
    private TextView spendingTitle;
    private TextView totalExpenses;
    private Spinner spinner;
    private SelectableParticipantAdapter selectableParticipantAdapter;
    private ArrayAdapter<Participant> participantArrayAdapter;

    public static CreateSpendingFragment newInstance() {
        return new CreateSpendingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_spending_fragment, container, false);
        mViewModel = ViewModelProviders.of(this).get(CreateSpendingViewModel.class);
        this.tripId = getArguments().getLong("ParamTripId");

        recyclerView = view.findViewById(R.id.lstParticipants);
        btnCreateSpending = view.findViewById(R.id.btnCreateSpending);
        spinner = view.findViewById(R.id.fromSpinner);
        spendingTitle = view.findViewById(R.id.spendingTitle);
        totalExpenses = view.findViewById(R.id.totalExpenses);

        btnCreateSpending.setOnClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.selectableParticipantAdapter = new SelectableParticipantAdapter(this, true);
        this.participantArrayAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_dropdown_item_1line);
        this.participantArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.mViewModel.getAllParticipants(this.tripId).observe(this, participants -> {
            if (participants != null) {
                participantArrayAdapter.addAll(participants);
                selectableParticipantAdapter.setmParticipants(participants);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(this.selectableParticipantAdapter);

        spinner.setAdapter(this.participantArrayAdapter);
    }

    @Override
    public void onClick(View v) {
        if(v == btnCreateSpending) {
            Spending spending = new Spending();
            if(spendingTitle.getText().toString().equals("")) {
                Toast.makeText(this.getContext(), R.string.empty_not_saved, Toast.LENGTH_LONG).show();
            }
            else if(totalExpenses.getText().toString().equals("")) {
                Toast.makeText(this.getContext(), R.string.empty_not_saved, Toast.LENGTH_LONG).show();
            } else if (toParticipants == null) {
                Toast.makeText(this.getContext(), R.string.no_participant_selected, Toast.LENGTH_LONG).show();
            } else {
                spending.setName(spendingTitle.getText().toString());
                String spinnerSelection = spinner.getSelectedItem().toString();
                Participant payeur = mViewModel.getParticipantByName(spinnerSelection);
                spending.setPayerId(payeur.getId());
                spending.setTripId(this.tripId);
                spending.setDate(new Date());
                spending.setTotal(Double.valueOf(this.totalExpenses.getText().toString()));

                long spendingId = mViewModel.addSpending(spending);
                for(Participant participant : toParticipants) {
                    Participation participation = new Participation(spendingId,participant.getId());
                    mViewModel.addParticipation(participation);
                }
                getActivity().finish();
            }

        }
    }

    @Override
    public void onItemSelected(SelectableParticipant item) {
        toParticipants = selectableParticipantAdapter.getSelectedParticipants();
    }
}
