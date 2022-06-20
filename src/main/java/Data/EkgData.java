package Data;

import java.sql.Timestamp;

public interface EkgData {
    void setVoltage(int voltage);
    int getVoltage();

    void setTime(Timestamp time);

    Timestamp getTime();


}
