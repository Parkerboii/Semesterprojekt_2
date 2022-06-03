package Data.Presentation;

import java.sql.Timestamp;

public interface EkgData {
    void setVoltage(double voltage);
    double getVoltage();

    void setTime(Timestamp time);

    Timestamp getTime();


}
