package Data;

import Business.EKGObserver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledForJreRange;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArduinoTest  implements EKGObserver {
    Arduino arduino = new Arduino("COM5");

    List<EkgData> data = new ArrayList<>();
    @Test
    public void test(){
        arduino.setObserver(this);
        arduino.record();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(data.size());
        assertTrue(data.size() > 1);
        //assertTrue(data.get(10).getVoltage() > 0);
    }

    @Override
    public void handle(EkgData ekgData) {
        this.data.add(ekgData);
    }
}
