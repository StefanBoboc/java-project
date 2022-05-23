package main;

import services.Service;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Service service = new Service();

        // reading data from .csv's
        // service.addCSVData();

        // reading data from JDBC
        service.addJDBCData();

        Scanner in = new Scanner(System.in);

        loop: while(true) {
            System.out.println("___MENU___");
            System.out.println("1. Add Clients\n2. Add Doctors\n3. Show Clients\n4. Show Doctors\n" +
                    "5. Make An Appointment\n6. Show Appointments Of A Client\n7. Edit Appointment\n" +
                    "8. Delete An Appointment\n9. Remove Client\n10. Remove Doctor\n0. EXIT");
            System.out.println("--------");
            System.out.print("> ");

            int command = in.nextInt();     // user command

            switch (command) {
                case 1:
                    service.addClient();
                    System.out.println("====================");
                    break;
                case 2:
                    service.addDoctor();
                    System.out.println("====================");
                    break;
                case 3:
                    service.showClient();
                    System.out.println("====================");
                    break;
                case 4:
                    service.showDoctors();
                    System.out.println("====================");
                    break;
                case 5:
                    service.makeAppointment();
                    System.out.println("====================");
                    break;
                case 6:
                    service.seeAppointment();
                    System.out.println("====================");
                    break;
                case 7:
                    service.editAppointment();
                    System.out.println("====================");
                    break;
                case 8:
                    service.removeAppointment();
                    System.out.println("====================");
                    break;
                case 9:
                    service.removeClient();
                    System.out.println("====================");
                    break;
                case 10:
                    service.removeDoctor();
                    System.out.println("====================");
                    break;
                case 0:
                    System.out.println("====================");
                    System.out.println("You left the program");
                    System.out.println("====================");
                    break loop;
                default:
                    System.out.println("Invalid command! Enter one command from 0 to 10");
            }

        }

    }
}