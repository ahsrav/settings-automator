package com.ahsrav.settingsautomator.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.ahsrav.settingsautomator.database.FilterDBHelper;
import com.ahsrav.settingsautomator.model.FilterInfo;

public class WifiBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "WifiBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            Log.i(TAG, wifiInfo.getSSID());
            FilterDBHelper dbHelper = new FilterDBHelper(context);
            FilterInfo filter = dbHelper.getRowByConnection(wifiInfo.getSSID(), FilterInfo.TRIGGER_TYPE_WIFI);
            if (filter != null) {
                // Implement settings
            }
        }
    }
}
