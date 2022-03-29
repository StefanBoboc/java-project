public class Person {
    private String lastName;
    private String firstName;
    private String dateOfBirth;
    private String emailAddress;

    public Person(String lastName, String firstName, String dateOfBirth, String emailAddress) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = emailAddress;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setBirthOfDate(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String showPerson(){
        return "Lastname: "+this.lastName+"; Firstname: "+firstName+"; Date of birth: "+dateOfBirth+"; Email address "+emailAddress;
    }

}
