package com.awesomekids.android.quickcharades;

/**
 * Created by Josue on 12/7/2014.
 */
public class Account {

    private int _id;
    private String _accountName;

    public Account () {this._id = 0; this._accountName = "None";}
    private Account (int id, String accountName){this._id = id; this._accountName = accountName;}
    public void setId(int id){this._id = id;}
    public int getId(){return this._id;}
    public void setAccountName(String accountName){this._accountName = accountName;}
    public String getAccountName() {return this._accountName;}
}
