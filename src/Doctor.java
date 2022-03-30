public class Doctor extends Person {
    private int yearsOfExperience;
    private String type;
    private int doctorID;
    private static int noOfDoctors = 0;

    public Doctor(String lastName, String firstName, String birthOfDate, String emailAddress, int yearsOfExperience, String type) {
        super(lastName, firstName, birthOfDate, emailAddress);
        this.yearsOfExperience = yearsOfExperience;
        this.type = type;
        noOfDoctors += 1;
        doctorID = noOfDoctors;
    }

    @Override
    public String showPerson() {
        return "d-ID: "+this.doctorID+"; "+super.showPerson()+"; Experience: "+this.yearsOfExperience+" years"+"; Type: "+this.type;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public static int getNoOfDoctors() {
        return noOfDoctors;
    }

    public static void setNoOfDoctors(int noOfDoctors) {
        Doctor.noOfDoctors = noOfDoctors;
    }
}
