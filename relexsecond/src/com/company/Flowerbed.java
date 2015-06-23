package com.company;

/**
 * Created by andrey on 23.06.15.
 */
public class Flowerbed {
    private int temperatureSensor;
    private int wetnessSensor;
    private int restTime;

    public Flowerbed() {
    }

    public Flowerbed(int temperatureSensor, int wetnessSensor, int restTime) {
        this.temperatureSensor = temperatureSensor;
        this.wetnessSensor = wetnessSensor;
        this.restTime = restTime;
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
}
