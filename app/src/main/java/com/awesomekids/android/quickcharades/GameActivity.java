package com.awesomekids.android.quickcharades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Tony Lim on 11/27/14.
 */
public class GameActivity extends Activity {


    private ImageView mImagePortrait;

    private TextView mAnswerTextView;
    private TextView mScoreTextView;
    private TextView mStreakView;

    private Button mEnterButton;
    private Button mClearButton;
    private Button mSkipButton;


    private final int RUNTIME = 45000; // 45 seconds
    protected boolean mbActive;
    private ProgressBar mTimerBar;

    static private Player mPlayer; // implement Singletons

    private Toast mAnswerToast;
    private int mCurrentQuestion;
    private int mMaxQuestion;

    //TODO : find a way to store ad retrieve image from database
    private Integer[] mImageIds = { R.drawable.image_001, R.drawable.image_002, R.drawable.image_003};

    // TODO : find a way to store and retrive strings from database
    private String[] mImageNames = {
            "IRONMAN",
            "AANG",
            "MARIO"
    };

    private ArrayList<Question> mGameQuestions;

    static private Integer[] sLettersButtonId = {
            R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9, R.id.button10, R.id.button11, R.id.button12, R.id.button13, R.id.button14
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);



        // To pass informatin from main screen
        Intent activityThatCalled = getIntent();
//        String previousActivity = activityThatCalled.getExtras().getString("callingActivity");


        mCurrentQuestion = 0;
        mMaxQuestion = mImageIds.length;
        mImagePortrait = (ImageView) findViewById (R.id.game_imageView);
        mImagePortrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextQuestion();
            }
        });

        mAnswerTextView = (TextView) findViewById(R.id.game_answer_textview);
        mScoreTextView = (TextView) findViewById(R.id.game_score_textview);
        mStreakView = (TextView) findViewById(R.id.game_streak_number);


        mEnterButton    = (Button) findViewById(R.id.game_button_enter);
        mEnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();


            }
        });
        mClearButton = (Button) findViewById(R.id.game_clear_button);
        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnswerTextView.setText("");
                resetAllLettersButton();
            }
        });
        mSkipButton = (Button) findViewById(R.id.game_skip_button);
        mSkipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mPlayer.currentScore -= 20;
                goToNextQuestion();
            }
        });

        // TODO : find a way to load informations from player database
        if(mPlayer == null)
            mPlayer = new Player();
        else { //temporary fix, import data using singletons design (static player)
            mScoreTextView.setText("" + mPlayer.currentScore);
            mStreakView.setText("" + mPlayer.currentStreak);
        }

        // ISSUE : This can only be run once
        mTimerBar = (ProgressBar) findViewById(R.id.barTimer);
        final Thread timerThread = new Thread() {
            @Override
            public void run() {
                mbActive = true;
                try {
                    int waited = 0;
                    while (mbActive && (waited < RUNTIME)) {
                        sleep(200); // the progress bar update every 200 ms
                        if (mbActive) {
                            waited += 200;
                            updateProgress(waited);
                        }
                    }
                } catch (InterruptedException e) {
                    //do nothing
                } finally {
                    onContinue();
                }
            }
        };
        // TODO : Fix progress bar
//        timerThread.start(); // Cause crash and very slow


        // TODO : Use information from category to generate different Questions into mGameQuestions
        loadAllQuestions(); // this will allocate and get resources for the game
                            // new question does not automatically generate random letters
        setupAllLettersButton();
    } // end of onCreate

    /**
     * Function to run and show the countdown timer progress bar
     */
//    private void runTimer() {

