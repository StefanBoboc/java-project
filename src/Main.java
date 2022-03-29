import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Person person1 = new Person("Popescu", "Alex", "12.12.1989", "popescualex@email.com");
//        Person person2 = new Person("Musca", "Bogdan", "12.12.1989", "muscabogdan@email.com", "0741234567");

//        System.out.println(person1.getPhoneNumber());
//        System.out.println(person2.getPhoneNumber());

//        Client client1 = new Client("Stana", "Teo", "12.12.1212", "stana.teo@email.com");
//        Client client2 = new Client("Bajan", "Ioana", "12.12.1212", "bajan.ioana@email.com");
//        System.out.println(client1.getClientID());
//        System.out.println(client2.getClientID());

//        Service service = new Service();
//        service.addClient();
//        System.out.println(service.getClients());
//        service.addClient();
//        System.out.println(service.getClients());

//        ==================================================

        Service service = new Service();

        Scanner in = new Scanner(System.in);

        while(true) {
            System.out.println("1. Add Clients\n2. Add Doctors\n0. EXIT");
            System.out.print("-> ");

            int command = in.nextInt();

            if (command == 1){
                service.addClient();
            }
            else if (command == 2){
                service.addDoctor();
            }
            else if (command == 0) {
                break;
            }
            else {
                System.out.println("Invalid command! Enter one command from 0 to 2");
            }
            System.out.println("====================");

        }

//        __TESTS__

//        ArrayList<Client> aux = service.getClients();
//        System.out.println(aux.get(1).getLastName());
//        System.out.println(aux.get(2).getPhoneNumber());
//        System.out.println(aux.get(1).getClientID());
//        System.out.println(aux.get(1));
//        System.out.println(aux.get(2));

        ArrayList<Client> aux = service.getClients();
        ArrayList<Doctor> aux2 = service.getDoctors();

        System.out.println(aux.get(1).getPhoneNumber());
        System.out.println(aux2.get(3).getYearsOfExperience());





    }
}
