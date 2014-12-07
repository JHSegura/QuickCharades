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
    private Spinner lengthSpinner;

    private Button mPlayButton;

    public static final String KEY_DIFF = "difficulty";
    public static final String KEY_LEN = "length";
    public static final String KEY_MODE = "mode";
    public static final String KEY_CAT = "category";

    private static final int T_EASY = 20;
    private static final int T_NORM = 15;
    private static final int T_HARD = 10;
    private static final int Q_SHORT = 10;
    private static final int Q_MED = 20;
    private static final int Q_LONG = 30;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setup);
        numQText = (TextView)findViewById(R.id.text_number_of_questions_value);
        numTText = (TextView)findViewById(R.id.text_timer_value);
        mPlayButton = (Button) findViewById(R.id.go_to_game_button);
        MainActivity.addButtonEffectOn(mPlayButton);
        createDiffSpinner();
        createModeSpinner();
        createCategorySpinner();
        createLengthSpinner();
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

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,new ArrayList<String>());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
        for(QCategory C : QCategory.values()) {
            if(!C.getValue().equals(QCategory.VOID.getValue())){
                adapter.add(C.getValue());
                adapter.notifyDataSetChanged();
                categorySpinner.setSelection(adapter.getPosition(C.getValue()));
            }
        }
        categorySpinner.setOnItemSelectedListener(new CategorySpinnerActivity());
    }
    public void createLengthSpinner(){
        lengthSpinner = (Spinner) findViewById(R.id.spinner_length_select);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.length_select_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lengthSpinner.setAdapter(adapter);
        lengthSpinner.setOnItemSelectedListener(new LengthSpinnerActivity());
    }

    public void onGoToPlayButtonClick(View view){
        Intent goPlayIntent = new Intent(this,GameActivity.class); //Set this back to gameactivity
                String hold = diffSpinner.getSelectedItem().toString().equals("Easy") ? Difficulty.EASY.getValue() :
                (diffSpinner.getSelectedItem().toString().equals("Normal") ?
                Difficulty.MEDIUM.getValue() : Difficulty.HARD.getValue());
        goPlayIntent.putExtra(KEY_DIFF,hold);

        hold = modeSpinner.getSelectedItem().toString().equals("Play Alone") ? Mode.SINGLE.getValue() :
                (modeSpinner.getSelectedItem().toString().equals("Play With A Friend") ?
                        Mode.FRIEND.getValue() : Mode.RANDOM.getValue());
        goPlayIntent.putExtra(KEY_MODE,hold);

        hold = lengthSpinner.getSelectedItem().toString().equals("Short") ? Length.SHORT.getValue() :
                (lengthSpinner.getSelectedItem().toString().equals("Medium") ?
                        Length.MEDIUM.getValue() : Length.LONG.getValue());
        goPlayIntent.putExtra(KEY_LEN,hold);
        goPlayIntent.putExtra(KEY_CAT,categorySpinner.getSelectedItem().toString());

        startActivityForResult(goPlayIntent, 0);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == 0)
            this.finish();
    }

    public class DiffSpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            String diff = parent.getItemAtPosition(pos).toString();
            switch(diff){
               case "Easy":{
                   numTText.setText(""+T_EASY);
                   break;
               }
               case "Normal":{
                   numTText.setText(""+T_NORM);
                   break;
               }
               case "Hard":{
                   numTText.setText(""+T_HARD);
                   break;
               }
               default : break;
            }
        }
        public void onNothingSelected(AdapterView<?> parent) {
            Toast.makeText(getBaseContext(),"Difficulty not selected",Toast.LENGTH_SHORT).show();
        }
    }

    public class ModeSpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener { //Implement stuff to show related to game modes

        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
        }

        public void onNothingSelected(AdapterView<?> parent) {
            Toast.makeText(getBaseContext(),"Game Mode not selected",Toast.LENGTH_SHORT).show();
        }
    }

    //Not sure if this and mode listener is even necessary
    public class CategorySpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {

        }

        public void onNothingSelected(AdapterView<?> parent) {
            Toast.makeText(getBaseContext(),"Category not selected",Toast.LENGTH_SHORT).show();
        }
    }
    public class LengthSpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener { //Implement stuff to show related to categories
        public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
            String length = parent.getItemAtPosition(pos).toString();
            switch(length){
                case "Short":{
                    numQText.setText(""+Q_SHORT);
                    break;
                }
                case "Medium":{
                    numQText.setText(""+Q_MED);
                    break;
                }
                case "Long":{
                    numQText.setText(""+Q_LONG);
                    break;
                }
                default : break;
            }
        }
        public void onNothingSelected(AdapterView<?> parent) {
            Toast.makeText(getBaseContext(), "Length not selected", Toast.LENGTH_SHORT).show();
        }
    }
}
