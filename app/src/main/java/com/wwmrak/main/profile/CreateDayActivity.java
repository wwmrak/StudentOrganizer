package com.wwmrak.main.profile;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.wwmrak.studentorganizer.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateDayActivity extends Activity {

    private Calendar calendar;
    private int year, month, day;

    String formattedDate;
    String dateString;
    EditText dateEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_day);

        dateEditText = (EditText) findViewById(R.id.editText11);

        setDateToEditText();
    }

    private void setDateToEditText() {
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        dateString = date.format(Calendar.getInstance().getTime());

        dateEditText.setText(dateString);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public void setDate(View view) {
        showDialog(1);
    }

    public void createLecture(View view) {
        showDialog(2);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 1) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        if (id == 2) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {

            year = arg1;
            month = arg2;
            day = arg3;

            Calendar cal = Calendar.getInstance();
            cal.set(year, month - 1, day, 0, 0);
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
            formattedDate = dateFormat1.format(cal.getTime());

            dateEditText.setText(formattedDate);
        }
    };

    public void createLecture2(View view) {
        LayoutInflater li = LayoutInflater.from(this);
        View dialogView = li.inflate(R.layout.activity_create_day_dialog, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        TextView dateTextView = (TextView) dialogView.findViewById(R.id.editText4);
        TextView dateTextView2 = (TextView) dialogView.findViewById(R.id.editText3);

        alertDialogBuilder.setView(dialogView);


        alertDialogBuilder
                .setTitle("Lecture Name")
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                startActivity(new Intent(CreateDayActivity.this, RecordLectureActivity.class));
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
        alertDialog.getWindow().setLayout(700, 600);
        dateTextView.setText((formattedDate == null) ? dateString : formattedDate);
        dateTextView2.requestFocus();
    }
}




