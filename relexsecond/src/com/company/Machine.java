package com.company;

/**
 * Created by andrey on 23.06.15.
 */
public class Machine {
    private int timeToMove;
    private int timeToShower;

    public Machine(int timeToMove, int timeToShower) {
        this.timeToMove = timeToMove;
        this.timeToShower = timeToShower;
    }

    public boolean dosmth(){
        if (timeToMove>0) {
            System.out.println("Машина едет");
            timeToMove--;
        }
        else {
            System.out.println("Идет полив");
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

    public boolean isFree(){
        return timeToShower==0;
    }

}
