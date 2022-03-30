public class Obstetrics extends Appointment{
    private int monthOfPregnancy;
    private int pregnancyNumber;
    private String abortions;

    private int obstetricsID;
    private static int noOfObstetrics = 0;

    public Obstetrics(String time, String day, String month, int monthOfPregnancy, int pregnancyNumber, String abortions) {
        super(time, day, month);
        this.monthOfPregnancy = monthOfPregnancy;
        this.pregnancyNumber = pregnancyNumber;
        this.abortions = abortions;

        noOfObstetrics += 1;
        obstetricsID = noOfObstetrics;
    }

    @Override
    public String showAppointment() {
        return "obs-ID: "+obstetricsID+"; "+super.showAppointment()+"; Month of pregnancy: "+monthOfPregnancy+"; Pregnancy number:"+pregnancyNumber+"; Abortions? "+abortions;
    }

    public int getObstetricsID() {
        return obstetricsID;
    }

    public void setObstetricsID(int obstetricsID) {
        this.obstetricsID = obstetricsID;
    }

    public static int getNoOfObstetrics() {
        return noOfObstetrics;
    }

    public static void setNoOfObstetrics(int noOfObstetrics) {
        Obstetrics.noOfObstetrics = noOfObstetrics;
    }

    public int getMonthOfPregnancy() {
        return monthOfPregnancy;
    }

    public void setMonthOfPregnancy(int monthOfPregnancy) {
        this.monthOfPregnancy = monthOfPregnancy;
    }

    public int getPregnancyNumber() {
        return pregnancyNumber;
    }

    public void setPregnancyNumber(int pregnancyNumber) {
        this.pregnancyNumber = pregnancyNumber;
    }

    public String getAbortions() {
        return abortions;
    }

    public void setAbortions(String abortions) {
        this.abortions = abortions;
    }
}
