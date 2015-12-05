package com.ahsrav.settingsautomator.view;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;

import com.ahsrav.settingsautomator.R;
import com.ahsrav.settingsautomator.fragment.CustomViewDialogFragment;
import com.ahsrav.settingsautomator.fragment.ListViewDialogFragment;
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

    public void setValue(int field, int value) {
        switch (field) {
            case R.id.triggerTypeInfoTV:
                currentFilterInfo.triggerType = value;
                break;
            case R.id.bluetoothOnOffInfoTV:
                currentFilterInfo.bluetoothOnOff = value;
                break;
            case R.id.gpsOnOffInfoTV:
                currentFilterInfo.gpsOnOff = value;
                break;
            case R.id.wifiOnOffInfoTV:
                currentFilterInfo.wifiOnOff = value;
                break;
            case R.id.deviceVolumeInfoTV:
                currentFilterInfo.deviceVolume = value;
                break;
            case R.id.alarmVolumeInfoTV:
                currentFilterInfo.alarmVolume = value;
                break;
            case R.id.mediaVolumeInfoTV:
                currentFilterInfo.mediaVolume = value;
                break;
            case R.id.lockScreenModeInfoTV:
                currentFilterInfo.lockScreenMode = value;
                break;
            case R.id.deviceBrightnessInfoTV:
                currentFilterInfo.deviceBrightness = value;
                break;
        }
    }

    public void setValue(int field, String value) {
        if (field == R.id.filterNameInfoTV) {
            currentFilterInfo.filterName = value;
        }
    }

    @OnClick (R.id.filterName)
    public void enterFilterName() {
        DialogFragment newFragment = TextViewDialogFragment.newInstance(R.string.enter_filter_name,
                currentFilterInfo.filterName, R.id.filterNameInfoTV);
        newFragment.show(getSupportFragmentManager(), "filterName");
    }

    @OnClick (R.id.triggerType)
    public void selectTriggerType() {
        DialogFragment newFragment = ListViewDialogFragment
                .newInstance(R.string.select_trigger_type, currentFilterInfo.triggerType,
                        R.id.triggerTypeInfoTV, R.array.triggerTypes);
        newFragment.show(getSupportFragmentManager(), "triggerType");
    }

    @OnClick (R.id.trigger)
    public void selectTrigger() {

    }

    @OnClick (R.id.bluetoothOnOff)
    public void setBluetoothOnOff() {
        DialogFragment newFragment = ListViewDialogFragment
                .newInstance(R.string.bluetooth, currentFilterInfo.bluetoothOnOff,
                        R.id.bluetoothOnOffInfoTV, R.array.onOffNoChange);
        newFragment.show(getSupportFragmentManager(), "bluetooth");
    }

    @OnClick (R.id.gpsOnOff)
    public void setGPSOnOff() {
        DialogFragment newFragment = ListViewDialogFragment
                .newInstance(R.string.gps, currentFilterInfo.gpsOnOff,
                        R.id.gpsOnOffInfoTV, R.array.onOffNoChange);
        newFragment.show(getSupportFragmentManager(), "gps");
    }

    @OnClick (R.id.wifiOnOff)
    public void setWifiOnOff() {
        DialogFragment newFragment = ListViewDialogFragment
                .newInstance(R.string.wifi, currentFilterInfo.wifiOnOff,
                        R.id.wifiOnOffInfoTV, R.array.onOffNoChange);
        newFragment.show(getSupportFragmentManager(), "wifi");
    }

    @OnClick (R.id.deviceVolume)
    public void setDeviceVolume() {
        DialogFragment newFragment = CustomViewDialogFragment
                .newInstance(R.string.device_volume, currentFilterInfo.deviceVolume,
                        R.id.deviceVolumeInfoTV, R.layout.dialog_seekbar);
        newFragment.show(getSupportFragmentManager(), "deviceVolume");
    }

    @OnClick (R.id.alarmVolume)
    public void setAlarmVolume() {
        DialogFragment newFragment = CustomViewDialogFragment
                .newInstance(R.string.alarm_volume, currentFilterInfo.alarmVolume,
                        R.id.alarmVolumeInfoTV, R.layout.dialog_seekbar);
        newFragment.show(getSupportFragmentManager(), "alarmVolume");
    }

    @OnClick (R.id.mediaVolume)
    public void setMediaVolume() {
        DialogFragment newFragment = CustomViewDialogFragment
                .newInstance(R.string.media_volume, currentFilterInfo.mediaVolume,
                        R.id.mediaVolumeInfoTV, R.layout.dialog_seekbar);
        newFragment.show(getSupportFragmentManager(), "mediaVolume");
    }

    @OnClick (R.id.lockScreenMode)
    public void selectLockScreenMode() {
        DialogFragment newFragment = ListViewDialogFragment
                .newInstance(R.string.lock_screen_mode, currentFilterInfo.lockScreenMode,
                        R.id.lockScreenModeInfoTV, R.array.lockScreenMode);
        newFragment.show(getSupportFragmentManager(), "wifi");
    }

    @OnClick (R.id.deviceBrightness)
    public void setDeviceBrightness() {
        DialogFragment newFragment = CustomViewDialogFragment
                .newInstance(R.string.device_brightness, currentFilterInfo.deviceBrightness,
                        R.id.deviceBrightnessInfoTV, R.layout.dialog_seekbar);
        newFragment.show(getSupportFragmentManager(), "deviceBrightness");
    }

}
