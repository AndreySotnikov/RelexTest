package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

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
    private int[] flowerbeds;

    public Gardener(InputInfo inputInfo) {
        machine = new Machine(0, 0);
        flowerbedCount = inputInfo.getFlowerbedCount();
        showerTime = inputInfo.getShowerTime();
        moveTime = inputInfo.getMoveTime();
        criticalSensorValue = inputInfo.getSensorValue();
        restTime = inputInfo.getRestTime();
        flowerbeds = new int[flowerbedCount];
    }

    public void updateSensors() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputSensor inputSensor = mapper.readValue(Main.read(Main.inputSensorsValue), InputSensor.class);
        sensorValue = inputSensor.getValue();
    }

    public void work() throws InterruptedException, IOException {
        Timer timer = new Timer();
        Worker worker = new Worker();
        timer.schedule(worker, 0, 1000);
    }

    public int pickFlowerbed() {
        List<Integer> readyToWash = new ArrayList<Integer>();
        for (int i = 0; i < flowerbedCount; i++) {
            if (flowerbeds[i] == 0)
                readyToWash.add(i);
        }
        if (readyToWash.isEmpty())
            return -1;
        return readyToWash.get(new Random().nextInt(readyToWash.size()));
    }

    public void updateFlowerbeds() {
        for (int i = 0; i < flowerbedCount; i++) {
            if (flowerbeds[i] != 0)
                flowerbeds[i] = flowerbeds[i]-1;
        }
    }

    class Worker extends TimerTask {
        private int currentFlowerbed = -1;
        private boolean isDone;
        private int time = 0;
        @Override
        public void run() {
            //System.out.println(Arrays.toString(flowerbeds));
            boolean ok=true;
            try {
                updateSensors();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (sensorValue > criticalSensorValue) {
                if (machine.isFree()) {
                    currentFlowerbed = pickFlowerbed();
                    if (currentFlowerbed==-1)
                        ok = false;
                    if (ok) {
                        System.out.println();
                        System.out.println("Текущее время: " + time + " минут");
                        System.out.println("Машина отправлена к клумбе " + currentFlowerbed);
                        machine.init(moveTime, showerTime);
                        isDone = machine.dosmth();
                    }
                } else {
                    isDone = machine.dosmth();
                }
                if (ok && isDone)
                    flowerbeds[currentFlowerbed] = restTime;
            }
            time++;
            updateFlowerbeds();
        }
    }
}

