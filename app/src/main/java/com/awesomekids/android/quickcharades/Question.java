package com.awesomekids.android.quickcharades;

import java.util.Random;

/**
 * Created by Joseph Park on 11/29/2014.
 */
public class Question {
    private Image image;
    private char[] letterList;
    private Difficulty difficulty;
    private static int MAX_RAND = 15;
    private static int MAX_LETTER = 14;

    public Question(){
        image = new Image();
        letterList = new char[MAX_LETTER];
        difficulty = Difficulty.VOID;
    }
    public Question(Image image, Difficulty difficulty){
        this.image = new Image(image.getName(),image.getCategory(),image.getImageID());
        this.difficulty = difficulty;
        randomizeLetters(image.getName());
    }
    private void randomizeLetters(String imageName){
        Random Rand = new Random();
        for(int i = 0; i < imageName.length(); i++)
            letterList[i] = imageName.charAt(i);
        for(int i = imageName.length(); i < MAX_LETTER; i++)
            letterList[i] = (char)(Rand.nextInt(26)+'A');
        for(int i = 0; i < MAX_RAND; i++){
            int a = Rand.nextInt(MAX_LETTER);
            int b = Rand.nextInt(MAX_LETTER);
            swap(letterList,a,b);
        }
//        Random Rand = new Random(); //Test randomize, as well as find a better way to randomize
//        letterList.clear();
//        for(int i = 0; i < imageName.length(); i++)
//            letterList.add(i,new Letter(imageName.charAt(i)));
//        for(int i = imageName.length(); i < numOfLetters; i++)
//            letterList.add(i,new Letter((char)(Rand.nextInt(26)+'0')));
//        for(int i = 1; i <= RAND_CONSTANT; i++){
//            int a = Rand.nextInt(numOfLetters);
//            int b = Rand.nextInt(numOfLetters);
//            //char temp = letterList.get(a).getValue();
//            Letter temp = new Letter(letterList.get(a).getValue());
//            letterList.set(a,letterList.get(b));
//            letterList.set(b,temp);
//        }
    }
    private void swap(char[] cArray, int a, int b ){
        char temp = cArray[a];
        cArray[a] = cArray[b];
        cArray[b] = temp;
    }
    public Image getImage(){return image; }//return new Image(this.image.getName(),this.image.getCategory()); //Return a copy of the image or return the image?
    public char[] getList(){return letterList; }//Same with this one ^
    public Difficulty getDifficulty(){return difficulty;}
    public void setImage(Image image){
        this.image.setName(image.getName());
        this.image.setCategory(image.getCategory());
        this.image.setImageID(image.getImageID());
        randomizeLetters(image.getName());
    }
    public void setDifficulty(Difficulty difficulty){this.difficulty = difficulty; }
    public void rerollLetters(){randomizeLetters(image.getName());}
    public boolean isCorrect(String answer){return answer.equals(image.getName());}
}
