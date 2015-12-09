package com.ahsrav.settingsautomator.util;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.ahsrav.settingsautomator.model.FilterInfo;

public class ImplementSettingsUtil {

    private static final String TAG = "ImplementSettingsUtil";
    private static Context context;

    public static void implementSettings(FilterInfo filter, Context context1) {
        context = context1;

        setBluetooth(filter.bluetoothOnOff);
        setWifi(filter.wifiOnOff);

        AudioManager manager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        Log.i(TAG, String.valueOf(filter.deviceVolume));

        // Device volume
        setVolume(manager, AudioManager.STREAM_SYSTEM, filter.deviceVolume);
        setVolume(manager, AudioManager.STREAM_RING, filter.deviceVolume);
        setVolume(manager, AudioManager.STREAM_NOTIFICATION, filter.deviceVolume);

        // Alarm volume
        setVolume(manager, AudioManager.STREAM_ALARM, filter.alarmVolume);

        // Media volume
        setVolume(manager, AudioManager.STREAM_MUSIC, filter.mediaVolume);

        // Lock screen mode

        // Device brightness

    }

    private static void setBluetooth(int btVal) {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (btVal == 0 && !bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.enable();
        } else if (btVal == 1 && bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.disable();
        }
    }

    private static void setWifi(int wifiVal) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (wifiVal == 0 && !wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        } else if (wifiVal == 1 && wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(false);
        }
    }

    private static void setVolume(AudioManager manager, int audioStream, int setValue) {
        int volume = manager.getStreamMaxVolume(audioStream) * setValue/100;
        manager.setStreamVolume(audioStream, volume, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
    }
}
