package com.awesomekids.android.quickcharades;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class GameSetupActivity extends ActionBarActivity {
    private TextView numQText;
    private TextView numTText;
    private Spinner diffSpinner;
    private Spinner modeSpinner;
    private Spinner categorySpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setup);
        numQText = (TextView)findViewById(R.id.text_number_of_questions_value);
        numTText = (TextView)findViewById(R.id.text_timer_value);
        Button goButton = (Button) findViewById(R.id.go_to_game_button);
        createDiffSpinner();
        createModeSpinner();
        createCategorySpinner();
    }
    public void createDiffSpinner(){
        diffSpinner = (Spinner) findViewById(R.id.spinner_diff_select);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.diff_select_array,android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        diffSpinner.setAdapter(adapter);
        diffSpinner.setOnItemSelectedListener(new DiffSpinnerActivity());
    }
    public void createModeSpinner(){
        modeSpinner = (Spinner) findViewById(R.id.spinner_mode_select);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.mode_select_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modeSpinner.setAdapter(adapter);
        modeSpinner.setOnItemSelectedListener(new ModeSpinnerActivity());
    }
    public void createCategorySpinner(){
        categorySpinner = (Spinner) findViewById(R.id.spinner_category_select);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,new ArrayList<String>());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
        for(QCategory C : QCategory.values()) {
            if(C.getValue() != "NONE"){
                adapter.add(C.getValue());
                adapter.notifyDataSetChanged();
                categorySpinner.setSelection(adapter.getPosition(C.getValue()));
            }
        }
        categorySpinner.setOnItemSelectedListener(new CategorySpinnerActivity());
    }

    public void onGoToPlayButtonClick(View view){
        Intent goPlayIntent = new Intent(this,GameActivity.class);
        int[] gameSettings = new int[2]; //Maybe better if we store as strings instead
        gameSettings[0] = diffSpinner.getSelectedItem().toString() == "Easy" ? Difficulty.EASY.getValue() :
                (diffSpinner.getSelectedItem().toString() == "Normal" ?
                        Difficulty.MEDIUM.getValue() : Difficulty.HARD.getValue());
        gameSettings[1] = modeSpinner.getSelectedItem().toString() == "Play Alone" ? Mode.SINGLE.getValue() :
                (modeSpinner.getSelectedItem().toString() == "Play With A Friend" ?
                        Mode.FRIEND.getValue() : Mode.RANDOM.getValue());
//        numQText.setText(diffSpinner.getSelectedItem().toString()); //Just used to test if the above works
//        numTText.setText(modeSpinner.getSelectedItem().toString());
        goPlayIntent.putExtra(categorySpinner.getSelectedItem().toString(),gameSettings); //Pass along the game mode/difficulty/category info to gameactivity so that it can begin an appropriate game
        startActivity(goPlayIntent);
    }
    public class DiffSpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            // An item was selected. You can retrieve the selected item using
            // parent.getItemAtPosition(pos)
            //Set difficulty here
            String diff = parent.getItemAtPosition(pos).toString();

            if(diff.compareTo("Easy")==0){ //Can always change later
                numQText.setText("15");
                numTText.setText("45s");
            } //easy
            else if(diff.compareTo("Normal")==0){
                numQText.setText("30");
                numTText.setText("30s");
            }//set to normal
            else if(diff.compareTo("Hard")==0){
                numQText.setText("50");
                numTText.setText("15s");
            }//hard
            else{

            }
                //error
        }

        public void onNothingSelected(AdapterView<?> parent) {
            Toast.makeText(getBaseContext(),"Difficulty not selected",Toast.LENGTH_SHORT).show();
        }
    }

    public class ModeSpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener { //Implement stuff to show related to game modes

        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            // An item was selected. You can retrieve the selected item using
            // parent.getItemAtPosition(pos)
            //Set game mode here

        }

        public void onNothingSelected(AdapterView<?> parent) {
            Toast.makeText(getBaseContext(),"Game Mode not selected",Toast.LENGTH_SHORT).show();
        }
    }

    public class CategorySpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener { //Implement stuff to show related to categories

        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            // An item was selected. You can retrieve the selected item using
            // parent.getItemAtPosition(pos)
            //Set category here

        }

        public void onNothingSelected(AdapterView<?> parent) {
            Toast.makeText(getBaseContext(),"Category not selected",Toast.LENGTH_SHORT).show();
        }
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_game_setup, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
