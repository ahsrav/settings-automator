package com.ahsrav.settingsautomator.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ahsrav.settingsautomator.R;

import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @OnClick (R.id.addFilterTV)
    public void addNewFilter() {
        // Start addFilterActivity
    }
}
