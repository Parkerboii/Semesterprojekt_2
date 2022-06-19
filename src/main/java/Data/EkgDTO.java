package Data;

import Data.EkgData;

import java.sql.Timestamp;

public class EkgDTO implements EkgData {
    private Timestamp time;
    private double voltage;
    private String id;
    private String firstName;
    private String lastName;

    public EkgDTO(double voltage, Timestamp time){
        this.voltage = voltage;
        this.time = time;
    }

    public EkgDTO() {
    }

    @Override
    public double getVoltage() {
        return voltage;
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
    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    @Override
    public Timestamp getTime() { return time; }

    @Override
    public void setTime(Timestamp time) { this.time = time; }

}
