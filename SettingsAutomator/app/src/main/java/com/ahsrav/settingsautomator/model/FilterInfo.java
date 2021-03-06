package com.ahsrav.settingsautomator.model;

import android.os.Parcel;
import android.os.Parcelable;

// This class contains all the information entered by the user when a ew filter is created.
public class FilterInfo implements Parcelable {

    public static final int TRIGGER_TYPE_WIFI = 0;
    public static final int TRIGGER_TYPE_BLUETOOTH = 1;

    public int primaryKey;
    public String filterName;
    public int triggerType;
    public String trigger;
    public String triggerID;
    public int bluetoothOnOff;
    public int wifiOnOff;
    public int deviceVolume;
    public int alarmVolume;
    public int mediaVolume;
    public int lockScreenMode;
    public int deviceBrightness;

    public FilterInfo() {
        triggerType = -1;
        trigger = ">";
        bluetoothOnOff = -1;
        wifiOnOff = -1;
        deviceVolume = -1;
        alarmVolume = -1;
        mediaVolume = -1;
        lockScreenMode = -1;
    }

    protected FilterInfo(Parcel in) {
        filterName = in.readString();
        triggerType = in.readInt();
        trigger = in.readString();
        triggerID = in.readString();
        bluetoothOnOff = in.readInt();
        wifiOnOff = in.readInt();
        deviceVolume = in.readInt();
        alarmVolume = in.readInt();
        mediaVolume = in.readInt();
        lockScreenMode = in.readInt();
        deviceBrightness = in.readInt();
    }

    public static final Creator<FilterInfo> CREATOR = new Creator<FilterInfo>() {
        @Override
        public FilterInfo createFromParcel(Parcel in) {
            return new FilterInfo(in);
        }

        @Override
        public FilterInfo[] newArray(int size) {
            return new FilterInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(filterName);
        dest.writeInt(triggerType);
        dest.writeString(trigger);
        dest.writeString(triggerID);
        dest.writeInt(bluetoothOnOff);
        dest.writeInt(wifiOnOff);
        dest.writeInt(deviceVolume);
        dest.writeInt(alarmVolume);
        dest.writeInt(mediaVolume);
        dest.writeInt(lockScreenMode);
        dest.writeInt(deviceBrightness);
    }
}
