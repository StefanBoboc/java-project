package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Read {
    private static Read single_instance = null;
    private Read(){}

    public static Read getInstance(){
        if(single_instance == null){
            single_instance = new Read();
        }
        return single_instance;
    }

    public ArrayList<String> csvReader(String fileName){
        ArrayList<String> lines = new ArrayList<>();

        try {
            File myObj = new File(fileName);
            Scanner in = new Scanner(myObj);
            while (in.hasNextLine()) {
                String data = in.nextLine();
                lines.add(data);
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading CSV file "+fileName);
            e.printStackTrace();
        }
        return lines;
    }
}
