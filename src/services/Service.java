package services;

import objects.*;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class Service {
//    ArrayList<Client> clients;
    Map<Integer, Client> clients;
    ArrayList<Doctor> doctors;

    Write audit = Write.getInstance();

    public Service() {
//        this.clients = new ArrayList<>();
        this.clients = new HashMap<>();
        this.doctors = new ArrayList<>();
    }

    public void addClientsCSV(){
        Read reading = Read.getInstance();

        String fileName = "clients.csv";
        ArrayList<String> lines = reading.csvReader(fileName);
        int noLines = lines.size();

        for(int i = 1; i < noLines; i++){
            String []line = lines.get(i).split(",");

            Client c = new Client(line[0], line[1], line[2], line[3], line[4]);
            clients.put(c.getClientID(), c);
        }
        audit.csvWriter("addClientsCSV");
    }

    public void addClient() {
        Scanner in = new Scanner(System.in);

        System.out.println("=> Clients Data Entry");
        System.out.print("-> Number of clients to be added: ");
        int n = in.nextInt();
        in.nextLine();

        for (int i = 1; i <= n; i++) {
            System.out.print("Last Name Client_" + i + ": ");
            String lastName = in.nextLine();

            System.out.print("First Name Client_" + i + ": ");
            String firstName = in.nextLine();

            System.out.print("Date of Birth Client_" + i + ": ");
            String dateOfBirth = in.nextLine();

            System.out.print("Email Address Client_" + i + ": ");
            String emailAddress = in.nextLine();

            System.out.print("Phone Number Client_" + i + " (optional: n for 'null'): ");
            String phoneNumber = in.nextLine();
            Pattern pattern = Pattern.compile("^\\d{10}$");
            Matcher matcher = pattern.matcher(phoneNumber);
            boolean matchFound = matcher.find();

            if ((phoneNumber.equals("n") == true) || matchFound == true) {
                Client c = new Client(lastName, firstName, dateOfBirth, emailAddress, phoneNumber);
                clients.put(c.getClientID(), c);

            } else {
                while ((phoneNumber.equals("n") != true) && (matchFound != true)) {
                    System.out.println("!You must enter a valid phone number. If you do not want a phone number, enter 'n'!");
                    System.out.print("Phone Number Client_" + i + ": ");
                    phoneNumber = in.nextLine();
                    matcher = pattern.matcher(phoneNumber);
                    matchFound = matcher.find();

                    if (phoneNumber.equals("n") == true) {
                        Client c = new Client(lastName, firstName, dateOfBirth, emailAddress, phoneNumber);
                        clients.put(c.getClientID(), c);
                        break;
                    } else if (matchFound == true) {
                        Client c = new Client(lastName, firstName, dateOfBirth, emailAddress, phoneNumber);
                        clients.put(c.getClientID(), c);
                        break;
                    }
                }

            }
        }
        audit.csvWriter("addClient");
    }

    public void showClient() {
        System.out.println("=> Clients: ");
        for (Integer i : clients.keySet()){
            System.out.println(clients.get(i).showPerson());
        }
//        for(int i = 0; i < clients.size(); i++) {
//            System.out.println(clients.get(i).showPerson());
//        }
        audit.csvWriter("showClient");
    }

    public void addDoctorCSV(){
        Read reading = Read.getInstance();

        String fileName = "doctors.csv";
        ArrayList<String> lines = reading.csvReader(fileName);
        int noLines = lines.size();

        for(int i = 1; i < noLines; i++){
            String []line = lines.get(i).split(",");

            Doctor d = new Doctor(line[0], line[1], line[2], line[3], parseInt(line[4]), line[5]);
            doctors.add(d);
//            for(int j = 0; j < line.length; j++){
//                System.out.println(line[j]);
//            }
//            System.out.println("--------");
        }
        audit.csvWriter("addDoctorCSV");
    }

    public void addDoctor() {
        Scanner in = new Scanner(System.in);

        System.out.println("=> Doctors Data Entry");
        System.out.print("-> Number of doctors to be added: ");

        int n = in.nextInt();
        in.nextLine();

        for (int i = 1; i <= n; i++) {
            System.out.print("Last Name Doctor_" + i + ": ");
            String lastName = in.nextLine();

            System.out.print("First Name Doctor_" + i + ": ");
            String firstName = in.nextLine();

            System.out.print("Date of Birth Doctor_" + i + ": ");
            String dateOfBirth = in.nextLine();

            System.out.print("Email Address Doctor_" + i + ": ");
            String emailAddress = in.nextLine();

            System.out.print("Years Of Experience Doctor_" + i + ": ");
            int yearsOfExperience = in.nextInt();
            in.nextLine();

            System.out.print("Type Doctor_" + i + ": ");
            String type = in.nextLine();

            Doctor d = new Doctor(lastName, firstName, dateOfBirth, emailAddress, yearsOfExperience, type);
            doctors.add(d);
        }
        audit.csvWriter("addDoctor");
    }

    public void showDoctors() {
        System.out.println("=> Doctors: ");
        for(int i = 0; i < doctors.size(); i++) {
            System.out.println(doctors.get(i).showPerson());
        }
        audit.csvWriter("showDoctor");
    }

    public void makeAppointmentAnalysesCSV() {
        Read reading = Read.getInstance();

        String fileName = "analyses.csv";
        ArrayList<String> lines = reading.csvReader(fileName);
        int noLines = lines.size();

        for(int i = 1; i < noLines; i++){
            String []line = lines.get(i).split(",");

            Client c;
            c = clients.get(parseInt(line[0]));

            Analyses a = new Analyses(line[1], line[2], line[3], line[4]);
            c.addAppointment(a);
        }
        audit.csvWriter("makeAppointmentAnalysesCVS");
    }

    public void makeAppointmentGynecologyCSV() {
        Read reading = Read.getInstance();

        String fileName = "gynecology.csv";
        ArrayList<String> lines = reading.csvReader(fileName);
        int noLines = lines.size();

        for(int i = 1; i < noLines; i++){
            String []line = lines.get(i).split(",");

            Client c;
            c = clients.get(parseInt(line[0]));

            Gynecology g = new Gynecology(line[1], line[2], line[3], line[4]);
            c.addAppointment(g);
        }

        audit.csvWriter("makeAppointmentGynecologyCSV");
    }

    public void makeAppointment() {
        Scanner in = new Scanner(System.in);

        System.out.println("=> Make an appointment:");
        System.out.print("-> The ID of the client who wants to make the appointment: ");
        int cID = in.nextInt();
        in.nextLine();

        Client c;
        c = clients.get(cID);

        System.out.print("Time of the appointment (hh:mm): ");
        String time = in.nextLine();

        System.out.print("Day of the appointment: ");
        String day = in.nextLine();
//        in.nextLine();

        System.out.print("Month of the appointment: ");
        String month = in.nextLine();
//        in.nextLine();

        System.out.print("Type of appointment: examination or analyses; (e/a): ");
        String typeAppointment = in.nextLine();

        while((typeAppointment.equals("e") != true) && (typeAppointment.equals("a") != true)){
            System.out.println("!Invalid option. You must enter 'e' or 'a'!");
            System.out.print("Type of appointment: ");
            typeAppointment = in.nextLine();
        }

        if(typeAppointment.equals("e") == true) {
            System.out.println("=> Examination: ");
            System.out.print("-> Which department: pediatrics, gynecology or obstetrics; (p/g/o): ");
            String typeExamination = in.nextLine();

            while((typeExamination.equals("p") != true) && (typeExamination.equals("g"))!= true && (typeExamination.equals("o")!= true)){
                System.out.println("!Invalid option. You must enter 'p', 'g' or 'o'!");
                System.out.print("Type of examination: ");
                typeExamination = in.nextLine();
            }

            if(typeExamination.equals("p") == true){
                System.out.println("# Pediatrics: ");

                System.out.print("Child full name: ");
                String childFullName = in.nextLine();

                System.out.print("Child age: ");
                int childAge = in.nextInt();
                in.nextLine();

                System.out.print("Child sex (f/m): ");
                String childSex = in.nextLine();

                while((childSex.equals("f") != true) && (childSex.equals("m") != true)){
                    System.out.println("!Invalid option. You must enter 'f' or 'm'!");
                    System.out.print("Child sex: ");
                    childSex = in.nextLine();
                }

                Appointment aux = new Pediatrics(time, day, month, childFullName, childAge, childSex);
                c.addAppointment(aux);
            }
            else if(typeExamination.equals("g") == true){
                System.out.println("# Gynecology: ");

                System.out.print("Briefly introduce the symptoms: ");
                String symptoms = in.nextLine();

                Appointment aux = new Gynecology(time, day, month, symptoms);
                c.addAppointment(aux);
            }
            else if(typeExamination.equals("o") == true){
                System.out.println("# Obstetrics: ");

                System.out.print("Month of pregnancy: ");
                int monthOfPregnancy = in.nextInt();
                in.nextLine();

                System.out.print("Pregnancy number: ");
                int pregnancyNumber = in.nextInt();
                in.nextLine();

                System.out.print("Did you have an abortion? (y/n) ");
                String abortions = in.nextLine();

                while((abortions.equals("y") != true) && (abortions.equals("n") != true)){
                    System.out.println("!Invalid option. You must enter 'y' or 'n'!");
                    System.out.print("Did you have an abortion? ");
                    abortions = in.nextLine();
                }

                Appointment aux = new Obstetrics(time, day, month, monthOfPregnancy, pregnancyNumber, abortions);
                c.addAppointment(aux);
            }
        }
        else if(typeAppointment.equals("a") == true){
            System.out.println("=> Analyses: ");

            System.out.print("-> Type of analysis: detailed or routine; (d/r): ");
            String optionOfAnalyses = in.nextLine();

            while((optionOfAnalyses.equals("d") != true) && (optionOfAnalyses.equals("r") != true)){
                System.out.println("!Invalid option. You must enter 'd' or 'r'!");
                System.out.print("-> Type of analysis: ");
                optionOfAnalyses = in.nextLine();
            }

            Appointment aux = new Analyses(time, day, month, optionOfAnalyses);
            c.addAppointment(aux);
        }
        audit.csvWriter("makeAppointment");
    }

    public void seeAppointment(){
        Scanner in = new Scanner(System.in);

        System.out.println("=> Show Appointments Of A Client:");
        System.out.print("-> The ID of the client: ");
        int cID = in.nextInt();

        Client c = clients.get(cID);

        c.showAppointments();

        audit.csvWriter("seeAppointment");
    }

    public void removeAppointment(){
        Scanner in = new Scanner(System.in);

        System.out.println("=> Remove An Appointment Of A Client:");
        System.out.print("-> The ID of the client: ");
        int cID = in.nextInt();

        Client c = clients.get(cID);

        if(c.getAppointments().size() != 0){
            System.out.println("-> The client has the following appointments:");
            c.showAppointments();

            System.out.print("-> Which one do you want to delete? ");
            int option = in.nextInt()-1;
            in.nextLine();

            c.removeAppointment(option);
        }
        else{
            System.out.println("!This client has no appointments!");
        }
        audit.csvWriter("removeAppointment");
    }

    public void editAppointment(){
        Scanner in = new Scanner(System.in);

        System.out.println("=> Edit An Appointment Of A Client:");
        System.out.print("-> The ID of the client: ");
        int cID = in.nextInt();

        Client c = clients.get(cID);

        if(c.getAppointments().size() != 0){
            System.out.println("-> The client has the following appointments:");
            c.showAppointments();

            System.out.print("-> Which appointment do you want to edit? ");
            int option = in.nextInt()-1;
            in.nextLine();

            System.out.print("New time/'n' form 'null': ");
            String time = in.nextLine();

            System.out.print("New day/'n' form 'null': ");
            String day = in.nextLine();

            System.out.print("New month/'n' form 'null': ");
            String month = in.nextLine();

            c.editAppointment(option, time, day, month);
        }
        else{
            System.out.println("!This client has no appointments!");
        }
        audit.csvWriter("editAppointment");
    }

    public void removeClient(){
        Scanner in = new Scanner(System.in);

        System.out.println("=> Delete A Client:");
        System.out.print("-> The ID of the client: ");
        int cID = in.nextInt();

        clients.remove(cID);

        audit.csvWriter("removeClient");
    }

    public void removeDoctor(){
        Scanner in = new Scanner(System.in);

        System.out.println("=> Delete A Doctor:");
        System.out.print("-> The ID of the Doctor: ");
        int dID = in.nextInt();

        for(int i = 0; i<doctors.size(); i++){
            if(dID == doctors.get(i).getDoctorID()){
                doctors.remove(i);
            }
        }
        audit.csvWriter("removeDoctor");
    }


    public Map<Integer, Client> getClients() {
        return clients;
    }

    public void setClients(Map<Integer, Client> clients) {
        this.clients = clients;
    }

    public ArrayList<Doctor> getDoctors () {
        return doctors;
    }

    public void setDoctors (ArrayList < Doctor > doctors) {
        this.doctors = doctors;
    }

}
