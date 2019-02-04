package org.diiage.splittripwithyourfriends.ui.addparticipant;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.diiage.splittripwithyourfriends.R;
import org.diiage.splittripwithyourfriends.adapters.SelectableParticipantAdapter;
import org.diiage.splittripwithyourfriends.adapters.SelectableParticipantViewHolder;
import org.diiage.splittripwithyourfriends.entities.Participant;

import java.util.ArrayList;
import java.util.List;

public class AddParticipantFragment extends Fragment implements SelectableParticipantViewHolder.OnItemSelectedListener, View.OnClickListener {

    private AddParticipantViewModel mViewModel;
    private RecyclerView recyclerView;
    private SelectableParticipantAdapter adapter;
    private long tripId;
    private List<Participant> selectedParticipants;
    private Button btnAddParticipant;

    public static AddParticipantFragment newInstance() {
        return new AddParticipantFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.add_participant_fragment, container, false);

        mViewModel = ViewModelProviders.of(this).get(AddParticipantViewModel.class);

        this.tripId = getArguments().getLong("ParamTripId");

        recyclerView = view.findViewById(R.id.selection_list);
        btnAddParticipant = view.findViewById(R.id.btnAddParticipant);

        btnAddParticipant.setOnClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.adapter = new SelectableParticipantAdapter(this, true);

        this.mViewModel.getmUnregisteredParticipants(this.tripId).observe(this, participants -> {
            adapter.setmParticipants(participants);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(this.adapter);

    }

    @Override
    public void onItemSelected(SelectableParticipant selectableParticipant) {
        selectedParticipants = adapter.getSelectedParticipants();
        Snackbar.make(recyclerView,"Selected item is "+selectableParticipant.getName()+
                ", Totally  selectem item count is "+selectedParticipants.size(),Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        if(v == btnAddParticipant) {
            if(selectedParticipants != null) {
                mViewModel.registerParticipants(selectedParticipants, tripId);
                getActivity().finish();
            } else {
                Toast.makeText(this.getContext(), R.string.empty_not_saved, Toast.LENGTH_LONG).show();
            }
        }

    }
}
