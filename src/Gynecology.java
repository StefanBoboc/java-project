public class Gynecology extends Appointment{
    private String symptoms;

    private int gynecologyID;
    private static int noOfGynecology = 0;

    public Gynecology(String time, String day, String month, String symptoms) {
        super(time, day, month);
        this.symptoms = symptoms;

        noOfGynecology += 1;
        gynecologyID = noOfGynecology;
    }

    @Override
    public String showAppointment() {
        return "gyn-ID: "+this.gynecologyID+"; "+super.showAppointment()+"; Simptome: "+this.symptoms;
    }

    public int getGynecologyID() {
        return gynecologyID;
    }

    public void setGynecologyID(int gynecologyID) {
        this.gynecologyID = gynecologyID;
    }

    public static int getNoOfGynecology() {
        return noOfGynecology;
    }

    public static void setNoOfGynecology(int noOfGynecology) {
        Gynecology.noOfGynecology = noOfGynecology;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }
}
