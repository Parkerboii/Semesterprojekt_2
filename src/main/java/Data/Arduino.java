package Data;
import java.io.IOException;
import java.sql.Timestamp;
import java.io.*;

import Business.EKGObserver;
import com.fazecast.jSerialComm.SerialPort;
import static java.lang.Integer.parseInt;

public class Arduino implements Simulator {
    private EKGObserver observer;
    private SerialPort sp = null;
    private BufferedReader in = null;

    public Arduino() {

        sp = SerialPort.getCommPort("COM5 (Arduino Uno)");
        sp.openPort();//Open serial port
        sp.setComPortParameters(38400, 8, 1, 0);//Set params.
        sp.setFlowControl(SerialPort.FLOW_CONTROL_DISABLED);
        in = new BufferedReader(new InputStreamReader(sp.getInputStream()));
    }


    @Override
    public void record() {
        sp.openPort();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (observer != null) {
                        if (sp.bytesAvailable() > 0) {
                            var input = in.readLine();
                            int result = Integer.parseInt(input);
                            observer.handle(new EkgDTO(result, new Timestamp(System.currentTimeMillis())));
                            System.out.println("DATA" + result);
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    @Override
    public void setObserver(EKGObserver observer) {
        this.observer = observer;
    }

    public void close() {
        try {
            in.close();
            sp.closePort() ;

        } catch (IOException ex) {
            System.out.println("Serial Port Exception: " + ex);
        }
    }
}