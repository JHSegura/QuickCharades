package com.awesomekids.android.quickcharades;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//Ignore for now
public class FriendsActivity extends Activity {
    private Button fAddFriendButton;
    private EditText fAddFriendTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        fAddFriendButton = (Button) findViewById(R.id.add_friend_button);
        fAddFriendTextField = (EditText) findViewById(R.id.add_friend_text_field);
        //Find a way to store friends
    }

    public void onAddFriendButtonClick(View view){
        //Get name from text field
        String friendName = fAddFriendTextField.getText().toString();
        //Access calling player's friends list  and addfriend()

    }
    public void onFriendsQuitButtonClick(View view) {
        Intent goingBack = new Intent();
        setResult(RESULT_OK,goingBack);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friends, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Here pressing on a friend in the list should allow for delete friend/invite friend
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //Intent getFriendsMenuIntent = new Intent(this,) //Go to friends menu with delete friend and invite friend option  (//Maybe we can just have delete friend

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
