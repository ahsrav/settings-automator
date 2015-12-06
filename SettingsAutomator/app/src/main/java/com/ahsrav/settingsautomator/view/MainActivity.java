package com.ahsrav.settingsautomator.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ahsrav.settingsautomator.R;
import com.ahsrav.settingsautomator.database.FilterDBHelper;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.my_toolbar)
    Toolbar myToolbar;

    @Bind(R.id.filtersLV)
    ListView filtersLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(myToolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateListView();
    }

    private void populateListView() {
        FilterDBHelper dbHelper = new FilterDBHelper(this);
        List<String> listOfNames = dbHelper.getAllFilterNames();
        if (listOfNames.size() > 0) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listOfNames);
            filtersLV.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_filter:
                startActivity(new Intent(this, AddFilterActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
