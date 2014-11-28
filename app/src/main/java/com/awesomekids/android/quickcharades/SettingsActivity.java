package com.awesomekids.android.quickcharades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by Tony Lim on 11/27/14.
 */
public class SettingsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        String[] gameOptionStrings = {"Difficulty", "Volume Control", "Brightness", "Exit"};

        ListAdapter settingsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                gameOptionStrings);

        ListView settingsListView  = (ListView) findViewById(R.id.settings_ListView);
        settingsListView.setAdapter(settingsAdapter);
        settingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String optionString = String.valueOf(parent.getItemAtPosition(position));
                if(optionString.equals("Exit"))
                    finish();
            }
        });

//        settingsListView.setOnClickListener(
//                new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                        String optionString = String.valueOf(adapterView.getItemAtPosition(position));
//                        if(optionString.equals("Exit"))
//                            finish();
//                    }
//                }
//        );

        // To pass informatin from main screen
        Intent activityThatCalled = getIntent();
//        String previousActivity = activityThatCalled.getExtras().getString("callingActivity");

    }

    public void onSettingQuitButtonClick(View view) {

        Intent goingBack = new Intent();
//        goingBack.putExtra("some key", value);

        setResult(RESULT_OK,goingBack);
        finish();
    }
}
