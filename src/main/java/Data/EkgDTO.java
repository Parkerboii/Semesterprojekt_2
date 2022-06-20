package Data;

import Data.EkgData;

import java.sql.Timestamp;

public class EkgDTO implements EkgData {
    private Timestamp time;
    private double voltage;

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
    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    @Override
    public Timestamp getTime() { return time; }

    @Override
    public void setTime(Timestamp time) { this.time = time; }

}
