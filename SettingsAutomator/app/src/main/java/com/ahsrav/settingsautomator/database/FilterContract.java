package com.ahsrav.settingsautomator.database;

import android.provider.BaseColumns;

public final class FilterContract implements BaseColumns {

    public FilterContract() {}

    public static final String TABLE_NAME = "filters";
    public static final String COLUMN_FILTER_NAME = "filterName";
    public static final String COLUMN_TRIGGER_TYPE = "triggerType";
    public static final String COLUMN_TRIGGER = "trigger";
    public static final String COLUMN_BLUETOOTH = "bluetooth";
    public static final String COLUMN_GPS = "gps";
    public static final String COLUMN_WIFI = "wifi";
    public static final String COLUMN_DEVICE_VOLUME = "deviceVolume";
    public static final String COLUMN_ALARM_VOLUME = "alarmVolume";
    public static final String COLUMN_MEDIA_VOLUME = "mediaVolume";
    public static final String COLUMN_LOCK_SCREEN_MODE = "lockScreenMode";
    public static final String COLUMN_DEVICE_BRIGHTNESS = "deviceBrightness";

}
