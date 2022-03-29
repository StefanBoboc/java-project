public class Client extends Person {
    private int clientID;
    private static int noOfClients = 0;

    private String phoneNumber;

    public Client(String lastName, String firstName, String dateOfBirth, String emailAddress) {
        super(lastName, firstName, dateOfBirth, emailAddress);
        noOfClients += 1;
        clientID = noOfClients;
    }

    public Client(String lastName, String firstName, String dateOfBirth, String emailAddress, String phoneNumber) {
        super(lastName, firstName, dateOfBirth, emailAddress);
        this.phoneNumber = phoneNumber;
        noOfClients += 1;
        clientID = noOfClients;
    }

    //    public Client(String lastName, String firstName, String birthOfDate, String emailAddress) {
//        super(lastName, firstName, birthOfDate, emailAddress);
//        noOfClients += 1;
//        clientID = noOfClients;
//    }
//
//    public Client(String lastName, String firstName, String birthOfDate, String emailAddress, String phoneNumber) {
//        super(lastName, firstName, birthOfDate, emailAddress);
//        this.phoneNumber = phoneNumber;
//        noOfClients += 1;
//        clientID = noOfClients;
//    }


    @Override
    public String showPerson() {
        return "c-ID: "+this.clientID+"; "+super.showPerson()+"; Phone number: "+this.phoneNumber;
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
}
