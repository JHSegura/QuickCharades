package com.awesomekids.android.quickcharades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Tony Lim on 11/27/14.
 */
public class GameActivity extends Activity {


    private ImageView mImagePortrait;
    private int mCurrent;
    private int mMaxImage;
    private Integer[] mImageIds = { R.drawable.image_001, R.drawable.image_002, R.drawable.image_003};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_game);
        // To pass informatin from main screen
        Intent activityThatCalled = getIntent();
//        String previousActivity = activityThatCalled.getExtras().getString("callingActivity");

        mCurrent = 0;
        mMaxImage = mImageIds.length;
        mImagePortrait = (ImageView) findViewById (R.id.game_imageView);

        mImagePortrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImagePortrait.setBackgroundResource(mImageIds[++mCurrent % mMaxImage]);
            }
        });
    }



    public void onGameQuitButtonClick(View view) {

        Intent goingBack = new Intent();
//        goingBack.putExtra("some key", value);

        setResult(RESULT_OK,goingBack);
        finish();
    }



    public void onGameOptionButtonClick(View view) {
        Intent getSettingsIntent = new Intent(this,
                SettingsActivity.class);
        final int result = 1; //result id

        startActivity(getSettingsIntent);
    }
}
