package org.diiage.splittripwithyourfriends.ui.createtrip;

import android.app.DatePickerDialog;
import android.databinding.DataBindingUtil;
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
import android.widget.Toast;

import org.diiage.splittripwithyourfriends.R;
import org.diiage.splittripwithyourfriends.databinding.CreateTripFragmentBinding;
import org.diiage.splittripwithyourfriends.entities.Trip;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

        View view = inflater.inflate(R.layout.create_trip_fragment, container, false);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);

        textViewTripName = view.findViewById(R.id.tripName);
        textViewBeginDate = view.findViewById(R.id.textViewbeginDate);
        textViewEndDate = view.findViewById(R.id.textViewEndDate);

        textViewBeginDate.setText(sdf.format(new Date()));
        c.add(Calendar.DATE, 1);
        textViewEndDate.setText(sdf.format(c.getTime()));

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
            try {
                String name = textViewTripName.getText().toString();
                String beginDate = textViewBeginDate.getText().toString();
                String endDate = textViewEndDate.getText().toString();

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
                Date oBeginDate = sdf.parse(beginDate);
                Date oEndDate = sdf.parse(endDate);

                if(name.equals("")) {
                    Toast.makeText(this.getContext(), R.string.empty_not_saved, Toast.LENGTH_LONG).show();
                } else if(oBeginDate.after(oEndDate)) {
                    Toast.makeText(this.getContext(), R.string.start_date_after_end_date, Toast.LENGTH_LONG).show();
                }
                else {
                    Trip t = new Trip(name,1);
                    t.setBeginDate(beginDate);
                    t.setEndDate(endDate);
                    mViewModel.insert(t);
                    getActivity().finish();
                }
            } catch (Exception e) {
                Toast.makeText(this.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }
    }
}