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
    private List<Machine> machines;
    private int flowerbedCount;
    private int machineCount;
    private int criticalTemperatureValue;
    private int criticalWetnessValue;
    private int restTime;
    private List<Flowerbed> flowerbeds;
    private SensorChangeJournal sensorChangeJournal;

    public Gardener(InputInfo inputInfo) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputAllSensors inputAllSensors = mapper.readValue(Main.read(Main.inputSensorsValue), InputAllSensors.class);
        sensorChangeJournal = new SensorChangeJournal(inputAllSensors.getInputSensors());
        flowerbedCount = inputInfo.getFlowerbedCount();
        restTime = inputInfo.getRestTime();
        machines = new ArrayList<Machine>();
        machineCount = inputInfo.getMachineCount();
        for (int i = 0; i < machineCount; i++){
            machines.add(new Machine(i));
        }
        criticalTemperatureValue = inputInfo.getCriticalTemperatureValue();
        criticalWetnessValue = inputInfo.getCriticalWetnessValue();
        flowerbeds = new ArrayList<Flowerbed>();
        for (int i = 0; i < flowerbedCount; i++) {
            InputSensor inputSensor = sensorChangeJournal.getInputSensors().get(i);
            flowerbeds.add(new Flowerbed(inputInfo.getMoveTime()[i], inputInfo.getShowerTime()[i],inputSensor.getTemperatureSensor().get(0), inputSensor.getWetnessSensor().get(0), 0));
        }
        sensorChangeJournal.setFlowerbeds(flowerbeds);
        sensorChangeJournal.start();

    }


    public void work() throws InterruptedException, IOException {
        List<Machine> busyMachines = new ArrayList<Machine>();
        List<Machine> availableMachines = new ArrayList<Machine>();
        List<Flowerbed> availableFlowerbeds = new ArrayList<Flowerbed>();
        while (true) {
            System.out.println(flowerbeds);
            busyMachines.clear();
            availableMachines.clear();
            availableFlowerbeds.clear();
            for (int i=0; i<machineCount; i++){
                if (machines.get(i).getTarget() == -1)
                    availableMachines.add(machines.get(i));
                else {
                    if ((criticalTemperatureValue>=flowerbeds.get(machines.get(i).getTarget()).getTemperatureSensor()
                            && criticalWetnessValue<=flowerbeds.get(machines.get(i).getTarget()).getWetnessSensor())){
                        System.out.println("Условия нормализовались, машина " + machines.get(i).getIndex() + " стоит");
                        availableMachines.add(machines.get(i));
                    }
                    else
                        busyMachines.add(machines.get(i));
                }
            }
            for (int i=0; i<flowerbedCount; i++){
                if (!flowerbeds.get(i).isWaiting())
                    availableFlowerbeds.add(flowerbeds.get(i));
            }
            int flowerbed;
            for (int i=0; i<availableMachines.size(); i++) {
                flowerbed = pickFlowerbed(availableFlowerbeds);
                if (flowerbed != -1){
                    System.out.println("Машина отправлена к клумбе " + flowerbed);
                    System.out.println("Температура: " + flowerbeds.get(flowerbed).getTemperatureSensor() +
                            " Влажность: " + flowerbeds.get(flowerbed).getWetnessSensor());
                    Machine machine = availableMachines.get(i);
                    machine.init(flowerbeds.get(flowerbed).getTimeToMove(), flowerbeds.get(flowerbed).getTimeToShower(), flowerbed);
                    machine.dosmth();
                }
                else
                    break;
            }
            for (int i=0; i<busyMachines.size(); i++) {
                if (busyMachines.get(i).dosmth()){
                    Flowerbed tmpflowerbed = flowerbeds.get(busyMachines.get(i).getTarget());
                    tmpflowerbed.setWaiting(false);
                    tmpflowerbed.setRestTime(restTime);
                    System.out.println("Машина " + busyMachines.get(i).getIndex() + " закончила полив клумбы " + busyMachines.get(i).getTarget());
                    busyMachines.get(i).free();
                }

            }

//
//
//
//            if (currentFlowerbed!=-1 && criticalTemperatureValue>=flowerbeds.get(currentFlowerbed).getTemperatureSensor() && criticalWetnessValue<=flowerbeds.get(currentFlowerbed).getWetnessSensor()){
//                currentFlowerbed = pickFlowerbed();
//                if (currentFlowerbed==-1){
//                    System.out.println("Условия нормализовались, машина стоит");
//                    machine.free();
//                    Thread.sleep(1000);
//                    updateFlowerbeds();
//                    continue;
//                }
//                else{
//                    System.out.println("Условия изменились, ищем новую цель");
//                    machine.free();
//                }
//            }
//
//
//            if (machine.isFree()) {
//                currentFlowerbed = pickFlowerbed();
//                if (currentFlowerbed == -1) {
//                    Thread.sleep(1000);
//                    updateFlowerbeds();
//                    continue;
//                }
//
//                System.out.println("Машина отправлена к клумбе " + currentFlowerbed);
//                System.out.println("Температура: " + flowerbeds.get(currentFlowerbed).getTemperatureSensor() +
//                        " Влажность: " + flowerbeds.get(currentFlowerbed).getWetnessSensor());
//                machine.init(flowerbeds.get(currentFlowerbed).getTimeToMove(), flowerbeds.get(currentFlowerbed).getTimeToShower());
//                isDone = machine.dosmth();
//            } else {
//                isDone = machine.dosmth();
//            }
//            if (isDone)
//                flowerbeds.get(currentFlowerbed).setRestTime(restTime);
            updateFlowerbeds();
            Thread.sleep(1000);
        }
    }

    public int pickFlowerbed(List<Flowerbed> availables) {
        List<Integer> readyToWash = new ArrayList<Integer>();
        for (int i = 0; i < availables.size(); i++) {
            if ((availables.get(i).getWetnessSensor() < criticalWetnessValue
                    || availables.get(i).getTemperatureSensor() > criticalTemperatureValue) &&
                    availables.get(i).getRestTime() == 0) {
                readyToWash.add(i);
            }
        }
        if (readyToWash.isEmpty())
            return -1;
        int tmp = readyToWash.get(new Random().nextInt(readyToWash.size()));
        availables.get(tmp).setWaiting(true);
        return flowerbeds.indexOf(availables.get(tmp));
    }

    public void updateFlowerbeds() {
        for (int i = 0; i < flowerbedCount; i++) {
            if (flowerbeds.get(i).getRestTime() != 0)
                flowerbeds.get(i).setRestTime(flowerbeds.get(i).getRestTime() - 1);
        }
    }
}
