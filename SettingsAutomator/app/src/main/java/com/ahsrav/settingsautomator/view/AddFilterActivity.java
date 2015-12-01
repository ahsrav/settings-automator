package com.ahsrav.settingsautomator.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.ahsrav.settingsautomator.R;

import butterknife.OnClick;

public class AddFilterActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_filter);

    }

    @OnClick (R.id.filterName)
    public void enterFilterName() {

    }

    @OnClick (R.id.triggerType)
    public void selectTriggerType() {

    }

    @OnClick (R.id.trigger)
    public void selectTrigger() {

    }

    @OnClick (R.id.bluetoothOnOff)
    public void setBluetoothOnOff() {

    }

    @OnClick (R.id.gpsOnOff)
    public void setGPSOnOff() {

    }

    @OnClick (R.id.wifiOnOff)
    public void setWifiOnOff() {

    }

    @OnClick (R.id.deviceVolume)
    public void setDeviceVolume() {

    }

    @OnClick (R.id.alarmVolume)
    public void setAlarmVolume() {

    }

    @OnClick (R.id.mediaVolume)
    public void setMediaVolume() {

    }

    @OnClick (R.id.lockScreenMode)
    public void selectLockScreenMode() {

    }

    @OnClick (R.id.deviceBrightness)
    public void setDeviceBrightness() {

    }

}
