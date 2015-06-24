package com.company;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static String workingDir = System.getProperty("user.dir")+"/resources/";
    public static String inputConfigFile = workingDir + "input.txt";
    public static String inputSensorsValue = workingDir + "sensors.txt";

    public static void main(String[] args) throws IOException, InterruptedException {

//        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//        InputInfo inputInfo = new InputInfo(5,10,3,30,240);
//        String message = ow.writeValueAsString(inputInfo);
        ObjectMapper mapper = new ObjectMapper();
        InputInfo inputInfo = mapper.readValue(read(inputConfigFile), InputInfo.class);
        InputAllSensors inputAllSensors = new InputAllSensors();
        for (int i = 0; i < inputInfo.getFlowerbedCount(); i++){
            inputAllSensors.add(new InputSensor(new ArrayList<Integer>(Arrays.asList(25,30,35,50,25,60)),new ArrayList<Integer>(Arrays.asList(80,75,70,80,40,60)),3));
        }
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(inputAllSensors);
        write(inputSensorsValue, json);
        //InputInfo inputInfo = new InputInfo(5,10,3,30,240);
        System.out.println("Отредактируйте журнал темпертаур и введите ОК");
        Scanner sc = new Scanner(System.in);
        while (!sc.next().equals("OK"))
            ;
        Gardener gardener = new Gardener(inputInfo);
        gardener.work();

    }

    public static void write(String fileName, String text){
        File file = new File(fileName);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
            try {
                out.print(text);
            } finally {
                out.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String read(String filename) throws IOException {
        StringBuilder sb = new StringBuilder();
        File file = new File(filename);
        BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
        String s;
        while ((s = in.readLine()) != null) {
            sb.append(s);
            sb.append("\n");
        }
        in.close();
        return sb.toString();
    }
}
