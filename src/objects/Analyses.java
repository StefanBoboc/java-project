package objects;

public class Analyses extends Appointment {
    private String optionOfAnalyses;

    private int analysesID;
    private static int noOfAnalyses = 0;

    public Analyses(String time, String day, String month, String optionOfAnalyses) {
        super(time, day, month);
        this.optionOfAnalyses = optionOfAnalyses;

        noOfAnalyses += 1;
        analysesID = noOfAnalyses;
    }

    @Override
    public String showAppointment() {
        return "ana-ID: "+this.analysesID+"; "+super.showAppointment()+"; Option of analyses: "+this.optionOfAnalyses;
    }

    public int getAnalysesID() {
        return analysesID;
    }

    public void setAnalysesID(int analysesID) {
        this.analysesID = analysesID;
    }

    public static int getNoOfAnalyses() {
        return noOfAnalyses;
    }

    public static void setNoOfAnalyses(int noOfAnalyses) {
        Analyses.noOfAnalyses = noOfAnalyses;
    }

    public String getOptionOfAnalyses() {
        return optionOfAnalyses;
    }

    public void setOptionOfAnalyses(String optionOfAnalyses) {
        this.optionOfAnalyses = optionOfAnalyses;
    }
}
