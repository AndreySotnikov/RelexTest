package com.company;

import java.util.List;

/**
 * Created by andrey on 23.06.15.
 */
public class SensorChangeJournal extends Thread{
    List<InputSensor> inputSensors;
    private int time = 0;
    private List<Flowerbed> flowerbeds;

    public SensorChangeJournal(List<InputSensor> inputSensors) {
        this.inputSensors = inputSensors;
    }

    public void update(){
        for (int i=0; i<flowerbeds.size(); i++){
            inputSensors.get(i).update(i, time);
        }
    }

    @Override
    public void run(){
        while(true){
            try {
                update();
                System.out.println(inputSensors);
                time++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Flowerbed> getFlowerbeds() {
        return flowerbeds;
    }

    public void setFlowerbeds(List<Flowerbed> flowerbeds) {
        this.flowerbeds = flowerbeds;
        for (InputSensor elem: inputSensors)
            elem.setFlowerbeds(flowerbeds);
    }

    public List<InputSensor> getInputSensors() {
        return inputSensors;
    }

    public void setInputSensors(List<InputSensor> inputSensors) {
        this.inputSensors = inputSensors;
    }
}
