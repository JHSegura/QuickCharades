package com.awesomekids.android.quickcharades;

/**
 * Created by Joseph Park on 11/29/2014.
 */
public enum Difficulty {
    VOID(-1),
    EASY(0),
    MEDIUM(1),
    HARD(2);

    private int value;
    private Difficulty(int value){this.value = value;}
    public int getValue(){return this.value;}
}

