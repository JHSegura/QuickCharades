package com.awesomekids.android.quickcharades;

/**
 * Created by BrosephPKC on 11/29/2014.
 */
public class Stat {
    public int maxScore;
    public Streak maxStreak;
    public float WLRatio;
    public int wins;
    public int losses;
    public Stat(){
        maxScore = 0;
        maxStreak = new Streak();
        WLRatio = 0.0f;
        wins = 0;
        losses = 0;
    }
    public Stat(int maxScore, Streak maxStreak, float WLRatio, int wins, int losses){
        this.maxScore = maxScore;
        this.maxStreak = new Streak(maxStreak.getStreak());
        this.WLRatio = WLRatio;
        this.wins = wins;
        this.losses = losses;
    }
    public Stat(Stat Other){
        this.maxScore = Other.maxScore;
        this.maxStreak = new Streak(Other.maxStreak.getStreak());
        this.WLRatio = Other.WLRatio;
        this.wins = Other.wins;
        this.losses = Other.losses;
    }
    public void setStats(Stat Other){
        this.maxScore = Other.maxScore;
        this.maxStreak = new Streak(Other.maxStreak.getStreak());
        this.WLRatio = Other.WLRatio;
        this.wins = Other.wins;
        this.losses = Other.losses;
    }
}
