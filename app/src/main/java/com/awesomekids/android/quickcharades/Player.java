package com.awesomekids.android.quickcharades;

import java.util.ArrayList;

/**
 * Created by Joseph Park on 11/29/2014.
 */
public class Player {
    private int playerID;
    private String playerName;
    private Stat playerStats;
    private int currentScore;
    private Streak currentStreak;
    private Status status;
    private ArrayList<Player> friendsList; //How are we going to do this?
    private int rank;
    //Settings Preference?

    public Player(){
        playerID = 0;
        playerName = "";
        playerStats = new Stat();
        currentScore = 0;
        currentStreak = new Streak();
        status = Status.VOID;
        friendsList = new ArrayList<Player>();
        rank = 0;
    }
    public Player(int ID, String name, Stat stats, int currentScore, Streak currentStreak,
                  Status status, ArrayList<Player> friendsList, int rank){
        playerID = ID;
        playerName = name;
        playerStats = new Stat(stats);
        this.currentScore = currentScore;
        currentStreak = new Streak(currentStreak.getStreak());
        this.status = status;
        this.friendsList = new ArrayList<Player>(friendsList);
        this.rank = rank;
    }
    public Player(Player player){
        playerID = player.getPlayerID();
        playerName = player.getName();
        playerStats = new Stat(player.getStats());
        this.currentScore = player.getCurrentScore();
        currentStreak = new Streak(player.getCurrentStreak().getStreak());
        this.status = player.getStatus();
        this.friendsList = new ArrayList<Player>(player.getFriendsList());
        this.rank = player.getRank();
    }
    public int getPlayerID(){return playerID;}
    public String getName(){return playerName;}
    public Stat getStats(){return playerStats;}//or return a copy?
    public int getCurrentScore(){return currentScore;}
    public Streak getCurrentStreak(){return currentStreak;} //Same as stats;
    public Status getStatus(){return status;}
    public ArrayList<Player> getFriendsList(){return friendsList;} //Same
    public int getRank(){return rank;}

    public void setPlayerID(int ID){playerID = ID;}
    public void setName(String name){playerName = name;}
    public void setPlayerStats(Stat stats){playerStats.setStats(stats);}
    public void setCurrentScore(int score){currentScore = score;}
    public void setStatus(Status status){this.status = status;}
    public void setRank(int rank){this.rank = rank;}
    public void setCurrentStreak(Streak streak){currentStreak.setStreak(streak.getStreak());}

    public boolean addFriend(Player friend){
        //Scan database to check if player exists
        //if false return false
        //if true
        friendsList.add(friendsList.size(),friend);
        return true;
    }
    public boolean removeFriend(Player friend){
        boolean check = false;
        int index = -1;
        for(int i = 0; i < friendsList.size(); i++)
            if (friendsList.get(i).getPlayerID() == friend.getPlayerID()) {
                check = true;
                index = i;
                break;
            }
        if(check) friendsList.remove(index);
        return check;
    }

    public void resetRank(){rank = 0;}
    public void resetScore(){currentScore = 0;}
    public void resetStreak(){currentStreak.setStreak(0);}
    public void resetStats(){playerStats.setStats(new Stat());}
}
