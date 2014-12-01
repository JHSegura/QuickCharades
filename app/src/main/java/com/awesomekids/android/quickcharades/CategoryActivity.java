package com.awesomekids.android.quickcharades;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;


public class CategoryActivity extends ActionBarActivity {
    private Button cGoNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

//        String[] gameOptionStrings = {"Difficulty", "Volume Control", "Brightness", "Exit"};
//
//        ListAdapter settingsAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1,
//                gameOptionStrings);
//
//        ListView settingsListView  = (ListView) findViewById(R.id.settings_ListView);
//        settingsListView.setAdapter(settingsAdapter);
//        settingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String optionString = String.valueOf(parent.getItemAtPosition(position));
//                if (optionString.equals("Exit"))
//                    finish();
//            }
//        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onGoNextButtonClick(View view){
        Intent goNextIntent = new Intent(this,GameModeActivity.class);

        final int result = 1;
        startActivity(goNextIntent);
    }
    public void onDiffSelectQuitButtonClick(View view){
        Intent goingBack = new Intent();
        setResult(RESULT_OK,goingBack);
        finish();
    }
}
