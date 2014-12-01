package com.awesomekids.android.quickcharades;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class DifficultySelectActivity extends ActionBarActivity {
    private Button dGoNextButton;
    private RadioGroup dDiffGroup;
    private RadioButton dEasyButton;
    private RadioButton dMedButton;
    private RadioButton dHardButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_select);
//        dGoNextButton = (Button) findViewById(R.id.diff_to_category_button);
//        dDiffGroup = (RadioGroup) findViewById(R.id.diff_button_group);
//        dEasyButton = (RadioButton) findViewById(R.id.diff_easy_button);
//        dMedButton = (RadioButton) findViewById(R.id.diff_med_button);
//        dHardButton = (RadioButton) findViewById(R.id.diff_hard_button);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_difficulty_select, menu);
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
//        Intent goNextIntent = new Intent(this,CategoryActivity.class);
//        if(dEasyButton.isSelected())
//            goNextIntent.putExtra("DIFF",Difficulty.EASY);
//        else if(dMedButton.isSelected())
//            goNextIntent.putExtra("DIFF",Difficulty.MEDIUM);
//        else if(dHardButton.isSelected())
//            goNextIntent.putExtra("DIFF",Difficulty.HARD);
//        else {}
//        //Display error: Select a difficulty before moving on
//        final int result = 1;
//        startActivity(goNextIntent);
    }
    public void onDiffSelectQuitButtonClick(View view){
        Intent goingBack = new Intent();
        setResult(RESULT_OK,goingBack);
        finish();
    }
}
