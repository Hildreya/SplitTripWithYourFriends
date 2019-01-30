package org.diiage.splittripwithyourfriends.ui.createtrip;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.diiage.splittripwithyourfriends.R;
import org.diiage.splittripwithyourfriends.entities.Trip;
import org.diiage.splittripwithyourfriends.interfaces.DaoTrip;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class CreateTripFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private CreateTripViewModel mViewModel;
    private EditText textViewTripName;
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    public static CreateTripFragment newInstance() {
        return new CreateTripFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(CreateTripViewModel.class);

        View view = inflater.inflate(R.layout.create_trip_fragment, container, false);
        textViewTripName = view.findViewById(R.id.tripName);
        Button btnCreate = view.findViewById(R.id.buttonCreate);
        //TODO binding
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(textViewTripName.getText())){
                    getActivity().setResult(RESULT_CANCELED, replyIntent);
                }
                else{
                    String nameTrip = textViewTripName.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, nameTrip);
                    getActivity().setResult(RESULT_OK, replyIntent);
                }
                getActivity().finish();
            }
        }
            //CreateTrip(view);
        );
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CreateTripViewModel.class);
        // TODO: Use the ViewModel
    }

    //private SplitTripDatabase org.diiage.splittripwithyourfriends.database = Room.databaseBuilder(getApplicationContext(),SplitTripDatabase.class, "SplitTripDatabase").build();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        month +=1;
        //Création de la date sous forme de string
        String s = day+"-"+month+"-"+year;
        TextView textView = getView().findViewById(R.id.textViewbeginDate);
        textView.setText(s);
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
}