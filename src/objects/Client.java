package objects;

import java.util.ArrayList;

public class Client extends Person {
    private int clientID;
    private static int noOfClients = 0;

    private String phoneNumber;
    private ArrayList<Appointment> appointments;

    public Client(String lastName, String firstName, String dateOfBirth, String emailAddress) {
        super(lastName, firstName, dateOfBirth, emailAddress);

        this.appointments = new ArrayList<>();

        noOfClients += 1;
        clientID = noOfClients;
    }

    public Client(String lastName, String firstName, String dateOfBirth, String emailAddress, String phoneNumber) {
        super(lastName, firstName, dateOfBirth, emailAddress);
        this.phoneNumber = phoneNumber;

        this.appointments = new ArrayList<>();

        noOfClients += 1;
        clientID = noOfClients;
    }

    @Override
    public String showPerson() {
        return "c-ID: "+this.clientID+"; "+super.showPerson()+"; Phone number: "+this.phoneNumber;
    }

    public void addAppointment(Appointment appointment){
        this.appointments.add(appointment);
    }

    public void showAppointments(){
        if(this.appointments.size() != 0) {
            for (int i = 0; i < this.appointments.size(); i++) {
                System.out.println((i+1) + ". " + appointments.get(i).showAppointment());
                // appointments.get(i).getAppointmentID()
            }
        }
        else{
            System.out.println("!This client has no appointments!");
        }
    }

    public void editAppointment(int option, String time, String day, String month){
        Appointment a = appointments.get(option);
        if(time.equals("n") != true){
            a.setTime(time);
        }
        if(day.equals("n") != true){
            a.setDay(day);
        }
        if(month.equals("n") != true){
            a.setMonth(month);
        }
    }

    public void removeAppointment(int option){
        String message = appointments.get(option).showAppointment();

        this.appointments.remove(option);

        System.out.println("-> Appointment: '"+message+"' was successfully deleted!");
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public static int getNoOfClients() {
        return noOfClients;
    }

    public static void setNoOfClients(int noOfClients) {
        Client.noOfClients = noOfClients;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }
}
