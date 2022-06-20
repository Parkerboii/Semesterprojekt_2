package Data;

public class PatientDTO implements PatientData {
    private String CPR;
    private String lastName;
    private String firstName;

    public void setCPR(String cpr) {
        this.CPR = cpr;
    }

    public String getCPR() {
        return CPR;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }
}
