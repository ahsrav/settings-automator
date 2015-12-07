package com.ahsrav.settingsautomator.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ListView;

import com.ahsrav.settingsautomator.model.FilterInfo;

import java.util.ArrayList;
import java.util.List;

public class FilterDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Filter.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FilterContract.TABLE_NAME + " (" +
                    FilterContract._ID + " INTEGER PRIMARY KEY," +
                    FilterContract.COLUMN_FILTER_NAME + TEXT_TYPE + COMMA_SEP +
                    FilterContract.COLUMN_TRIGGER_TYPE + INTEGER_TYPE + COMMA_SEP +
                    FilterContract.COLUMN_TRIGGER + TEXT_TYPE + COMMA_SEP +
                    FilterContract.COLUMN_BLUETOOTH + INTEGER_TYPE + COMMA_SEP +
                    FilterContract.COLUMN_GPS + INTEGER_TYPE + COMMA_SEP +
                    FilterContract.COLUMN_WIFI + INTEGER_TYPE + COMMA_SEP +
                    FilterContract.COLUMN_DEVICE_VOLUME + INTEGER_TYPE + COMMA_SEP +
                    FilterContract.COLUMN_ALARM_VOLUME + INTEGER_TYPE + COMMA_SEP +
                    FilterContract.COLUMN_MEDIA_VOLUME + INTEGER_TYPE + COMMA_SEP +
                    FilterContract.COLUMN_LOCK_SCREEN_MODE + INTEGER_TYPE + COMMA_SEP +
                    FilterContract.COLUMN_DEVICE_BRIGHTNESS + INTEGER_TYPE +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FilterContract.TABLE_NAME;
    private static final String TAG = "FilterDBHelper";

    public FilterDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public long addFilterRow(FilterInfo data) {
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(FilterContract.TABLE_NAME, null, getContentValues(data));
    }

    public List<String> getAllFilterNames() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {FilterContract.COLUMN_FILTER_NAME};

        List<String> listOfNames = new ArrayList<>();

        Cursor cursor = db.query(FilterContract.TABLE_NAME, projection, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(cursor.getColumnIndex(FilterContract.COLUMN_FILTER_NAME));
                listOfNames.add(name);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return listOfNames;
    }

    public FilterInfo getFilterByName(String filterName) {
        List<FilterInfo> filterInfoList;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(FilterContract.TABLE_NAME, null, FilterContract.COLUMN_FILTER_NAME+"=?",
                new String[]{filterName}, null, null, null);
        filterInfoList = getListOfFilters(cursor);
        if (filterInfoList.size() == 1) {
            return filterInfoList.get(0);
        }
        return null;
    }

    public int updateFilterRow(FilterInfo data) {
        SQLiteDatabase db = getReadableDatabase();

        return db.update(FilterContract.TABLE_NAME, getContentValues(data), FilterContract._ID+"=?",
                new String[]{String.valueOf(data.primaryKey)});
    }

    public int deleteFilterRow(int primaryKey) {
        SQLiteDatabase db = getReadableDatabase();
        return db.delete(FilterContract.TABLE_NAME, FilterContract._ID + "=?",
                new String[]{String.valueOf(primaryKey)});
    }

    public FilterInfo getWifiRow(String ssid) {
        SQLiteDatabase db = getReadableDatabase();

        List<FilterInfo> filters;
        String selection = FilterContract.COLUMN_TRIGGER_TYPE + "=0 AND " + FilterContract.COLUMN_TRIGGER + "=?";

        Cursor cursor = db.query(FilterContract.TABLE_NAME, null, selection, new String[]{ssid}, null, null, null);
        filters = getListOfFilters(cursor);
        if (filters.size() == 1) {
            return filters.get(0);
        }
        return null;
    }

    private List<FilterInfo> getListOfFilters(Cursor cursor) {
        List<FilterInfo> filterInfoList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                FilterInfo filterInfo = new FilterInfo();
                filterInfo.primaryKey = cursor.getInt(cursor.getColumnIndex(FilterContract._ID));
                filterInfo.filterName = cursor.getString(cursor.getColumnIndex(FilterContract.COLUMN_FILTER_NAME));
                filterInfo.triggerType = cursor.getInt(cursor.getColumnIndex(FilterContract.COLUMN_TRIGGER_TYPE));
                filterInfo.trigger = cursor.getString(cursor.getColumnIndex(FilterContract.COLUMN_TRIGGER));
                filterInfo.bluetoothOnOff = cursor.getInt(cursor.getColumnIndex(FilterContract.COLUMN_BLUETOOTH));
                filterInfo.gpsOnOff = cursor.getInt(cursor.getColumnIndex(FilterContract.COLUMN_GPS));
                filterInfo.wifiOnOff = cursor.getInt(cursor.getColumnIndex(FilterContract.COLUMN_WIFI));
                filterInfo.deviceVolume = cursor.getInt(cursor.getColumnIndex(FilterContract.COLUMN_DEVICE_VOLUME));
                filterInfo.alarmVolume = cursor.getInt(cursor.getColumnIndex(FilterContract.COLUMN_ALARM_VOLUME));
                filterInfo.mediaVolume = cursor.getInt(cursor.getColumnIndex(FilterContract.COLUMN_MEDIA_VOLUME));
                filterInfo.lockScreenMode = cursor.getInt(cursor.getColumnIndex(FilterContract.COLUMN_LOCK_SCREEN_MODE));
                filterInfo.deviceBrightness = cursor.getInt(cursor.getColumnIndex(FilterContract.COLUMN_DEVICE_BRIGHTNESS));

                filterInfoList.add(filterInfo);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return filterInfoList;
    }

    private ContentValues getContentValues(FilterInfo data) {
        ContentValues values = new ContentValues();
        values.put(FilterContract.COLUMN_FILTER_NAME, data.filterName);
        values.put(FilterContract.COLUMN_TRIGGER_TYPE, data.triggerType);
        values.put(FilterContract.COLUMN_TRIGGER, data.trigger);
        values.put(FilterContract.COLUMN_BLUETOOTH, data.bluetoothOnOff);
        values.put(FilterContract.COLUMN_GPS, data.gpsOnOff);
        values.put(FilterContract.COLUMN_WIFI, data.wifiOnOff);
        values.put(FilterContract.COLUMN_DEVICE_VOLUME, data.deviceVolume);
        values.put(FilterContract.COLUMN_ALARM_VOLUME, data.alarmVolume);
        values.put(FilterContract.COLUMN_MEDIA_VOLUME, data.mediaVolume);
        values.put(FilterContract.COLUMN_LOCK_SCREEN_MODE, data.lockScreenMode);
        values.put(FilterContract.COLUMN_DEVICE_BRIGHTNESS, data.deviceBrightness);
        return values;
    }
}
