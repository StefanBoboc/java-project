package services;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class Write {
    private static Write single_instance = null;
    private Write(){}

    public static Write getInstance(){
        if(single_instance == null){
            single_instance = new Write();
        }
        return single_instance;
    }

    public void csvWriter(String message){
        Timestamp time = new Timestamp(System.currentTimeMillis());

        try {
            FileWriter myWriter = new FileWriter("audit.csv", true);
            myWriter.write(message + "," + time + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing in audit.csv");
            e.printStackTrace();
        }
    }
}
