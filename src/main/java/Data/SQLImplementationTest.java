package Data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SQLImplementationTest {

    @Test
    void saveAndLoad() {
        SQLImplementation impl = new SQLImplementation();
        EkgData data = new EkgData() {
            @Override
            public void setVoltage(int voltage) {

            }

            @Override
            public int getVoltage() {
                return 0;
            }

            @Override
            public void setID(String id) {

            }

            @Override
            public String getID() {
                return null;
            }

            @Override
            public void setTime(Timestamp time) {

            }

            @Override
            public Timestamp getTime() {
                return null;
            }
        };
        List<EkgDTO> data1 = new ArrayList<>();
        data.setID("0204065010");
        data.setVoltage(5);
        Timestamp ts = new Timestamp(1313045568);
        data.setTime(ts);
        impl.save(data);

        System.out.printf(impl.load(ts).toString());

    }

    @Test
    void load() {
    }
}