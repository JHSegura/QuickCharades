package com.awesomekids.android.quickcharades;

/**
 * Created by Joseph Park on 11/29/2014.
 */
public enum Difficulty {
    VOID("VOID"),
    EASY("EASY"),
    MEDIUM("NORMAL"),
    HARD("HARD");

    private String value;
    private Difficulty(String value){this.value = value;}
    public String getValue(){return this.value;}
}

