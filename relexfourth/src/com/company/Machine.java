package com.company;

import java.util.List;

/**
 * Created by andrey on 23.06.15.
 */
public class Machine extends Thread{
    private int index;
    private int timeToMove;
    private int timeToShower;
    private int target;

    public Machine(int index) {
        this.timeToMove = 0;
        this.timeToShower = 0;
        this.index = index;
        this.target = -1;
    }

    public boolean dosmth(){
        if (timeToMove>0) {
            System.out.println("Машина " + index + " едет к клумбе " + target + ", осталось " + timeToMove + " минут");
            timeToMove--;
        }
        else {
            System.out.println("Машина " + index + " поливает клумбу " + target + ", осталось " + timeToShower + " минут");
            if (timeToShower>0)
                timeToShower--;
        }
        if (timeToShower==0){
            return true;
        }
        return false;
    }



    public void init(int timeToMove, int timeToShower, int target) {
        this.timeToMove = timeToMove;
        this.timeToShower = timeToShower;
        this.target = target;
    }

    public void free(){
        timeToMove = 0;
        timeToShower = 0;
        target = -1;
    }

    public boolean isFree(){
        return target==-1;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
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
