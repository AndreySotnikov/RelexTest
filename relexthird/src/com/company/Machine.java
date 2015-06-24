package com.company;

import java.util.List;

/**
 * Created by andrey on 23.06.15.
 */
public class Machine extends Thread{
    private int timeToMove;
    private int timeToShower;

    public Machine(int timeToMove, int timeToShower) {
        this.timeToMove = timeToMove;
        this.timeToShower = timeToShower;
    }

    public boolean dosmth(){
        if (timeToMove>0) {
            System.out.println("Машина едет, осталось " + timeToMove + " минут");
            timeToMove--;
        }
        else {
            System.out.println("Идет полив, осталось " + timeToShower + " минут");
            if (timeToShower>0)
                timeToShower--;
        }
        if (timeToShower==0)
            return true;
        return false;
    }



    public void init(int timeToMove, int timeToShower) {
        this.timeToMove = timeToMove;
        this.timeToShower = timeToShower;
    }

    public void free(){
        timeToMove = 0;
        timeToShower = 0;
    }

    public boolean isFree(){
        return timeToShower==0;
    }


    public int getTimeToMove() {
        return timeToMove;
    }

    public void setTimeToMove(int timeToMove) {
        this.timeToMove = timeToMove;
    }

    public int getTimeToShower() {
        return timeToShower;
    }

    public void setTimeToShower(int timeToShower) {
        this.timeToShower = timeToShower;
    }
}
