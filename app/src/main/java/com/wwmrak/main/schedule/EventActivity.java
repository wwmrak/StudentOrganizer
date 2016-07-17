package com.wwmrak.main.schedule;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.wwmrak.adapter.AlarmReceiver;
import com.wwmrak.studentorganizer.R;
import com.wwmrak.sqlLite.DatabaseManagement;
import com.wwmrak.sqlLite.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class EventActivity extends Activity {

    private Calendar calendar;
    private int yearCurrent, monthCurrent, dayCurrent;
    private int hourCurrent, minuteCurrent;

    private int yearEventFrom, monthEventFrom, dayEventFrom;
    private int yearEventTo, monthEventTo, dayEventTo;
    private int yearRemainder, monthRemainder, dayRemainder, hourRemainder, minuteReminder;
    String typeOfEvent;
    String description;

    final static int RQS_1 = 1;
    DatabaseManagement databaseManagement;
    int numberOfdatePicker;
    String formattedDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

//        if (databaseManagement == null) {
//            DatabaseManagement databaseManagement = new DatabaseManagement(this);
//            databaseManagement.deleteRecords();
//        }

        setTime();

        Spinner staticSpinner = (Spinner) findViewById(R.id.static_spinner);

        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.event_type,
                        android.R.layout.simple_spinner_item);

        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        staticSpinner.setAdapter(staticAdapter);
    }

    private void setTime() {
        calendar = Calendar.getInstance();
        yearCurrent = calendar.get(Calendar.YEAR);

        monthCurrent = calendar.get(Calendar.MONTH);
        dayCurrent = calendar.get(Calendar.DAY_OF_MONTH);
        hourCurrent = calendar.get(Calendar.HOUR_OF_DAY);
        minuteCurrent = calendar.get(Calendar.MINUTE);
    }

    public void setDateEventFrom(View view) {
        numberOfdatePicker = 1;
        showDialog(1);
    }

    public void setDateEventTo(View view) {
        numberOfdatePicker = 2;
        showDialog(1);
    }

    public void setDateReminder(View view) {
        numberOfdatePicker = 3;
        showDialog(1);
    }

    public void setTime(View view) {
        showDialog(2);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 2) {
            return new TimePickerDialog(this, myTimePickerListener, hourCurrent, minuteCurrent, false);
        }
        return new DatePickerDialog(this, myDateListener, yearCurrent, monthCurrent, dayCurrent);
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {

            Calendar cal = Calendar.getInstance();
            cal.set(arg1, arg2, arg3, 0, 0);
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
            formattedDate = dateFormat1.format(cal.getTime());

            switch (numberOfdatePicker) {
                case 1:
                    EditText editTextDate1 = (EditText) findViewById(R.id.editText6);
                    editTextDate1.setText(formattedDate);
                    yearEventFrom = arg1;
                    monthEventFrom = arg2;
                    dayEventFrom = arg3;
                    break;
                case 2:
                    EditText editTextDate2 = (EditText) findViewById(R.id.editText7);
                    editTextDate2.setText(formattedDate);
                    yearEventTo = arg1;
                    monthEventTo = arg2;
                    dayEventTo = arg3;
                    break;
                case 3:
                    EditText editTextDate3 = (EditText) findViewById(R.id.editText9);
                    editTextDate3.setText(formattedDate);
                    yearRemainder = arg1;
                    monthRemainder = arg2;
                    dayRemainder = arg3;
                    break;
            }
        }
    };

    private TimePickerDialog.OnTimeSetListener myTimePickerListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
            EditText editText4 = (EditText) findViewById(R.id.editText10);
            editText4.setText(new StringBuilder().append(padding_str(selectedHour))
                    .append(":").append(padding_str(selectedMinute)));

            hourRemainder = selectedHour;
            minuteReminder = selectedMinute;
        }
    };

    private static String padding_str(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    public void saveButton(View view) {
        Spinner mySpinner = (Spinner) findViewById(R.id.static_spinner);
        typeOfEvent = mySpinner.getSelectedItem().toString();
        EditText editTextDescription = (EditText) findViewById(R.id.editText8);
        description = editTextDescription.getText().toString();

        Event event = new Event(yearEventFrom, monthEventFrom, dayEventFrom, yearEventTo, monthEventTo,
                dayEventTo, yearRemainder, monthRemainder, dayRemainder, hourRemainder, minuteReminder,
                typeOfEvent, description);

        if (databaseManagement == null) {
            DatabaseManagement databaseManagement = new DatabaseManagement(this);
            databaseManagement.addRow(event);
        }

        Calendar calendarAlarm = new GregorianCalendar();
        calendarAlarm.set(Calendar.YEAR, yearRemainder);
        calendarAlarm.set(Calendar.MONTH, monthRemainder);
        calendarAlarm.set(Calendar.DAY_OF_MONTH, dayRemainder);
        calendarAlarm.set(Calendar.HOUR_OF_DAY, hourRemainder);
        calendarAlarm.set(Calendar.MINUTE, minuteReminder);

        setAlarm(calendarAlarm);

        finish();
    }

    private void setAlarm(Calendar targetCal) {
//        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
        Toast.makeText(getApplicationContext(), "Alarm is set : "
                + targetCal.getTime(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getBaseContext(), RQS_1, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
                pendingIntent);
    }

    public void cancelButton(View view) {
        finish();
    }
}
