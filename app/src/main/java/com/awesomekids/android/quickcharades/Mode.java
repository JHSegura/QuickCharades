package com.awesomekids.android.quickcharades;

/**
 * Created by BrosephPKC on 11/30/2014.
 */
public enum Mode {
    VOID(-1),
    SINGLE(0),
    FRIEND(1),
    RANDOM(2);

    private int value;
    private Mode(int value){this.value = value;}
    public int getValue(){return this.value;}
}
