package com.wwmrak.main.schedule;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.wwmrak.studentorganizer.R;
import com.wwmrak.sqlLite.DatabaseManagement;
import com.wwmrak.sqlLite.Event;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ViewScheduleActivity extends Activity {

    ListView listView;
    DatabaseManagement databaseManagement;
    List<Object> listEntitesAndRowNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_schedule);

        listEntitesAndRowNum = new ArrayList<Object>();
        if (databaseManagement == null) {
            DatabaseManagement databaseManagement = new DatabaseManagement(this);
            listEntitesAndRowNum = databaseManagement.selectAll();
        }

        listView = (ListView) findViewById(R.id.listView);

        int rowNums = (int) listEntitesAndRowNum.get(0);
        String[] values = new String[rowNums];
        List<List<Event>> listPerm = (List) listEntitesAndRowNum.get(1);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdfAlarm = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Calendar calendarFrom = new GregorianCalendar();
        Calendar calendarTo = new GregorianCalendar();
        Calendar calendarAlarm = new GregorianCalendar();

        for (int i = 0; i < rowNums; i++) {
            Event event = (Event) listPerm.get(i);

            calendarFrom.set(Calendar.YEAR, event.getYearEventFrom());
            calendarFrom.set(Calendar.MONTH, event.getMonthEventFrom());
            calendarFrom.set(Calendar.DAY_OF_MONTH, event.getDayEventFrom());

            calendarTo.set(Calendar.YEAR, event.getYearEventTo());
            calendarTo.set(Calendar.MONTH, event.getMonthEventTo());
            calendarTo.set(Calendar.DAY_OF_MONTH, event.getDayEventTo());

            calendarAlarm.set(Calendar.YEAR, event.getYearRemainder());
            calendarAlarm.set(Calendar.MONTH, event.getMonthRemainder());
            calendarAlarm.set(Calendar.DAY_OF_MONTH, event.getDayRemainder());
            calendarAlarm.set(Calendar.HOUR_OF_DAY, event.getHourRemainder());
            calendarAlarm.set(Calendar.MINUTE, event.getMinuteReminder());

            values[i] = "Type of event : " + event.getTypeOfEvent()
                    + "      Description : " + event.getDescription()
                    + "      Event duration from: " + sdf.format(calendarFrom.getTime())
                    + "                       Event duration until : " + sdf.format(calendarTo.getTime())
                    + "                       Alarm notification : " + sdfAlarm.format(calendarAlarm.getTime()) + "\n";
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);
    }
}
