package com.awesomekids.android.quickcharades;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Joseph Park on 11/29/2014.
 */
public class Question {
    private Image image;
    private ArrayList<Letter> letterList;
    private Difficulty difficulty;
    private static int RAND_CONSTANT = 15;
    private static int MAX_LETTER = 10;

    public Question(){
        image = new Image();
        letterList = new ArrayList<Letter>();
        difficulty = Difficulty.VOID;
    }
    public Question(Image image, Difficulty difficulty){
        this.image = new Image(image.getName(),image.getCategory());
        letterList = new ArrayList<Letter>();
        this.difficulty = difficulty;
        randomizeLetters(image.getName(),MAX_LETTER);
    }
    private void randomizeLetters(String imageName, int numOfLetters){
        Random Rand = new Random();
        for(int i = 0; i < imageName.length(); i++)
            letterList.add(i,new Letter(imageName.charAt(i)));
        for(int i = imageName.length(); i < numOfLetters; i++)
            letterList.add(i,new Letter((char)(Rand.nextInt(26)+'0')));
        for(int i = 1; i <= RAND_CONSTANT; i++){
            int a = Rand.nextInt(numOfLetters);
            int b = Rand.nextInt(numOfLetters);
            //char temp = letterList.get(a).getValue();
            Letter temp = new Letter(letterList.get(a).getValue());
            letterList.set(a,letterList.get(b));
            letterList.set(b,temp);
        }
    }
    public Image getImage(){
        //return new Image(this.image.getName(),this.image.getCategory());
        return image; //Return a copy of the image or return the image?
    }
    public ArrayList<Letter> getList(){
        return letterList; //Same with this one ^
    }
    public Difficulty getDifficulty(){
        return difficulty;
    }
    public void setImage(Image image){
        this.image.setName(image.getName());
        this.image.setCategory(image.getCategory());
    }
    public void setDifficulty(Difficulty difficulty){
        this.difficulty = difficulty;
    }
    public void rerollLetters(){
        randomizeLetters(image.getName(),MAX_LETTER);
    }
    public boolean isCorrect(String answer){
        return answer.equals(image.getName());
    }
}
