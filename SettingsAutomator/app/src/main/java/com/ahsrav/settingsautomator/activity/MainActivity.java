package com.ahsrav.settingsautomator.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ahsrav.settingsautomator.R;
import com.ahsrav.settingsautomator.database.FilterDBHelper;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Bind(R.id.my_toolbar)
    Toolbar myToolbar;

    @Bind(R.id.filtersLV)
    ListView filtersLV;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;
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
            filtersLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.i(TAG, String.valueOf(((TextView) view).getText()));
                    Intent editFilterIntent = new Intent(context, AddFilterActivity.class);
                    editFilterIntent.putExtra("FilterName", ((TextView) view).getText());
                    startActivity(editFilterIntent);
                }
            });
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
