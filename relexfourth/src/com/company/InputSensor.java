package com.company;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrey on 23.06.15.
 */
public class InputSensor {
    private ArrayList<Integer> temperatureSensor;
    private ArrayList<Integer> wetnessSensor;
    private int period;
    @JsonIgnore
    private int position = 0;
    @JsonIgnore
    private  List<Flowerbed> flowerbeds;

    public InputSensor(ArrayList<Integer> temperatureSensor, ArrayList<Integer> wetnessSensor, int period) {
        this.temperatureSensor = temperatureSensor;
        this.wetnessSensor = wetnessSensor;
        this.period = period;
    }

    public void update(int index, int time){
        if (time % period == 0){
            position++;
            if (position==temperatureSensor.size())
                position=0;
            Flowerbed flowerbed = flowerbeds.get(index);
            flowerbed.setTemperatureSensor(temperatureSensor.get(position));
            flowerbed.setWetnessSensor(wetnessSensor.get(position));
        }

    }

    public InputSensor() {
    }

    public ArrayList<Integer> getTemperatureSensor() {
        return temperatureSensor;
    }

    public void setTemperatureSensor(ArrayList<Integer> temperatureSensor) {
        this.temperatureSensor = temperatureSensor;
    }

    public ArrayList<Integer> getWetnessSensor() {
        return wetnessSensor;
    }

    public void setWetnessSensor(ArrayList<Integer> wetnessSensor) {
        this.wetnessSensor = wetnessSensor;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public List<Flowerbed> getFlowerbeds() {
        return flowerbeds;
    }

    public void setFlowerbeds(List<Flowerbed> flowerbeds) {
        this.flowerbeds = flowerbeds;
    }

    @Override
    public String toString() {
        return "InputSensor{" +
                "position=" + position +
                '}';
    }
}
