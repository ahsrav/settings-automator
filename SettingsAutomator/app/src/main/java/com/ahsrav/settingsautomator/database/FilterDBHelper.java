package com.ahsrav.settingsautomator.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
                    FilterContract.COLUMN_DEVICE_BRIGHTNESS + INTEGER_TYPE + COMMA_SEP +
            " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FilterContract.TABLE_NAME;

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
}
