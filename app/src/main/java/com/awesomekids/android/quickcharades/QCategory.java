package com.awesomekids.android.quickcharades;

/**
 * Created by Joseph Park on 11/29/2014.
 */
public enum QCategory {
    //Categories
    VOID("NONE"),
    HEROES("HEROES");
    private String name;
    private QCategory(String value){this.name = value;}
    public String getValue(){return this.name;}
}
