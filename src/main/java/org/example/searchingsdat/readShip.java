package org.example.searchingsdat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class readShip {
    public static List <String[]> readShip(String filePath) {
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){

        } catch (Exception exception) {
            // TODO: handle exception
            
        }
        return data;
    }
}
