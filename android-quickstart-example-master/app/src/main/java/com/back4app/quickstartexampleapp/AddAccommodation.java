package com.back4app.quickstartexampleapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.util.Calendar;
import java.util.Date;

public class AddAccommodation extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    public Date checkinDate = new Date();
    public Date checkoutDate = new Date();
    private int timePickerInput;
    private int datePickerInput;
    public String tripId;
    public String tripName;
    public Calendar checkinDateCal = Calendar.getInstance();
    public Calendar checkoutDateCal = Calendar.getInstance();

    public void addAccomodation (View view){
        EditText nameAcc = (EditText) findViewById(R.id.nameAcc);
        EditText addressAcc = (EditText) findViewById(R.id.addressAcc);
        EditText reservNr = (EditText) findViewById(R.id.reservNr);
        EditText price = (EditText) findViewById(R.id.priceAcc);
        EditText noteAcc = (EditText) findViewById(R.id.noteAcc);


        ParseObject acc = new ParseObject("Accommodation");

        acc.put("TripBy", ParseObject.createWithoutData("Trip",tripId));
        acc.put("nameAcc", nameAcc.getText().toString());
        acc.put("addressAcc", addressAcc.getText().toString());
        acc.put("reservationNo", reservNr.getText().toString());
        acc.put("price", Double.parseDouble(price.getText().toString()));
        acc.put("CheckInDate", checkinDate);
        acc.put("CheckOutDate", checkoutDate);
        acc.put("noteAcc", noteAcc.getText().toString());

        acc.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if( e == null){
                        Log.i("create", "creare trip cu succes");
//                        Intent intent = new Intent(getApplicationContext(),CurrentTrip.class);
//                        startActivity(intent);
//                        intent.putExtra("tripName", tripName);
//                        intent.putExtra("tripId", tripId);
                    }else{
                        Log.i("create", "creare trip cu eroare");
                        Log.i("create", String.valueOf(e));
                    }
                }
            });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_accommodation);
        Intent intent = getIntent();
        tripId = intent.getStringExtra("tripId");
        tripName = intent.getStringExtra("tripName");
    }

    public void showTimePickerDialog(View v) {
        timePickerInput = v.getId();
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");

    }
    public void showDatePickerDialog(View v) {
        datePickerInput = v.getId();
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

        switch( datePickerInput ) {
            case R.id.startDateTripA:
                checkinDateCal.set(Calendar.YEAR,year);
                checkinDateCal.set(Calendar.MONTH,month);
                checkinDateCal.set(Calendar.DAY_OF_MONTH,day);
                break;
            case R.id.endDateTripA:
                checkoutDateCal.set(Calendar.YEAR,year);
                checkoutDateCal.set(Calendar.MONTH,month);
                checkoutDateCal.set(Calendar.DAY_OF_MONTH,day);
            default:
                Log.d("mins", "Eroare la selectie data");
                break;
        }

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {

        Log.d("oras", String.valueOf(hour));
        Log.d("mins", String.valueOf(minute));


        switch(timePickerInput) {
            case R.id.startTimeTripA:
                checkinDateCal.set(Calendar.HOUR_OF_DAY,hour);
                checkinDateCal.set(Calendar.MINUTE, minute);
                final java.text.DateFormat dateFormat = java.text.DateFormat.getDateTimeInstance();
                ((TextView) findViewById(R.id.startDateTripAll)).setText(dateFormat.format(checkinDateCal.getTime()));
                Log.d("mins", String.valueOf(dateFormat.format(checkinDateCal.getTime())));
                break;
            case R.id.endTimeTripA:
                checkoutDateCal.set(Calendar.HOUR_OF_DAY,hour);
                checkoutDateCal.set(Calendar.MINUTE, minute);
                final java.text.DateFormat dateFormat2 = java.text.DateFormat.getDateTimeInstance();
                ((TextView) findViewById(R.id.endDateTripAll)).setText(dateFormat2.format(checkoutDateCal.getTime()));
                Log.d("mins", String.valueOf(dateFormat2.format(checkoutDateCal.getTime())));
            default:
                Log.d("mins", "Eroare la selectie ora");
                break;
        }
    }



    public static class TimePickerFragment extends DialogFragment  {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener) getActivity(), hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }



    }

    public static class DatePickerFragment extends DialogFragment {

        public Dialog onCreateDialog(Bundle savedInstaceState){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(),year, month, day);

        }
    }
}
