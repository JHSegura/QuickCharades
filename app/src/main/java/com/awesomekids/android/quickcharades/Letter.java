package com.awesomekids.android.quickcharades;

/**
 * Created by Joseph Park on 11/29/2014.
 */
public class Letter {
    private char value;
    public static char VOID_CHAR = ' ';
    public Letter(){
        value = VOID_CHAR;
    }
    public Letter(char value){
        this.value = value;
    }
    public char getValue(){
        return value;
    }
}
