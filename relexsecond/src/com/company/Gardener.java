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
    private int criticalTemperatureValue;
    private int criticalWetnessValue;
    private int restTime;
    private List<Flowerbed> flowerbeds;
    private SensorChangeJournal sensorChangeJournal;

    public Gardener(InputInfo inputInfo) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputAllSensors inputAllSensors = mapper.readValue(Main.read(Main.inputSensorsValue), InputAllSensors.class);
        sensorChangeJournal = new SensorChangeJournal(inputAllSensors.getInputSensors());
        machine = new Machine(0, 0);
        flowerbedCount = inputInfo.getFlowerbedCount();
        showerTime = inputInfo.getShowerTime();
        moveTime = inputInfo.getMoveTime();
        restTime = inputInfo.getRestTime();
        criticalTemperatureValue = inputInfo.getCriticalTemperatureValue();
        criticalWetnessValue = inputInfo.getCriticalWetnessValue();
        flowerbeds = new ArrayList<Flowerbed>();
        for (int i = 0; i < flowerbedCount; i++) {
            InputSensor inputSensor = sensorChangeJournal.getInputSensors().get(i);
            flowerbeds.add(new Flowerbed(inputSensor.getTemperatureSensor().get(0), inputSensor.getWetnessSensor().get(0), 0));
        }
        sensorChangeJournal.setFlowerbeds(flowerbeds);
        sensorChangeJournal.start();

    }


    public void work() throws InterruptedException, IOException {
        int currentFlowerbed = -1;
        boolean isDone;
        while (true) {
            System.out.println(flowerbeds);
            if (currentFlowerbed!=-1 && criticalTemperatureValue<=flowerbeds.get(currentFlowerbed).getTemperatureSensor() && criticalWetnessValue>=flowerbeds.get(currentFlowerbed).getWetnessSensor()){
                Thread.sleep(1000);
                updateFlowerbeds();
                continue;
            }
            if (machine.isFree()) {
                currentFlowerbed = pickFlowerbed();
                if (currentFlowerbed == -1) {
                    Thread.sleep(1000);
                    updateFlowerbeds();
                    continue;
                }

                System.out.println("Машина отправлена к клумбе " + currentFlowerbed);
                System.out.println("Температура: " + flowerbeds.get(currentFlowerbed).getTemperatureSensor() +
                        " Влажность: " + flowerbeds.get(currentFlowerbed).getWetnessSensor());
                machine.init(moveTime, showerTime);
                isDone = machine.dosmth();
            } else {
                isDone = machine.dosmth();
            }
            if (isDone)
                flowerbeds.get(currentFlowerbed).setRestTime(restTime);
            updateFlowerbeds();
            Thread.sleep(1000);
        }
    }

    public int pickFlowerbed() {
        List<Integer> readyToWash = new ArrayList<Integer>();
        for (int i = 0; i < flowerbedCount; i++) {
            if ((flowerbeds.get(i).getWetnessSensor() < criticalWetnessValue
                    || flowerbeds.get(i).getTemperatureSensor() > criticalTemperatureValue) &&
                    flowerbeds.get(i).getRestTime() == 0) {
                readyToWash.add(i);
            }
        }
        if (readyToWash.isEmpty())
            return -1;
        return readyToWash.get(new Random().nextInt(readyToWash.size()));
    }

    public void updateFlowerbeds() {
        for (int i = 0; i < flowerbedCount; i++) {
            if (flowerbeds.get(i).getRestTime() != 0)
                flowerbeds.get(i).setRestTime(flowerbeds.get(i).getRestTime() - 1);
        }
    }
}
