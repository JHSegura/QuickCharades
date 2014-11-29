package com.awesomekids.android.quickcharades;

/**
 * Created by BrosephPKC on 11/29/2014.
 */
public class Streak {
    int streak;
    public Streak(){
        streak = 0;
    }
    public Streak(int value){
        streak = value;
    }
    public int getStreak(){
        return streak;
    }
    public void setStreak(int streak){
        this.streak = streak;
    }
}
