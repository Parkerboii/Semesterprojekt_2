package Data;

public class PatientDTO implements PatientData{
    private String firstName;
    private String lastName;
    private String id;

    @Override
    public void setFirstName(String first) {
        this.firstName = firstName;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setLastName(String last) {
        this.lastName = lastName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getID() {
        return id;
    }

}
