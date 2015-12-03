package com.ahsrav.settingsautomator.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ahsrav.settingsautomator.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick (R.id.addFilterTV)
    public void addNewFilter() {
        Log.i("MainActivity", "AddNewFilter()");
        startActivity(new Intent(this, AddFilterActivity.class));
    }
}
