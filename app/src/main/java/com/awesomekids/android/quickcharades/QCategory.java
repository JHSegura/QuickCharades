package com.awesomekids.android.quickcharades;

/**
 * Created by Joseph Park on 11/29/2014.
 */
public enum QCategory {
    //Categories
    VOID(-1);
    private int value;
    private QCategory(int value){this.value = value;}
    public int getValue(){return this.value;}
}
