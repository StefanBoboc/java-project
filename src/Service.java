import javax.print.Doc;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Service {
    ArrayList<Client> clients;
    ArrayList<Doctor> doctors;

    public Service() {
        this.clients = new ArrayList<>();
        this.doctors = new ArrayList<>();
    }

    public void addClient() {
        Scanner in = new Scanner(System.in);

        System.out.println("Clients Data Entry");
        System.out.println("------------------");
        System.out.print("Number of clients to be added: ");
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
                clients.add(c);
            } else {
                while ((phoneNumber.equals("n") != true) && (matchFound != true)) {
                    System.out.println("You must enter a valid phone number. If you do not want a phone number enter 'n'!");
                    System.out.print("Phone Number Client" + i + ": ");
                    phoneNumber = in.nextLine();
                    matcher = pattern.matcher(phoneNumber);
                    matchFound = matcher.find();

                    if (phoneNumber.equals("n") == true) {
                        Client c = new Client(lastName, firstName, dateOfBirth, emailAddress);
                        clients.add(c);
                        break;
                    } else if (matchFound == true) {
                        Client c = new Client(lastName, firstName, dateOfBirth, emailAddress, phoneNumber);
                        clients.add(c);
                        break;
                    }
                }

            }
//            System.out.println(lastName+firstName+dateOfBirth+emailAddress+phoneNumber);
        }

    }

    public void addDoctor() {
        Scanner in = new Scanner(System.in);

        System.out.println("Doctors Data Entry");
        System.out.println("------------------");
        System.out.print("Number of doctors to be added: ");

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

            Doctor d = new Doctor(lastName, firstName, dateOfBirth, emailAddress, yearsOfExperience);
            doctors.add(d);
        }
    }
        public ArrayList<Client> getClients () {
            return clients;
        }

        public void setClients (ArrayList < Client > clients) {
            this.clients = clients;
        }

        public ArrayList<Doctor> getDoctors () {
            return doctors;
        }

        public void setDoctors (ArrayList < Doctor > doctors) {
            this.doctors = doctors;
        }
}
