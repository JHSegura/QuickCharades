package com.awesomekids.android.quickcharades;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
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

    public static final String KEY_ACCOUNT = "account"; // will use later
    public static final String KEY_TOTALTIME = "total_time";
    public static final String KEY_TOTALQUESTION = "total_question";

    private static final int BAR_REFRESH_RATE = 1000; // every 1 sec

    private ImageView mImagePortrait;

    private TextView mAnswerTextView;
    private TextView mScoreTextView;
    private TextView mStreakView;
    private TextView mPlayerName;
    String name;

    private Button mEnterButton;
    private Button mClearButton;
    private Button mSkipButton;


//    private final int RUNTIME = 15000; // 15 seconds
    protected boolean mbActive;
    private ProgressBar mTimerBar;
    private Thread mTimerThread;

    static public Player mPlayer; // implement Singletons

    private Toast mAnswerToast;
    private int mCurrentQuestion;
    private int mMaxQuestion;
    private String mDiff;
    private String mCat;
    private String mLen;
    private String mMode;

    private int time;
    private int numQ;
    private boolean gameEnded;




    //TODO : find a way to store ad retrieve image from database
    private Integer[] mImageIds = { R.drawable.image_001, R.drawable.image_002, R.drawable.image_003};

    // TODO : find a way to store and retrive strings from database
    private String[] mImageNames = {
            "IRONMAN",
            "AANG",
            "MARIO"
    };




    private int mTimeElapsed;
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

        ///////Here we get information about diff, len, cat, and mode from setup
        mDiff = activityThatCalled.getStringExtra(GameSetupActivity.KEY_DIFF);
        mLen = activityThatCalled.getStringExtra(GameSetupActivity.KEY_LEN);
        mCat = activityThatCalled.getStringExtra(GameSetupActivity.KEY_CAT); //Use this to figure out which q's to get
        mMode = activityThatCalled.getStringExtra(GameSetupActivity.KEY_MODE); //FOR FUTURE USE: Use this to determine matchmaking
        //Set the time and number of q's from the above
        time = mDiff.equals(Difficulty.EASY.getValue()) ? GameSetupActivity.T_EASY :
                (mDiff.equals(Difficulty.MEDIUM.getValue()) ? GameSetupActivity.T_NORM : GameSetupActivity.T_HARD);
        time *= 1000;
        numQ = mLen.equals(Length.SHORT.getValue()) ? GameSetupActivity.Q_SHORT :
                (mLen.equals(Length.MEDIUM.getValue()) ? GameSetupActivity.Q_MED : GameSetupActivity.Q_LONG);


