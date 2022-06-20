package Business;
import Data.EkgData;

import java.sql.Timestamp;

public interface EKGObserver{
    void handle(EkgData ekgData);
}
