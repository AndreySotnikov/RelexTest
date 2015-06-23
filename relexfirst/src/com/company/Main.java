package com.company;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
        Gardener gardener = new Gardener(inputInfo);
        gardener.work();

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
