package com.ahsrav.settingsautomator.model;

// This class contains all the information entered by the user when a ew filter is created.
public class FilterInfo {

    public String filterName;
    public int triggerType;
    public String trigger;
    public int bluetoothOnOff;
    public int gpsOnOff;
    public int wifiOnOff;
    public int deviceVolume;
    public int alarmVolume;
    public int mediaVolume;
    public int lockScreenMode;
    public int deviceBrightness;

    public FilterInfo() {
        triggerType = -1;
        bluetoothOnOff = -1;
        gpsOnOff = -1;
        wifiOnOff = -1;
        lockScreenMode = -1;
    }
}
