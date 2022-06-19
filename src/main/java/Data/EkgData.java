package Data;

import java.sql.Timestamp;

public interface EkgData {

    void setVoltage(double voltage);
    double getVoltage();
    void setID(String id);
    String getID();

    void setTime(Timestamp time);

    Timestamp getTime();


}
