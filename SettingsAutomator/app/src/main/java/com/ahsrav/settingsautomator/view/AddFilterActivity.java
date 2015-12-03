package com.ahsrav.settingsautomator.view;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;

import com.ahsrav.settingsautomator.R;
import com.ahsrav.settingsautomator.fragment.TextViewDialogFragment;
import com.ahsrav.settingsautomator.model.FilterInfo;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddFilterActivity extends FragmentActivity {

    public FilterInfo currentFilterInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_filter);
        currentFilterInfo = new FilterInfo();
        ButterKnife.bind(this);
    }

    @OnClick (R.id.filterName)
    public void enterFilterName() {
        DialogFragment newFragment = new TextViewDialogFragment();
        newFragment.show(getSupportFragmentManager(), "filterName");
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