//        String previousActivity = activityThatCalled.getExtras().getString("callingActivity");
        mCurrentQuestion = 0;
        mTimeElapsed = 0;
        mMaxQuestion = mImageIds.length;
        mImagePortrait = (ImageView) findViewById (R.id.game_imageView);
        mAnswerTextView = (TextView) findViewById(R.id.game_answer_textview);
        mScoreTextView = (TextView) findViewById(R.id.game_score_textview);
        mStreakView = (TextView) findViewById(R.id.game_streak_number);
        mPlayerName = (TextView) findViewById(R.id.accountName);
        mEnterButton = (Button) findViewById(R.id.game_button_enter);

        setPlayerName();

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
        MainActivity.addButtonEffectOn(mClearButton);

        mSkipButton = (Button) findViewById(R.id.game_skip_button);
        mSkipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextQuestion();
            }
        });
        MainActivity.addButtonEffectOn(mSkipButton);

        mTimerBar = (ProgressBar) findViewById(R.id.barTimer);

        loadAllQuestions(); // this will allocate and get resources for the game
        setupPlayerDisplay();
        // new question does not automatically generate random letters
        // TODO : Use information from category to generate different Questions into mGameQuestions
        setupAllLettersButton();
        startCountSession(); // start the first countdownSession
    } // end of onCreate

    /**
     * Very inefficient multithreading, no time to fix
     * TODO : Implement Thread countDownThread @Override: onStop()
     * @Ovveride onStop() { super.onStop(); if (countDownThread != null) { countDownThread.interrupt(); } }
     */

    public void setPlayerName ()
    {
        AccountDatabaseHandler db = new AccountDatabaseHandler(null);

        mPlayerName.setText(name);
    }

    public void startCountSession() {
        final Handler mProgressHandler;
        mProgressHandler = new Handler();

        mTimerThread = new Thread() {
            @Override
            public void run() {
                int waited = 0;
                try {
                    while ((waited < time)) { //RUNTIME = time
                        sleep(GameActivity.BAR_REFRESH_RATE); // the progress bar update every 1000 ms
                        waited += GameActivity.BAR_REFRESH_RATE;
                        mTimeElapsed += GameActivity.BAR_REFRESH_RATE/1000; // add 1 seconds
                        updateProgress(waited);
                    }

                    // need Multithreading to understand this,
                    // basicly, inside this thread, we can't change whatever happens in MainActivity
                    // so, we need Handler
                    mProgressHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            onTimeOut(); // time out
                        }
                    });
                } catch (InterruptedException e) {
                    //do nothing
                } finally {
                    //do nothing
                }
            }
        };
        mTimerThread.start();
    }


    // Only used when starting a new game
    public void setupPlayerDisplay() {
        // TODO : find a way to load informations from player database
//        if(mPlayer == null)
        mPlayer = new Player(); // always make new player
        // after loading players , or using previous player , display their stats
        mScoreTextView.setText("" + mPlayer.currentScore);
        mStreakView.setText("" + mPlayer.currentStreak);
    }

    public void updateProgress(final int timePassed) {
        if(null != mTimerBar) {
            // Ignore rounding error here
            final int progress = mTimerBar.getMax() * timePassed / time; //RUNTIME = time
            mTimerBar.setProgress(progress);
        }
    }


    /**
     * This function will run when the progress bar is ended
     */
    public void onTimeOut() {
        // perform any final actions here
        hideAllLetter();
        disableButton(mEnterButton);
        disableButton(mSkipButton);

        mAnswerToast = Toast.makeText(this, getString(R.string.toast_time_out), Toast.LENGTH_SHORT);
        mAnswerToast.setGravity(Gravity.CENTER, 0, 0);
        mAnswerToast.show();

        // a copy from checkAnswer, TODO : find a way to integrate the two
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                goToNextQuestion();
            }
        }, 1500);
    }






    /**
     * Load questions
     */
    private void loadAllQuestions() { //Here
        mGameQuestions = new ArrayList<>();
        for (int i = 0; i < mImageIds.length; i++) { //mImageIds.length() = numQ
            mGameQuestions.add(new Question(mImageIds[i], mImageNames[i]));
        }
    }






    public void hideAllLetter() {
        Button letterButtons;
        for (Integer id : sLettersButtonId) {
            letterButtons = (Button) findViewById(id);
            letterButtons.setVisibility(View.INVISIBLE);
        }
    }

    public void disableButton(Button button) {
        button.setTextColor(Color.RED);
        button.setClickable(false);
    }

    public void enableButton(Button button) {
        button.setTextColor(Color.BLACK);
        button.setClickable(true);
    }


    public void letterClicked(View v){
        v.setClickable(false);
        v.setVisibility(v.INVISIBLE);

        // going to have to append from somewhere else besides extracting letters from layout
        Button b = (Button)v;
        String buttonLetter = b.getText().toString();
        mAnswerTextView.append(buttonLetter);
    }

    public void onGameQuitButtonClick(View view) {
        turnOffTimer();
        setResult(0);
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
            mPlayer.currentQanswered++;
            turnOffTimer();
            disableButton(mSkipButton);
            disableButton(mEnterButton);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
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
            // update player's maxStreak
            if(mPlayer.currentStreak > mPlayer.getStats().maxStreak)
                mPlayer.getStats().maxStreak = mPlayer.currentStreak;
        }
        else {
            mPlayer.currentScore -= 20; // temporary
            mPlayer.currentStreak = 0;
        }
        mScoreTextView.setText("" + mPlayer.currentScore);
        mStreakView.setText("" + mPlayer.currentStreak);
    }

    /**
     * This call will go to next question and terminating timerThread
     * if there's no more question, go to ResultActivity
     */
    public void goToNextQuestion() {
        // always allow button to be clicked before exiting the GameActivity
        enableButton(mSkipButton);
        enableButton(mEnterButton);

        mCurrentQuestion = ++mCurrentQuestion % mMaxQuestion;
        if(mCurrentQuestion == 0)
            gameEnded = true;
        // check if the question reaches mMaximum, or, return to 0
        // but since this function can only be called after first question, we only check for 0
        // if so, go to ResultActivity

        mTimerBar.setProgress(0);
        turnOffTimer(); // this will clean previous thread if it hasnt ended yet
        if(gameEnded) {
            endGame();
        } else {
            mImagePortrait.setImageResource(mImageIds[mCurrentQuestion]);
            mAnswerTextView.setText("");
            setupAllLettersButton();
            startCountSession(); // this starts the next n thread, how does android manage memory?
        }

    }

    public void resetGame() {
        gameEnded = false;
        mCurrentQuestion = 0;
        setProgress(0);
        setupPlayerDisplay();
        mImagePortrait.setImageResource(mImageIds[mCurrentQuestion]);
        mAnswerTextView.setText("");
        setupAllLettersButton();
        turnOffTimer();
        startCountSession(); // this starts the next n thread, how does android manage memory?
    }


    public void endGame() {
        Intent i = new Intent(GameActivity.this, GameResultActivity.class);
        i.putExtra(GameActivity.KEY_TOTALTIME, mTimeElapsed);
        i.putExtra(GameActivity.KEY_TOTALQUESTION, mMaxQuestion);
        i.putExtra(GameSetupActivity.KEY_DIFF,mDiff);
        i.putExtra(GameSetupActivity.KEY_CAT,mCat);
        i.putExtra(GameSetupActivity.KEY_MODE,mMode);
        i.putExtra(GameSetupActivity.KEY_LEN,mLen);
        mTimeElapsed = 0;
        //Get score and streak as well
        // use intent only for temporary info
        startActivityForResult(i, 0);
    }


    // function to contain duplicate codes
    private void turnOffTimer() {
        if (null != mTimerThread) { // end timer
            mTimerThread.interrupt();
            mTimerThread = null;
        }
    }

    /**
     * Save information to pass on to next unpaused activity ( ex: switch portrait/landcape mode)
     * @param savedInstanceState
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        turnOffTimer();
    }
    @Override
    protected void onStop() {
        turnOffTimer();
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 1) {
            resetGame();
        }
        else {
            setResult(0);
            finish();
        }
    }
}