//        mbActive = true;
//        final Thread t = new Thread() {
//            @Override
//            public void run() {
//                int jumpTime = 0;
//                while (jumpTime < totalProgressTime) {
//                    try {
//                        sleep(200);
//                        jumpTime += 5;
//                        mTimerBar.setProgress(jumpTime);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//        t.start();
//    }


    public void updateProgress(final int timePassed) {
        if(null != mTimerBar) {
            // Ignore rounding error here
            final int progress = mTimerBar.getMax() * timePassed / RUNTIME;
            mTimerBar.setProgress(progress);
        }
    }

    public void onContinue() {
        // perform any final actions here
        goToNextQuestion();
    }

    /**
     * Load questions
     */
    private void loadAllQuestions() {
        mGameQuestions = new ArrayList<>();
        for (int i = 0; i < mImageNames.length; i++) {
            mGameQuestions.add(new Question(mImageIds[i], mImageNames[i]));
        }
    }

    public void letterClicked(View v){
        v.setClickable(false);
        v.setVisibility(v.INVISIBLE);

        // going to have to append from somewhere else besides extracting letters from layout
        Button b = (Button)v;
        String buttonLetter = b.getText().toString();

        mAnswerTextView.append(buttonLetter);
        //text.setText(buttonLetter);
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

    /**
     * This will generate random letters according to current question
     * Display the random letters and unhide them
     */
    public void setupAllLettersButton() {
        // setting up the visibility
        ArrayList<Character> randomLetters = mGameQuestions
                .get(mCurrentQuestion)
                .getRandomizedLetters(); // each time this is called, it will always be random

//        Log.d("Debugging actual answer", mGameQuestions.get(mCurrentQuestion).getAnswer());
//        Log.d("Debugging random letters(count manually) ", randomLetters.toString());
//        Log.d("Length of sLetterButtonId", "" + sLettersButtonId.length);

        Button myLetterView;
        for (int i = 0; i < sLettersButtonId.length; i++) {
            myLetterView = (Button) findViewById(sLettersButtonId[i]);
            myLetterView.setText("" + randomLetters.get(i)); // have to be string
            myLetterView.setClickable(true);
            myLetterView.setVisibility(View.VISIBLE);
        }
    }

    public void resetAllLettersButton() {
        Button myLetterView;
        for (int i = 0; i < sLettersButtonId.length; i++) {
            myLetterView = (Button) findViewById(sLettersButtonId[i]);
            myLetterView.setClickable(true);
            myLetterView.setVisibility(View.VISIBLE);
        }
    }

    private void checkAnswer() {
        boolean isCorrect = false;
        if( mAnswerTextView.getText().toString().equals( mGameQuestions.get(mCurrentQuestion).getAnswer()))
            isCorrect = true;
        int toastMessageID = 0;
        toastMessageID = ( isCorrect ?
                R.string.toast_correct_notification :
                R.string.toast_incorrect_notification);
        mAnswerToast = Toast.makeText(this, toastMessageID, Toast.LENGTH_SHORT);
        mAnswerToast.setGravity(Gravity.CENTER, 0, 0);
        mAnswerToast.show();

        processScore(isCorrect); // update score, streak, and display

        if (isCorrect) {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 1.5s = 1500ms
                    goToNextQuestion();
                }
            }, 1500);
        }
    }

    public void processScore(boolean trueFalse) {
        // TODO : Implement a multipler and different score for different difficulty
        if(trueFalse) {
            mPlayer.currentScore += 100; // for now, use 100
            mPlayer.currentStreak += 1;
        }
        else {
            mPlayer.currentScore -= 20; // temporary
            mPlayer.currentStreak = 0;
        }
        mScoreTextView.setText("" + mPlayer.currentScore);
        mStreakView.setText("" + mPlayer.currentStreak);
    }

    public void goToNextQuestion() {
//        mTimerBar.setProgress(0);
        // TODO : Fix the crashing bug here if it is set to 0
        mCurrentQuestion = ++mCurrentQuestion % mMaxQuestion;
        mImagePortrait.setImageResource(mImageIds[mCurrentQuestion]);
        mAnswerTextView.setText("");
        setupAllLettersButton();
    }

}
