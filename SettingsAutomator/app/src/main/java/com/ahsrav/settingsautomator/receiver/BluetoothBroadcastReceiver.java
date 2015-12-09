package com.ahsrav.settingsautomator.receiver;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ahsrav.settingsautomator.database.FilterDBHelper;
import com.ahsrav.settingsautomator.model.FilterInfo;
import com.ahsrav.settingsautomator.util.ImplementSettingsUtil;

public class BluetoothBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "BTBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action.equals(BluetoothDevice.ACTION_ACL_CONNECTED)) {
            Log.d("TAG","Received: Bluetooth Connected");
            FilterDBHelper dbHelper = new FilterDBHelper(context);
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            FilterInfo filter = dbHelper.getRowByConnection(device.getAddress(), FilterInfo.TRIGGER_TYPE_BLUETOOTH);
            if (filter != null) {
                ImplementSettingsUtil.implementSettings(filter, context);
            }
        }
    }
}
