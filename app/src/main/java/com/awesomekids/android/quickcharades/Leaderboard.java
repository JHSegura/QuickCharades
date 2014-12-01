package com.awesomekids.android.quickcharades;

import java.util.ArrayList;

/**
 * Created by Joseph Park on 11/29/2014.
 */
public class Leaderboard {
    private ArrayList<Player> playerList;
    public Leaderboard(){
        playerList = new ArrayList<Player>();
    }
    public Leaderboard(ArrayList<Player> playerList){
        this.playerList = new ArrayList<Player>(playerList);
    }

    public boolean addPlayer(Player player){
        //Check to see if player exists
        playerList.add(player);
        sort();
        return true;
    }
    public boolean removePlayer(Player player){
        int j = -1;
        for(int i = 0; i < playerList.size(); i++){
            if(playerList.get(i).getPlayerID() == player.getPlayerID()){
                j = i;
                break;
            }
        }
        if(j < 0) return false;
        playerList.remove(j);
        sort();
        return true;
    }

    public void reset(){
        playerList.clear();
    }
    public void sort(){ //Find a better way to sort
        for(int i = 0; i < playerList.size()-1; i++){
            int minPos = minPos(i);
            Player temp = new Player(playerList.get(i));
            playerList.set(i,playerList.get(minPos));
            playerList.set(minPos,temp);
        }
    }
    private int minPos(int from){
        int minPos = from;
        for(int i = from + 1; i < playerList.size(); i++)
            if(playerList.get(i).getRank() < playerList.get(minPos).getRank()) minPos = i;
        return minPos;
    }

}
