package org.diiage.splittripwithyourfriends.ui.createtrip;

import android.app.DatePickerDialog;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.diiage.splittripwithyourfriends.R;
import org.diiage.splittripwithyourfriends.entities.Trip;

import java.util.Calendar;

public class CreateTripFragment extends Fragment implements View.OnClickListener {

    private CreateTripViewModel mViewModel;
    private EditText textViewTripName;
    private TextView textViewBeginDate, textViewEndDate;
    private Button btnCreate, btnSetEndDate, btnSetBeginDate;
    private int mYear, mMonth, mDay;

    public static CreateTripFragment newInstance() {
        return new CreateTripFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //mViewModel = ViewModelProviders.of(this.getActivity()).get(CreateTripViewModel.class);

        View view = inflater.inflate(R.layout.create_trip_fragment, container, false);
        textViewTripName = view.findViewById(R.id.tripName);
        textViewBeginDate = view.findViewById(R.id.textViewbeginDate);
        textViewEndDate = view.findViewById(R.id.textViewEndDate);

        btnCreate = view.findViewById(R.id.buttonCreate);
        btnSetBeginDate = view.findViewById(R.id.btnStartDate);
        btnSetEndDate = view.findViewById(R.id.btnDateEnd);

        btnSetBeginDate.setOnClickListener(this);
        btnSetEndDate.setOnClickListener(this);
        btnCreate.setOnClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CreateTripViewModel.class);
        // TODO: Use the ViewModel
    }

    public void CreateTrip(View v){
        TextView textView = v.findViewById(R.id.tripName);
        TextView textViewBeginDate = v.findViewById(R.id.textViewbeginDate);
        TextView textViewEndDate = v.findViewById(R.id.textViewEndDate);
        String name = textView.getText().toString();
        String beginDate = textViewBeginDate.getText().toString();
        String endDate = textViewEndDate.getText().toString();
        Trip t = new Trip(name);
        mViewModel.insert(t);
        //mViewModel.AddTrip(name);
    }

    @Override
    public void onClick(View v) {
        if(v == btnSetBeginDate) {
            final Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this.getContext(),
                    (view, year, monthOfYear, dayOfMonth) -> textViewBeginDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year), mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if(v == btnSetEndDate) {
            final Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this.getContext(),
                    (view, year, monthOfYear, dayOfMonth) -> textViewEndDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year), mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if(v == btnCreate) {

        }
    }
}