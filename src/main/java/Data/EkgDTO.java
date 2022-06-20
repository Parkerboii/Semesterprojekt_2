package Data;

import Data.EkgData;

import java.sql.Timestamp;

public class EkgDTO implements EkgData {
    private Timestamp time;
    private int voltage;
    private String id;
    private String firstName;
    private String lastName;

    public EkgDTO(int voltage, Timestamp time){
        this.voltage = voltage;
        this.time = time;
    }

    public EkgDTO() {
    }

    @Override
    public int getVoltage() {
        return voltage;
    }

    @Override
    public String toString() {
        return "EkgDTO{" +
                "time=" + time +
                ", voltage=" + voltage +
                ", id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public void setID(String id) {
        this.id=id;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    @Override
    public Timestamp getTime() { return time; }

    @Override
    public void setTime(Timestamp time) { this.time = time; }

}
