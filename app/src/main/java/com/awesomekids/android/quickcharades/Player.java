package com.awesomekids.android.quickcharades;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Joseph Park on 11/29/2014.
 */
public class Player implements Parcelable {
    private int playerID;
    public String playerName;
    private Stat playerStats;
    public int currentScore;
    public int currentStreak;
    public int currentQanswered;

    private Status status;
    private ArrayList<Player> friendsList; //How are we going to do this?
    private int rank;
    //Settings Preference?

    public Player(){
        playerID = 0;
        playerName = "";
        playerStats = new Stat();
        currentScore = 0;
        currentStreak = 0;
        currentQanswered = 0;
        status = Status.VOID;
        friendsList = new ArrayList<Player>();
        rank = 0;
    }
    public Player(int ID, String name, Stat stats, int currentScore, int currentStreak,
                  Status status, ArrayList<Player> friendsList, int rank){
        playerID = ID;
        playerName = name;
        playerStats = new Stat(stats);
        this.currentScore = currentScore;
        this.currentStreak = currentStreak;
        this.status = status;
        this.friendsList = new ArrayList<Player>(friendsList);
        this.rank = rank;
    }
    public Player(Player player){
        playerID = player.getPlayerID();
        playerName = player.getName();
        playerStats = new Stat(player.getStats());
        this.currentScore = player.getCurrentScore();
        currentStreak = 0;
        this.status = player.getStatus();
        this.friendsList = new ArrayList<Player>(player.getFriendsList());
        this.rank = player.getRank();
    }
    public int getPlayerID(){return playerID;}
    public String getName(){return playerName;}
    public Stat getStats(){return playerStats;}//or return a copy?
    public int getCurrentScore(){return currentScore;}
    public Status getStatus(){return status;}
    public ArrayList<Player> getFriendsList(){return friendsList;} //Same
    public int getRank(){return rank;}

    public void setPlayerID(int ID){playerID = ID;}
    public void setName(String name){playerName = name;}
    public void setPlayerStats(Stat stats){playerStats.setStats(stats);}
    public void setCurrentScore(int score){currentScore = score;}
    public void setStatus(Status status){this.status = status;}
    public void setRank(int rank){this.rank = rank;}

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
    public void resetStreak(){currentStreak = 0;}
    public void resetStats(){playerStats.setStats(new Stat());}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt();
        // TODO : Implement Parcelable for Player
        // http://stackoverflow.com/questions/2139134/how-to-send-an-object-from-one-android-activity-to-another-using-intents
    }
}
