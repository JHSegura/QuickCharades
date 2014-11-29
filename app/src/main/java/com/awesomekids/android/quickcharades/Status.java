package com.awesomekids.android.quickcharades;

/**
 * Created by BrosephPKC on 11/29/2014.
 */
public enum Status {
    VOID(-1),
    OFFLINE(0),
    ONLINE(1),
    BUSY(2), //or AWAY
    INGAME(3),
    LOOKING(4); //Looking for game
    private int value;
    private Status(int value){this.value = value;}
    public int getValue(){return this.value;}
}
