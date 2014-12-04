package com.awesomekids.android.quickcharades;

/**
 * Created by BrosephPKC on 12/3/2014.
 */
public enum Length {
    VOID("VOID"),
    SHORT("SHORT"),
    MEDIUM("MEDIUM"),
    LONG("LONG");

    private String value;
    private Length(String value){this.value = value;}
    public String getValue(){return this.value;}
}
