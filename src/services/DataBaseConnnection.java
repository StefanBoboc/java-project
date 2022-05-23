package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnnection {
    private static DataBaseConnnection single_instance = null;
    private String url = "jdbc:mysql://localhost:3306/medicalofficeappointments";
    private String user = "root";
    private String password = "";
    Connection connection = null;

    public DataBaseConnnection() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static DataBaseConnnection getInstance() {
        if(single_instance == null){
            single_instance = new DataBaseConnnection();
        }
        return single_instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
