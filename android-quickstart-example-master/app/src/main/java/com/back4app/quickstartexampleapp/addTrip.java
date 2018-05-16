package com.back4app.quickstartexampleapp;

import android.app.Dialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;


public class addTrip extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private int datePickerInput;
    private Date startDate = new Date();
    private Date endDate = new Date();

    public void createTrip (View view){
        EditText nameTripText = (EditText) findViewById(R.id.nameTrip);
        boolean isMoreDest = ((CheckBox) findViewById(R.id.moreDest)).isChecked();
        Log.i("moreDest", String.valueOf(isMoreDest));
        if( nameTripText != null){
            ParseObject trip = new ParseObject("Trip");
            trip.put("createdBy", ParseUser.getCurrentUser());
            trip.put("nameTrip", nameTripText.getText().toString());
            trip.put("startDate", startDate);
            trip.put("startDate", endDate);
            trip.put("moreDestination", isMoreDest);

            trip.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if( e == null){
                        Log.i("create", "creare trip cu succes");
                        Intent intent = new Intent(getApplicationContext(),MainContent.class);
                        startActivity(intent);
                    }else{
                        Log.i("create", "creare trip cu eroare");
                    }
                }
            });
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);
    }


    public void datePicker(View view){
        datePickerInput = view.getId();
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(),"date");
    }


    private void setDate(final  Calendar calendar){
        switch( datePickerInput ) {
            case R.id.setStartDate:
                final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
                ((TextView) findViewById(R.id.startDate)).setText(dateFormat.format(calendar.getTime()));
                startDate = calendar.getTime();
                break;
            case R.id.setEndDate:
                final DateFormat dateFormat2 = DateFormat.getDateInstance(DateFormat.MEDIUM);
                ((TextView) findViewById(R.id.endDate)).setText(dateFormat2.format(calendar.getTime()));
                endDate = calendar.getTime();
            default:

                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year,month,day);
        setDate(cal);
    }

    public static class DatePickerFragment extends DialogFragment {

        public Dialog onCreateDialog(Bundle savedInstaceState){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(),
                    (DatePickerDialog.OnDateSetListener)
                    getActivity(),year, month, day);

        }
    }
}
