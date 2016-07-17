package com.wwmrak.sqlLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wwmrak on 7/13/2016.
 */
public class DatabaseManagement extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "student_organizer";
    private static final String TABLE_EVENT = "event";

    private static final String KEY_ID = "id";

    private static final String KEY_YEAR_EVENT_FROM = "yearEventFrom";
    private static final String KEY_MONTH_EVENT_FROM = "monthEventFrom";
    private static final String KEY_DAY_EVENT_FROM = "dayEventFrom";

    private static final String KEY_YEAR_EVENT_TO = "yearEventTo";
    private static final String KEY_MONTH_EVENT_TO = "monthEventTo";
    private static final String KEY_DAY_EVENT_TO = "dayEventTo";

    private static final String KEY_YEAR_REMAINDER = "yearRemainder";
    private static final String KEY_MONTH_REMAINDER = "monthRemainder";
    private static final String KEY_DAY_REMAINDER = "dayRemainder";
    private static final String KEY_HOUR_REMAINDER = "hourRemainder";
    private static final String KEY_MINUTE_REMAINDER = "minuteReminder";

    private static final String KEY_TYPE_OF_EVENT = "typeOfEvent";
    private static final String KEY_DESCRIPTION = "description";

    public DatabaseManagement(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EVENT_TABLE = "CREATE TABLE " + TABLE_EVENT + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"

                + KEY_YEAR_EVENT_FROM + " TEXT,"
                + KEY_MONTH_EVENT_FROM + " TEXT,"
                + KEY_DAY_EVENT_FROM + " TEXT,"

                + KEY_YEAR_EVENT_TO + " TEXT,"
                + KEY_MONTH_EVENT_TO + " TEXT,"
                + KEY_DAY_EVENT_TO + " TEXT,"

                + KEY_YEAR_REMAINDER + " TEXT,"
                + KEY_MONTH_REMAINDER + " TEXT,"
                + KEY_DAY_REMAINDER + " TEXT,"
                + KEY_HOUR_REMAINDER + " TEXT,"
                + KEY_MINUTE_REMAINDER + " TEXT,"

                + KEY_TYPE_OF_EVENT + " TEXT,"
                + KEY_DESCRIPTION + " TEXT"

                + ")";
        db.execSQL(CREATE_EVENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);
        onCreate(db);
    }

    public void addRow(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_YEAR_EVENT_FROM, event.getYearEventFrom());
        values.put(KEY_MONTH_EVENT_FROM, event.getMonthEventFrom());
        values.put(KEY_DAY_EVENT_FROM, event.getDayEventFrom());

        values.put(KEY_YEAR_EVENT_TO, event.getYearEventTo());
        values.put(KEY_MONTH_EVENT_TO, event.getMonthEventTo());
        values.put(KEY_DAY_EVENT_TO, event.getDayEventTo());

        values.put(KEY_YEAR_REMAINDER, event.getYearRemainder());
        values.put(KEY_MONTH_REMAINDER, event.getMonthRemainder());
        values.put(KEY_DAY_REMAINDER, event.getDayRemainder());
        values.put(KEY_HOUR_REMAINDER, event.getHourRemainder());
        values.put(KEY_MINUTE_REMAINDER, event.getMinuteReminder());

        values.put(KEY_TYPE_OF_EVENT, event.getTypeOfEvent());
        values.put(KEY_DESCRIPTION, event.getDescription());

        db.insert(TABLE_EVENT, null, values);
        db.close();
    }

    public List<Object> selectAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_EVENT);

        List<Event> eventList = new ArrayList<Event>();

        String selectQuery = "SELECT  * FROM " + TABLE_EVENT;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Event event = new Event();
                event.setId(Integer.parseInt(cursor.getString(0)));

                event.setYearEventFrom(Integer.parseInt(cursor.getString(1)));
                event.setMonthEventFrom(Integer.parseInt(cursor.getString(2)));
                event.setDayEventFrom(Integer.parseInt(cursor.getString(3)));

                event.setYearEventTo(Integer.parseInt(cursor.getString(4)));
                event.setMonthEventTo(Integer.parseInt(cursor.getString(5)));
                event.setDayEventTo(Integer.parseInt(cursor.getString(6)));

                event.setYearRemainder(Integer.parseInt(cursor.getString(7)));
                event.setMonthRemainder(Integer.parseInt(cursor.getString(8)));
                event.setDayRemainder(Integer.parseInt(cursor.getString(9)));
                event.setHourRemainder(Integer.parseInt(cursor.getString(10)));
                event.setMinuteReminder(Integer.parseInt(cursor.getString(11)));

                event.setTypeOfEvent(cursor.getString(12));
                event.setDescription(cursor.getString(13));

                eventList.add(event);
            } while (cursor.moveToNext());
        }
        List<Object> listEntitiesAndRowNum = new ArrayList<Object>();
        listEntitiesAndRowNum.add(numRows);
        listEntitiesAndRowNum.add(eventList);

        return listEntitiesAndRowNum;
    }

    public void deleteRecords() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_EVENT);
        db.close();
    }
}
