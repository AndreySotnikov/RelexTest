package com.company;

/**
 * Created by andrey on 23.06.15.
 */
public class Machine {
    private int timeToMove;
    private int timeToShower;

    private int printMove = 0;
    private int printShower = 0;

    public Machine(int timeToMove, int timeToShower) {
        this.timeToMove = timeToMove;
        this.timeToShower = timeToShower;
    }

    public boolean dosmth(){
        if (timeToMove>0) {
            if (printMove==0)
                System.out.print("Машина едет #");
            else
                System.out.print('#');
            printMove++;
            timeToMove--;
        }
        else {
            if (printShower==0) {
                System.out.println();
                System.out.print("Идет полив #");
            }
            else
                System.out.print('#');
            printShower++;
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
        printMove=0;
        printShower=0;
    }

    public boolean isFree(){
        return timeToShower==0;
    }

}
