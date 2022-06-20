/*package Data;

import Business.EKGObserver;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArduinoTest  implements EKGObserver {
    EKGSimulator simulator = new EKGSimulator();

    List<EkgData> data = new ArrayList<>();

    public void test(){
        simulator.setObserver(this);
        simulator.record();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        Assertions.assertTrue(data.size() > 1);
    }


    @Override
    public void handle(EkgData ekgData) {
        this.data.add(ekgData);
    }
}
*/
