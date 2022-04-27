package main;

import services.Read;
import services.Service;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Service service = new Service();

        // reading data from .csv's
        service.addDoctorCSV();
        service.addClientsCSV();
        service.makeAppointmentAnalysesCSV();
        service.makeAppointmentGynecologyCSV();

        Scanner in = new Scanner(System.in);

        while(true) {
            System.out.println("___MENU___");
            System.out.println("1. Add Clients\n2. Add Doctors\n3. Show Clients\n4. Show Doctors\n5. Make An Appointment\n6. Show Appointments Of A Client\n7. Edit Appointment\n8. Delete An Appointment\n9. Remove Client\n10. Remove Doctor\n0. EXIT");
            System.out.println("--------");
            System.out.print("> ");

            int command = in.nextInt();

            if (command == 1){
                service.addClient();
                System.out.println("====================");
            }
            else if (command == 2){
                service.addDoctor();
                System.out.println("====================");
            }
            else if (command == 3){
                service.showClient();
                System.out.println("====================");
            }
            else if (command == 4){
                service.showDoctors();
                System.out.println("====================");
            }
            else if (command == 5){
                service.makeAppointment();
                System.out.println("====================");
            }
            else if (command == 6){
                service.seeAppointment();
                System.out.println("====================");
            }
            else if (command == 7){
                service.editAppointment();
                System.out.println("====================");
            }
            else if (command == 8){
                service.removeAppointment();
                System.out.println("====================");
            }
            else if (command == 9){
                service.removeClient();
                System.out.println("====================");
            }
            else if (command == 10){
                service.removeDoctor();
                System.out.println("====================");
            }
            else if (command == 0) {
                System.out.println("====================");
                System.out.println("You left the program");
                System.out.println("====================");
                break;
            }
            else {
                System.out.println("Invalid command! Enter one command from 0 to 10");
            }
        }

    }
}