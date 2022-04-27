package objects;

public class Pediatrics extends Appointment {
    private String childFullName;
    private int childAge;
    private String childSex;

    private int pediatricsID;
    private static int noOfPediatrics = 0;

    public Pediatrics(String time, String day, String month, String childFullName, int childAge, String childSex) {
        super(time, day, month);
        this.childFullName = childFullName;
        this.childAge = childAge;
        this.childSex = childSex;

        noOfPediatrics += 1;
        pediatricsID = noOfPediatrics;
    }

    @Override
    public String showAppointment() {
        return "ped-ID: "+this.pediatricsID+"; "+super.showAppointment()+"; Child name: " +this.childFullName+"; Age: "+this.childAge+"; Sex: "+this.childSex;
    }

    public int getPediatricsID() {
        return pediatricsID;
    }

    public void setPediatricsID(int pediatricsID) {
        this.pediatricsID = pediatricsID;
    }

    public static int getNoOfPediatrics() {
        return noOfPediatrics;
    }

    public static void setNoOfPediatrics(int noOfPediatrics) {
        Pediatrics.noOfPediatrics = noOfPediatrics;
    }

    public String getChildFullName() {
        return childFullName;
    }

    public void setChildFullName(String childFullName) {
        this.childFullName = childFullName;
    }

    public int getChildAge() {
        return childAge;
    }

    public void setChildAge(int childAge) {
        this.childAge = childAge;
    }

    public String getChildSex() {
        return childSex;
    }

    public void setChildSex(String childSex) {
        this.childSex = childSex;
    }
}
