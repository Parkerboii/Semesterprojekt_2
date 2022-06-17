/*package Data;

import Business.EkgController;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

public class TestArduino implements Simulator {
    EkgController recorder = new Arduino();
    List<EkgData> dataList = new LinkedList<>();

    @Test
    public void testRecording() {
        recorder.setObserver(this);
        recorder.record();

    try {
        Thread.sleep(1000);
    } catch(InterruptedException e) {
        e.printStackTrace();
    }
    Assertions.assertTrue(dataList.size()>1);
}


    @Override
    public void handle(EkgData ekgData) {
        this.dataList.add(ekgData);
    }
}
*/
