package com.company;

/**
 * Created by andrey on 23.06.15.
 */
public class InputInfo {
    private int showerTime;
    private int moveTime;
    private int flowerbedCount;
    private int sensorValue;
    private int restTime;

    public InputInfo() {
    }

    public InputInfo(int showerTime, int moveTime, int flowerbedCount, int sensorValue, int restTime) {
        this.showerTime = showerTime;
        this.moveTime = moveTime;
        this.flowerbedCount = flowerbedCount;
        this.sensorValue = sensorValue;
        this.restTime = restTime;
    }

    public int getShowerTime() {
        return showerTime;
    }

    public void setShowerTime(int showerTime) {
        this.showerTime = showerTime;
    }

    public int getMoveTime() {
        return moveTime;
    }

    public void setMoveTime(int moveTime) {
        this.moveTime = moveTime;
    }

    public int getFlowerbedCount() {
        return flowerbedCount;
    }

    public void setFlowerbedCount(int flowerbedCount) {
        this.flowerbedCount = flowerbedCount;
    }

    public int getSensorValue() {
        return sensorValue;
    }

    public void setSensorValue(int sensorValue) {
        this.sensorValue = sensorValue;
    }

    public int getRestTime() {
        return restTime;
    }

    public void setRestTime(int restTime) {
        this.restTime = restTime;
    }


}
