package com.awesomekids.android.quickcharades;

/**
 * Created by Joseph Park on 11/29/2014.
 */
public class Stat {
    public int maxScore;
    public int maxStreak;
    public float WLRatio;
    public int wins;
    public int losses;
    public Stat(){
        maxScore = 0;
        maxStreak = 0;
        WLRatio = 0.0f;
        wins = 0;
        losses = 0;
    }
    public Stat(int maxScore, int maxStreak, float WLRatio, int wins, int losses){
        this.maxScore = maxScore;
        this.maxStreak = maxStreak;
        this.WLRatio = WLRatio;
        this.wins = wins;
        this.losses = losses;
    }
    public Stat(Stat Other){
        this.maxScore = Other.maxScore;
        this.maxStreak = Other.maxStreak;
        this.WLRatio = Other.WLRatio;
        this.wins = Other.wins;
        this.losses = Other.losses;
    }
    public void setStats(Stat Other){
        this.maxScore = Other.maxScore;
        this.maxStreak = Other.maxStreak;
        this.WLRatio = Other.WLRatio;
        this.wins = Other.wins;
        this.losses = Other.losses;
    }
}
