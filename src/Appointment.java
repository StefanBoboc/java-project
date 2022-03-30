public class Appointment {
    private String time;
    private String day;
    private String month;

    private int appointmentID;
    private static int noOfAppointment = 0;

    public Appointment(String time, String day, String month) {
        this.time = time;
        this.day = day;
        this.month = month;

        noOfAppointment += 1;
        appointmentID = noOfAppointment;
    }

    public String showAppointment() {
        return "Time: "+this.time+"; Date (dd/mm): "+this.day+"/"+this.month;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public static int getNoOfAppointment() {
        return noOfAppointment;
    }

    public static void setNoOfAppointment(int noOfAppointment) {
        Appointment.noOfAppointment = noOfAppointment;
    }
}
