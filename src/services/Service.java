package services;

import objects.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class Service {
    Map<Integer, Client> clients;
    ArrayList<Doctor> doctors;
    Read reading = Read.getInstance();
    Write writing = Write.getInstance();
    DataBaseConnnection dataBase = DataBaseConnnection.getInstance();

    public Service() {
        this.clients = new HashMap<>();
        this.doctors = new ArrayList<>();
    }

    public void addCSVData(){
        addClientsCSV();
        addDoctorCSV();
        makeAppointmentAnalysesCSV();
        makeAppointmentGynecologyCSV();
    }

    // loading the date from JDBC
    public void addJDBCData(){
        try(Statement statement = dataBase.getConnection().createStatement()){
            String query = "select * from clients";
            ResultSet rs = statement.executeQuery(query);

            while(rs.next()) {
                Client client = new Client(rs.getString("last_name"), rs.getString("first_name"), rs.getString("birth_of_date"), rs.getString("email_address"), rs.getString("phone_number"));

                clients.put(client.getClientID(), client);
            }

            query = "select * from doctors";
            rs = statement.executeQuery(query);

            while(rs.next()) {
                Doctor doctor = new Doctor(rs.getString("last_name"), rs.getString("first_name"), rs.getString("birth_of_date"), rs.getString("email_address"), rs.getInt("years_of_experience"), rs.getString("type"));

                doctors.add(doctor);
            }

            query = "select * from analyses";
            rs = statement.executeQuery(query);

            while(rs.next()) {
                Client c;
                c = clients.get(rs.getInt("id_client"));

                Analyses a = new Analyses(rs.getString("time"), rs.getString("day"), rs.getString("month"), rs.getString("option_of_analyses"));
                c.addAppointment(a);
            }

            query = "select * from gynecology";
            rs = statement.executeQuery(query);

            while(rs.next()) {
                Client c;
                c = clients.get(rs.getInt("id_client"));

                Gynecology g = new Gynecology(rs.getString("time"), rs.getString("day"), rs.getString("month"), rs.getString("symptoms"));
                c.addAppointment(g);
            }

            query = "select * from pediatrics";
            rs = statement.executeQuery(query);

            while(rs.next()) {
                Client c;
                c = clients.get(rs.getInt("id_client"));

                Pediatrics p = new Pediatrics(rs.getString("time"), rs.getString("day"), rs.getString("month"), rs.getString("child_full_name"), rs.getInt("child_age"), rs.getString("child_sex"));
                c.addAppointment(p);
            }

            query = "select * from obstetrics";
            rs = statement.executeQuery(query);

            while(rs.next()) {
                Client c;
                c = clients.get(rs.getInt("id_client"));

                Obstetrics o = new Obstetrics(rs.getString("time"), rs.getString("day"), rs.getString("month"), rs.getInt("month_of_pregnancy"), rs.getInt("pregnancy_number"), rs.getString("abortions"));
                c.addAppointment(o);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Client insertion into JDBC
    public void addClientsJDBC(Client c){
        try {
            String query = "insert into clients values(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = dataBase.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, c.getClientID());
            preparedStatement.setString(2, c.getLastName());
            preparedStatement.setString(3, c.getFirstName());
            preparedStatement.setString(4, c.getDateOfBirth());
            preparedStatement.setString(5, c.getEmailAddress());
            preparedStatement.setString(6, c.getPhoneNumber());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // reading Clients data from CSV files
    public void addClientsCSV(){
        String fileName = "clients.csv";
        ArrayList<String> lines = reading.csvReader(fileName);
        int noLines = lines.size();

        // line reading and removing commas
        for(int i = 1; i < noLines; i++){
            String []line = lines.get(i).split(",");

            Client c = new Client(line[0], line[1], line[2], line[3], line[4]);
            clients.put(c.getClientID(), c);
        }

        writing.csvWriter("addClientsCSV");
    }

    // reading Client data
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

            // phone number must be in a format
            Pattern pattern = Pattern.compile("^\\d{10}$");
            Matcher matcher = pattern.matcher(phoneNumber);
            boolean matchFound = matcher.find();

            if ((phoneNumber.equals("n") == true) || matchFound == true) {
                Client c = new Client(lastName, firstName, dateOfBirth, emailAddress, phoneNumber);
                clients.put(c.getClientID(), c);
                addClientsJDBC(c);

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
                        addClientsJDBC(c);
                        break;
                    } else if (matchFound == true) {
                        Client c = new Client(lastName, firstName, dateOfBirth, emailAddress, phoneNumber);
                        clients.put(c.getClientID(), c);
                        addClientsJDBC(c);
                        break;
                    }
                }

            }
        }

        writing.csvWriter("addClient");
    }

    // display Client
    public void showClient() {
        System.out.println("=> Clients: ");
        for (Integer i : clients.keySet()){
            System.out.println(clients.get(i).showPerson());
        }

        writing.csvWriter("showClient");
    }

    // Doctors insertion into JDBC
    public void addDoctorsJDBC(Doctor d){
        try {
            String query = "insert into doctors values(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = dataBase.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, d.getDoctorID());
            preparedStatement.setString(2, d.getLastName());
            preparedStatement.setString(3, d.getFirstName());
            preparedStatement.setString(4, d.getDateOfBirth());
            preparedStatement.setString(5, d.getEmailAddress());
            preparedStatement.setInt(6, d.getYearsOfExperience());
            preparedStatement.setString(7, d.getType());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // reading Doctor data from CSV files
    public void addDoctorCSV(){
        String fileName = "doctors.csv";
        ArrayList<String> lines = reading.csvReader(fileName);
        int noLines = lines.size();

        // line reading and removing commas
        for(int i = 1; i < noLines; i++){
            String []line = lines.get(i).split(",");

            Doctor d = new Doctor(line[0], line[1], line[2], line[3], parseInt(line[4]), line[5]);
            doctors.add(d);
        }

        writing.csvWriter("addDoctorCSV");
    }

    // reading Doctor data
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

            addDoctorsJDBC(d);
        }

        writing.csvWriter("addDoctor");
    }

    // display Doctors
    public void showDoctors() {
        System.out.println("=> Doctors: ");
        for(int i = 0; i < doctors.size(); i++) {
            System.out.println(doctors.get(i).showPerson());
        }

        writing.csvWriter("showDoctor");
    }

    // reading Analyses Appointment data from CSV files
    public void makeAppointmentAnalysesCSV() {
        String fileName = "analyses.csv";
        ArrayList<String> lines = reading.csvReader(fileName);
        int noLines = lines.size();

        // line reading and removing commas
        for(int i = 1; i < noLines; i++){
            String []line = lines.get(i).split(",");

            Client c;
            c = clients.get(parseInt(line[0]));

            Analyses a = new Analyses(line[1], line[2], line[3], line[4]);
            c.addAppointment(a);
        }

        writing.csvWriter("makeAppointmentAnalysesCVS");
    }

    // reading Gynecology Appointment data from CSV files
    public void makeAppointmentGynecologyCSV() {
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

        writing.csvWriter("makeAppointmentGynecologyCSV");
    }

    // reading Appointment data
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

        System.out.print("Month of the appointment: ");
        String month = in.nextLine();

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

            // reading Pediatrics data
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

                Pediatrics p = (Pediatrics) aux;

                // Pediatrics insertion into JDBC
                try {
                    String query = "insert into pediatrics values(?,?,?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = dataBase.getConnection().prepareStatement(query);
                    preparedStatement.setInt(1, p.getPediatricsID());
                    preparedStatement.setInt(2, cID);
                    preparedStatement.setString(3, time);
                    preparedStatement.setString(4, day);
                    preparedStatement.setString(5, month);
                    preparedStatement.setString(6, childFullName);
                    preparedStatement.setInt(7, childAge);
                    preparedStatement.setString(8, childSex);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // reading Gynecology data
            else if(typeExamination.equals("g") == true){
                System.out.println("# Gynecology: ");

                System.out.print("Briefly introduce the symptoms: ");
                String symptoms = in.nextLine();

                Appointment aux = new Gynecology(time, day, month, symptoms);
                c.addAppointment(aux);

                Gynecology g = (Gynecology) aux;

                // Gynecology insertion into JDBC
                try {
                    String query = "insert into gynecology values(?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = dataBase.getConnection().prepareStatement(query);
                    preparedStatement.setInt(1, g.getGynecologyID());
                    preparedStatement.setInt(2, cID);
                    preparedStatement.setString(3, time);
                    preparedStatement.setString(4, day);
                    preparedStatement.setString(5, month);
                    preparedStatement.setString(6, symptoms);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // reading Obstetrics data
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

                Obstetrics o = (Obstetrics) aux;

                // Obstetrics insertion into JDBC
                try {
                    String query = "insert into obstetrics values(?,?,?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = dataBase.getConnection().prepareStatement(query);
                    preparedStatement.setInt(1, o.getObstetricsID());
                    preparedStatement.setInt(2, cID);
                    preparedStatement.setString(3, time);
                    preparedStatement.setString(4, day);
                    preparedStatement.setString(5, month);
                    preparedStatement.setInt(6, monthOfPregnancy);
                    preparedStatement.setInt(7, pregnancyNumber);
                    preparedStatement.setString(8, abortions);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // reading Analyses data
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

            Analyses a = (Analyses) aux;

            // Analyses insertion into JDBC
            try {
                String query = "insert into analyses values(?,?,?,?,?,?)";
                PreparedStatement preparedStatement = dataBase.getConnection().prepareStatement(query);
                preparedStatement.setInt(1, a.getAnalysesID());
                preparedStatement.setInt(2, cID);
                preparedStatement.setString(3, time);
                preparedStatement.setString(4, day);
                preparedStatement.setString(5, month);
                preparedStatement.setString(6, optionOfAnalyses);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

        writing.csvWriter("makeAppointment");
    }

    // display all the Appointments of a given Client
    public void seeAppointment(){
        Scanner in = new Scanner(System.in);

        System.out.println("=> Show Appointments Of A Client:");
        System.out.print("-> The ID of the client: ");
        int cID = in.nextInt();

        Client c = clients.get(cID);

        c.showAppointments();

        writing.csvWriter("seeAppointment");
    }

    // delete an Appointment of a given Client
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
            int appointmentID = in.nextInt()-1;
            in.nextLine();

            // getting the Appointment ID and the Appointment type before deleting them
            ArrayList<Appointment> app = c.getAppointments();
            String typeApp = app.get(appointmentID).showAppointment().substring(0, 3);
            Appointment aux = app.get(appointmentID);

            // deleting the Appointment
            c.removeAppointment(appointmentID);

            if(typeApp.equals("ana")){

                // casting the Appointment into Analyses to obtain the Analyses ID
                Analyses auxx = (Analyses) aux;
                Integer typeAppID = auxx.getAnalysesID();

                // deleting Analyses from JDBC
                try {
                    String query = "delete from analyses where id_analyses = "+typeAppID+";";
                    PreparedStatement preparedStatement = dataBase.getConnection().prepareStatement(query);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else if(typeApp.equals("gyn")) {

                // casting the Appointment into Gynecology to obtain the Gynecology ID
                Gynecology auxx = (Gynecology) aux;
                Integer typeAppID = auxx.getGynecologyID();

                // deleting Gynecology from JDBC
                try {
                    String query = "delete from gynecology where id_gynecology = " + typeAppID + ";";
                    PreparedStatement preparedStatement = dataBase.getConnection().prepareStatement(query);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else if(typeApp.equals("obs")) {

                // casting the Appointment into Obstetrics to obtain the Obstetrics ID
                Obstetrics auxx = (Obstetrics) aux;
                Integer typeAppID = auxx.getObstetricsID();

                // deleting Obstetrics from JDBC
                try {
                    String query = "delete from obstetrics where id_obstetrics = " + typeAppID + ";";
                    PreparedStatement preparedStatement = dataBase.getConnection().prepareStatement(query);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else if(typeApp.equals("ped")) {

                // casting the Appointment into Pediatrics to obtain the Pediatrics ID
                Pediatrics auxx = (Pediatrics) aux;
                Integer typeAppID = auxx.getPediatricsID();

                // deleting Pediatrics from JDBC
                try {
                    String query = "delete from pediatrics where id_pediatrics = " + typeAppID + ";";
                    PreparedStatement preparedStatement = dataBase.getConnection().prepareStatement(query);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("Error...");
            }
        }
        else{
            System.out.println("!This client has no appointments!");
        }

        writing.csvWriter("removeAppointment");
    }

    // edit an Appointment of a given Client
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
            int appointmentID = in.nextInt()-1;
            in.nextLine();

            System.out.print("New time or press 'n' to skip: ");
            String time = in.nextLine();

            System.out.print("New day or press 'n' to skip: ");
            String day = in.nextLine();

            System.out.print("New month or press 'n' to skip: ");
            String month = in.nextLine();

            // getting the Appointment ID and the Appointment type
            ArrayList<Appointment> app = c.getAppointments();
            Appointment a = app.get(appointmentID);
            String typeApp = app.get(appointmentID).showAppointment().substring(0, 3);
            Appointment aux = app.get(appointmentID);

            c.editAppointment(appointmentID, time, day, month);

            // obtaining time, day and month changes
            time = a.getTime();
            day = a.getDay();
            month = a.getMonth();

            // edit Analyses
            if(typeApp.equals("ana")){

                // casting the Appointment into Analyses to obtain the Analyses ID
                Analyses auxx = (Analyses) aux;
                Integer typeAppID = auxx.getAnalysesID();

                // update Analyses into JDBC
                try {
                    String query = "update analyses set time = '"+time+"', day = '"+day+"', month = '"+month+"' where id_analyses = '"+ typeAppID + "';";
                    PreparedStatement preparedStatement = dataBase.getConnection().prepareStatement(query);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // edit Gynecology
            else if(typeApp.equals("gyn")){

                // casting the Appointment into Gynecology to obtain the Gynecology ID
                Gynecology auxx = (Gynecology) aux;
                Integer typeAppID = auxx.getGynecologyID();

                // update Gynecology into JDBC
                try {
                    String query = "update gynecology set time = '"+time+"', day = '"+day+"', month = '"+month+"' where id_gynecology = '"+ typeAppID + "';";
                    PreparedStatement preparedStatement = dataBase.getConnection().prepareStatement(query);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // edit Obstetrics
            else if(typeApp.equals("obs")){

                // casting the Appointment into Obstetrics to obtain the Obstetrics ID
                Obstetrics auxx = (Obstetrics) aux;
                Integer typeAppID = auxx.getObstetricsID();

                // update Obstetrics into JDBC
                try {
                    String query = "update obstetrics set time = '"+time+"', day = '"+day+"', month = '"+month+"' where id_obstetrics = '"+ typeAppID + "';";
                    PreparedStatement preparedStatement = dataBase.getConnection().prepareStatement(query);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // edit Pediatrics
            else if(typeApp.equals("ped")){

                // casting the Appointment into Pediatrics to obtain the Pediatrics ID
                Pediatrics auxx = (Pediatrics) aux;
                Integer typeAppID = auxx.getPediatricsID();

                // update Pediatrics into JDBC
                try {
                    String query = "update pediatrics set time = '"+time+"', day = '"+day+"', month = '"+month+"' where id_pediatrics = '"+ typeAppID + "';";
                    PreparedStatement preparedStatement = dataBase.getConnection().prepareStatement(query);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("Error...");
            }
        }
        else{
            System.out.println("!This client has no appointments!");
        }

        writing.csvWriter("editAppointment");
    }

    // delete a given Client
    public void removeClient(){
        Scanner in = new Scanner(System.in);

        System.out.println("=> Delete A Client:");
        System.out.print("-> The ID of the client: ");
        int cID = in.nextInt();

        clients.remove(cID);

        // deleting Client from JDBC
        try {
            String query = "delete from clients where id_client = " + cID + ";";
            PreparedStatement preparedStatement = dataBase.getConnection().prepareStatement(query);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        writing.csvWriter("removeClient");
    }

    // delete a given Doctor
    public void removeDoctor(){
        Scanner in = new Scanner(System.in);

        System.out.println("=> Delete A Doctor:");
        System.out.print("-> The ID of the Doctor: ");
        int dID = in.nextInt();

        doctors.remove(dID-1);

        // deleting Doctor from JDBC
        try {
            String query = "delete from doctors where id_doctor = " + dID + ";";
            PreparedStatement preparedStatement = dataBase.getConnection().prepareStatement(query);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        writing.csvWriter("removeDoctor");
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
