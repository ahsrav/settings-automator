package com.ahsrav.settingsautomator.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.ahsrav.settingsautomator.R;
import com.ahsrav.settingsautomator.database.FilterDBHelper;
import com.ahsrav.settingsautomator.fragment.CustomViewDialogFragment;
import com.ahsrav.settingsautomator.fragment.ListViewDialogFragment;
import com.ahsrav.settingsautomator.fragment.TextViewDialogFragment;
import com.ahsrav.settingsautomator.model.FilterInfo;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddFilterActivity extends AppCompatActivity {

    private static final String TAG = "AddFilterActivity";
    public FilterInfo currentFilterInfo;

    @Bind(R.id.filterNameInfoTV) TextView filterNameInfoTV;
    @Bind(R.id.triggerTypeInfoTV) TextView triggerTypeInfoTV;
    @Bind(R.id.triggerInfoTV) TextView triggerInfoTV;
    @Bind(R.id.bluetoothOnOffInfoTV) TextView bluetoothOnOffInfoTV;
    @Bind(R.id.gpsOnOffInfoTV) TextView gpsOnOffInfoTV;
    @Bind(R.id.wifiOnOffInfoTV) TextView wifiOnOffInfoTV;
    @Bind(R.id.deviceVolumeInfoTV) TextView deviceVolumeInfoTV;
    @Bind(R.id.alarmVolumeInfoTV) TextView alarmVolumeInfoTV;
    @Bind(R.id.mediaVolumeInfoTV) TextView mediaVolumeInfoTV;
    @Bind(R.id.lockScreenModeInfoTV) TextView lockScreenModeInfoTV;
    @Bind(R.id.deviceBrightnessInfoTV) TextView deviceBrightnessInfoTV;


    @Bind(R.id.my_toolbar)
    Toolbar myToolbar;

    private ArrayList<String> triggersList;
    private ArrayList<String> triggersIDList;
    private boolean editMode;
    private FilterDBHelper dbHelper = new FilterDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_filter);
        ButterKnife.bind(this);

        setSupportActionBar(myToolbar);
        currentFilterInfo = new FilterInfo();

        String editFilter = getIntent().getStringExtra("FilterName");
        if (editFilter != null && editFilter.length() > 0) {
            editMode = true;
        }

        if (editMode) {
            currentFilterInfo = dbHelper.getFilterByName(editFilter);
            setData();
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            if (editMode) {
                actionBar.setTitle(R.string.edit_filter);
            } else {
                actionBar.setTitle(R.string.add_filter);
            }
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_filter_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                if (editMode) {
                    dbHelper.updateFilterRow(currentFilterInfo);
                } else {
                    dbHelper.addFilterRow(currentFilterInfo);
                }
                finish();
                return true;
            case R.id.action_trash:
                if (editMode) {
                    dbHelper.deleteFilterRow(currentFilterInfo.primaryKey);
                }
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setData() {
        Log.i(TAG, "setDAta");
        filterNameInfoTV.setText(currentFilterInfo.filterName);
        triggerTypeInfoTV.setText(getDisplayString(currentFilterInfo.triggerType, R.array.triggerTypes));
        triggerInfoTV.setText(currentFilterInfo.trigger);
        bluetoothOnOffInfoTV.setText(getDisplayString(currentFilterInfo.bluetoothOnOff, R.array.onOffNoChange));
        gpsOnOffInfoTV.setText(getDisplayString(currentFilterInfo.gpsOnOff, R.array.onOffNoChange));
        wifiOnOffInfoTV.setText(getDisplayString(currentFilterInfo.wifiOnOff, R.array.onOffNoChange));
        deviceVolumeInfoTV.setText(getDisplayString(currentFilterInfo.deviceVolume));
        alarmVolumeInfoTV.setText(getDisplayString(currentFilterInfo.alarmVolume));
        mediaVolumeInfoTV.setText(getDisplayString(currentFilterInfo.mediaVolume));
        lockScreenModeInfoTV.setText(getDisplayString(currentFilterInfo.lockScreenMode, R.array.onOffNoChange));
        deviceBrightnessInfoTV.setText(getDisplayString(currentFilterInfo.deviceBrightness));
    }

    private String getDisplayString(int value, int array) {
        String[] stringArray = getResources().getStringArray(array);
        if (value > -1 && value < stringArray.length) {
            return stringArray[value];
        }
        return getString(R.string.arrow);
    }

    private String getDisplayString(int value) {
        if (value == 0) {
            return getString(R.string.arrow);
        }
        return String.valueOf(value);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i(TAG, "onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putParcelable("currentFilterInfo", currentFilterInfo);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.i(TAG, "onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
        currentFilterInfo = savedInstanceState.getParcelable("currentFilterInfo");
        setData();
    }

    public void setValue(int field, int value) {
        switch (field) {
            case R.id.triggerTypeInfoTV:
                currentFilterInfo.triggerType = value;
                break;
            case R.id.triggerInfoTV:
                if (triggersList.size() > value) {
                    currentFilterInfo.trigger = triggersList.get(value);
                    currentFilterInfo.triggerID = triggersIDList.get(value);
                }
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
                        R.id.triggerTypeInfoTV, getResources().getStringArray(R.array.triggerTypes));
        newFragment.show(getSupportFragmentManager(), "triggerType");
    }

    @OnClick (R.id.trigger)
    public void selectTrigger() {
        triggersList = new ArrayList<>();
        triggersIDList = new ArrayList<>();
        switch (currentFilterInfo.triggerType) {
            case FilterInfo.TRIGGER_TYPE_WIFI:
                WifiManager wifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
                if (wifiManager.isWifiEnabled()){
                    for (WifiConfiguration wifi : wifiManager.getConfiguredNetworks()) {
                        triggersList.add(wifi.SSID);
                        triggersIDList.add(wifi.SSID);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), R.string.please_turn_on_wifi, Toast.LENGTH_SHORT).show();
                }
                break;
            case FilterInfo.TRIGGER_TYPE_BLUETOOTH:
                BluetoothAdapter bluetoothAdapter;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    bluetoothAdapter = ((BluetoothManager) getSystemService(BLUETOOTH_SERVICE)).getAdapter();
                } else {
                    bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                }
                if (bluetoothAdapter.getState() == BluetoothAdapter.STATE_ON) {
                    for (BluetoothDevice device : bluetoothAdapter.getBondedDevices()) {
                        triggersList.add(device.getName());
                        triggersIDList.add(device.getAddress());
                    }
                } else {
                    Toast.makeText(getApplicationContext(), R.string.please_turn_on_bluetooth, Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                Toast.makeText(getApplicationContext(), R.string.trigger_type_first, Toast.LENGTH_SHORT).show();
                break;
        }
        if (triggersList.size() > 0) {
            DialogFragment newFragment = ListViewDialogFragment
                    .newInstance(R.string.select_trigger, -1,
                            R.id.triggerInfoTV, triggersList.toArray(new String[triggersList.size()]));
            newFragment.show(getSupportFragmentManager(), "triggerType");
        }

    }

    @OnClick (R.id.bluetoothOnOff)
    public void setBluetoothOnOff() {
        DialogFragment newFragment = ListViewDialogFragment
                .newInstance(R.string.bluetooth, currentFilterInfo.bluetoothOnOff,
                        R.id.bluetoothOnOffInfoTV, getResources().getStringArray(R.array.onOffNoChange));
        newFragment.show(getSupportFragmentManager(), "bluetooth");
    }

    @OnClick (R.id.gpsOnOff)
    public void setGPSOnOff() {
        DialogFragment newFragment = ListViewDialogFragment
                .newInstance(R.string.gps, currentFilterInfo.gpsOnOff,
                        R.id.gpsOnOffInfoTV, getResources().getStringArray(R.array.onOffNoChange));
        newFragment.show(getSupportFragmentManager(), "gps");
    }

    @OnClick (R.id.wifiOnOff)
    public void setWifiOnOff() {
        DialogFragment newFragment = ListViewDialogFragment
                .newInstance(R.string.wifi, currentFilterInfo.wifiOnOff,
                        R.id.wifiOnOffInfoTV, getResources().getStringArray(R.array.onOffNoChange));
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
                        R.id.lockScreenModeInfoTV, getResources().getStringArray(R.array.lockScreenMode));
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
