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
import android.widget.ListAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;


public class CategoryActivity extends ActionBarActivity {
    private Button cGoNextButton;
    private ListView mCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        addItemsToCategoryList();

    }

    public void addItemsToCategoryList() {
        // instead of creating strings from the class, use xml resources, different fron settings
        mCategoryList = (ListView) findViewById(R.id.category_ListView);
        ArrayAdapter<CharSequence>  categoryListAdapter = ArrayAdapter.createFromResource(this,
                R.array.category_types, android.R.layout.simple_list_item_1);
        mCategoryList.setAdapter(categoryListAdapter);
    }



    public void onGoNextButtonClick(View view){
        Intent goNextIntent = new Intent(this,GameModeActivity.class);
        startActivity(goNextIntent);
    }
    public void onDiffSelectQuitButtonClick(View view){
        Intent goingBack = new Intent();
        setResult(RESULT_OK,goingBack);
        finish();
    }
}
