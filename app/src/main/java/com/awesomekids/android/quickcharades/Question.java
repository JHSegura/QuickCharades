package com.awesomekids.android.quickcharades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Joseph Park on 11/29/2014.
 */
public class Question {

    // instead of using Image object, use imageID instead, category contains imageID, not the other way around
//    private Image image;

    private int mImageID;

    public String getAnswer() {
        return mAnswer;
    }

    private String mAnswer;
    private ArrayList<Character> mRandomizedLetters;
    private Difficulty difficulty;
    private static int MAX_RAND = 15;
    private static int MAX_LETTER = 14;


    // TODO: Implement difficulty
    public Question(int imageID, String answer){
//        this.difficulty = difficulty;
        mRandomizedLetters = new ArrayList<Character>();
        mImageID = imageID;
        mAnswer = answer;
    }

    private void randomizeLetters() {
        if (!mRandomizedLetters.isEmpty())
            mRandomizedLetters.clear();

        Random Rand = new Random();
        for (int i = 0; i < mAnswer.length(); i++)
            mRandomizedLetters.add(mAnswer.charAt(i));
        for (int i = mAnswer.length(); i < MAX_LETTER; i++)
            mRandomizedLetters.add((char) (Rand.nextInt(26) + 'A'));
        for (int i = 0; i < MAX_RAND; i++) {
            int a = Rand.nextInt(MAX_LETTER);
            int b = Rand.nextInt(MAX_LETTER);
            Collections.swap(mRandomizedLetters, a, b); // using java collection
        }
    }

    // either pass in string or pass in charList
    public boolean isCorrect(String answer){
        return answer.equals(mAnswer);
    }

    public int getImageID(){return mImageID; }//return new Image(this.image.getName(),this.image.getCategory()); //Return a copy of the image or return the image?

    public ArrayList<Character> getRandomizedLetters() {
        randomizeLetters();
        return mRandomizedLetters;
    }

    public Difficulty getDifficulty(){return difficulty;}
    public void setDifficulty(Difficulty difficulty){this.difficulty = difficulty; }
}
