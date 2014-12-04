package com.awesomekids.android.quickcharades;

/**
 * Created by BrosephPKC on 11/30/2014.
 */
public enum Mode {
    VOID("VOID"),
    SINGLE("SINGLE"),
    FRIEND("FRIEND"),
    RANDOM("RANDOM");

    private String value;
    private Mode(String value){this.value = value;}
    public String getValue(){return this.value;}
}
