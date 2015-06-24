package com.company;

/**
 * Created by andrey on 23.06.15.
 */
public class Flowerbed {
    private int timeToShower;
    private int temperatureSensor;
    private int wetnessSensor;
    private int restTime;
    private boolean waiting;

    public Flowerbed() {
    }

    public Flowerbed(int timeToShower, int temperatureSensor, int wetnessSensor, int restTime) {
        this.timeToShower = timeToShower;
        this.temperatureSensor = temperatureSensor;
        this.wetnessSensor = wetnessSensor;
        this.restTime = restTime;
        waiting = false;
    }

    public boolean isWaiting() {
        return waiting;
    }

    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
    }


    public int getTimeToShower() {
        return timeToShower;
    }

    public void setTimeToShower(int timeToShower) {
        this.timeToShower = timeToShower;
    }

    public int getTemperatureSensor() {
        return temperatureSensor;
    }

    public void setTemperatureSensor(int temperatureSensor) {
        this.temperatureSensor = temperatureSensor;
    }

    public int getWetnessSensor() {
        return wetnessSensor;
    }

    public void setWetnessSensor(int wetnessSensor) {
        this.wetnessSensor = wetnessSensor;
    }

    public int getRestTime() {
        return restTime;
    }

    public void setRestTime(int restTime) {
        this.restTime = restTime;
    }

    @Override
    public String toString() {
        return Integer.toString(restTime);
    }
}
