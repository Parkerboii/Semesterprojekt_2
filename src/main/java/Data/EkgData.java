package Data;

import java.sql.Timestamp;

public interface EkgData {

    void setVoltage(int voltage);
    int getVoltage();
    void setID(String id);
    String getID();

    void setTime(Timestamp time);

    Timestamp getTime();


}
