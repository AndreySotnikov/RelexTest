package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by andrey on 23.06.15.
 */
public class Gardener {
    private Machine machine;
    private int flowerbedCount;
    private int showerTime;
    private int moveTime;
    private int criticalSensorValue;
    private int restTime;
    private int sensorValue;
    private List<Integer> flowerbeds;

    public Gardener(InputInfo inputInfo) {
        machine = new Machine(0, 0);
        flowerbedCount = inputInfo.getFlowerbedCount();
        showerTime = inputInfo.getShowerTime();
        moveTime = inputInfo.getMoveTime();
        criticalSensorValue = inputInfo.getSensorValue();
        restTime = inputInfo.getRestTime();
        flowerbeds = new ArrayList<Integer>();
        for (int i=0; i<flowerbedCount; i++){
            flowerbeds.add(0);
        }
    }

    public void updateSensors() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputSensor inputSensor = mapper.readValue(Main.read(Main.inputSensorsValue), InputSensor.class);
        sensorValue = inputSensor.getValue();
    }

    public void work() throws InterruptedException, IOException {
        int currentFlowerbed=-1;
        boolean isDone;
        while (true){
            System.out.println(flowerbeds);
            updateSensors();
            if (sensorValue > criticalSensorValue){
                if (machine.isFree()){
                    currentFlowerbed = pickFlowerbed();
                    System.out.println("Машина отправлена к клумбе " + currentFlowerbed);
                    machine.init(moveTime, showerTime);
                    isDone = machine.dosmth();
                }
                else {
                    isDone = machine.dosmth();
                }
                if (isDone)
                    flowerbeds.set(currentFlowerbed, restTime);
            }
            updateFlowerbeds();
            Thread.sleep(1000);
        }
    }

    public int pickFlowerbed(){
        List<Integer> readyToWash = new ArrayList<Integer>();
        for (int i=0; i< flowerbedCount; i++){
            if (flowerbeds.get(i)==0)
                readyToWash.add(i);
        }
        if (flowerbeds.isEmpty())
            return -1;
        return readyToWash.get(new Random().nextInt(readyToWash.size()));
    }

    public void updateFlowerbeds(){
        for (int i=0; i< flowerbedCount; i++){
            if (flowerbeds.get(i)!=0)
                flowerbeds.set(i, flowerbeds.get(i)-1);
        }
    }
}
