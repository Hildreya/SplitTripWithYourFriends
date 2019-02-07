package org.diiage.splittripwithyourfriends.ui.createparticipant;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.diiage.splittripwithyourfriends.R;
import org.diiage.splittripwithyourfriends.databinding.CreateParticipantFragmentBinding;
import org.diiage.splittripwithyourfriends.entities.Participant;

public class CreateParticipantFragment extends Fragment implements View.OnClickListener {

    private CreateParticipantViewModel mViewModel;
    private EditText txtParticipantName;
    private Button btnCreateParticipant;

    public static CreateParticipantFragment newInstance() {
        return new CreateParticipantFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        CreateParticipantFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.create_participant_fragment, container,false);
        View view = binding.getRoot();

        txtParticipantName = view.findViewById(R.id.txtParticipantName);
        btnCreateParticipant = view.findViewById(R.id.btnCreateParticipant);

        btnCreateParticipant.setOnClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CreateParticipantViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onClick(View v) {

        if(v == btnCreateParticipant) {
            String name = txtParticipantName.getText().toString();

            if (name.equals("")) {
                Toast.makeText(this.getContext(), R.string.empty_not_saved, Toast.LENGTH_LONG).show();
            } else {
                try {
                    Participant p = new Participant(name);
                    mViewModel.insert(p);
                    getActivity().finish();
                } catch (Exception e) {
                    Toast.makeText(this.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
