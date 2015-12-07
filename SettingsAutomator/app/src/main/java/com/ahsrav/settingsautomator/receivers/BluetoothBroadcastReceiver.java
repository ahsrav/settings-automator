package com.ahsrav.settingsautomator.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.ahsrav.settingsautomator.database.FilterDBHelper;
import com.ahsrav.settingsautomator.model.FilterInfo;

public class BluetoothBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "BTBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            FilterDBHelper dbHelper = new FilterDBHelper(context);
            FilterInfo filter = dbHelper.getWifiRow(wifiInfo.getSSID());
            if (filter != null) {
                // Implement settings
            }
        }
    }
}
