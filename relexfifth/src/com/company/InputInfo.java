package com.company;

/**
 * Created by andrey on 23.06.15.
 */
public class InputInfo {
    private int[] showerTime;
    private int[][] distances;
    private int flowerbedCount;
    private int machineCount;
    private int criticalTemperatureValue;
    private int criticalWetnessValue;
    private int restTime;

    public InputInfo() {
    }


    public int[][] getDistances() {
        return distances;
    }

    public void setDistances(int[][] distances) {
        this.distances = distances;
    }

    public int[] getShowerTime() {
        return showerTime;
    }

    public void setShowerTime(int[] showerTime) {
        this.showerTime = showerTime;
    }

    public int getFlowerbedCount() {
        return flowerbedCount;
    }

    public void setFlowerbedCount(int flowerbedCount) {
        this.flowerbedCount = flowerbedCount;
    }

    public int getMachineCount() {
        return machineCount;
    }

    public void setMachineCount(int machineCount) {
        this.machineCount = machineCount;
    }

    public int getCriticalTemperatureValue() {
        return criticalTemperatureValue;
    }

    public void setCriticalTemperatureValue(int criticalTemperatureValue) {
        this.criticalTemperatureValue = criticalTemperatureValue;
    }

    public int getCriticalWetnessValue() {
        return criticalWetnessValue;
    }

    public void setCriticalWetnessValue(int criticalWetnessValue) {
        this.criticalWetnessValue = criticalWetnessValue;
    }

    public int getRestTime() {
        return restTime;
    }

    public void setRestTime(int restTime) {
        this.restTime = restTime;
    }


}
