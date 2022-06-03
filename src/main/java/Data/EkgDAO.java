package Data;

import Data.EkgDTO;
import Data.EkgData;

import java.sql.Timestamp;
import java.util.List;

public interface EkgDAO {
    void save(EkgData ekgDTO);

    List<EkgDTO> load(Timestamp time);
}
