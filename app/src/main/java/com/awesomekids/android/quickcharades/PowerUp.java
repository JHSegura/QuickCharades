package com.awesomekids.android.quickcharades;

/**
 * Created by Joseph Park on 11/29/2014.
 */
public class PowerUp {
    private String name;
    private int requirement;
    private boolean multiplayerOnly;

    public PowerUp(){
        name = "";
        requirement = -1;
        multiplayerOnly = false;
    }
    public PowerUp(String name, int requirement, boolean multiplayerOnly){
        this.name = name;
        this.requirement = requirement;
        this.multiplayerOnly = multiplayerOnly;
    }
    public String getName(){return name;}
    public int getRequirement(){return requirement;}
    public boolean getMultiplayerOnly(){return multiplayerOnly;}
    public void setName(String name){this.name = name;}
    public void setRequirement(int requirement){this.requirement = requirement;}
    public void setMultiplayerOnly(boolean multiplayerOnly){this.multiplayerOnly = multiplayerOnly;}

}
