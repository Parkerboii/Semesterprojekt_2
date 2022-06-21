package Data;
import java.io.IOException;
import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.io.*;

import Business.EKGObserver;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import static java.lang.Integer.parseInt;

/*@author  Marcus Andersen, 215846
 * @version 1.0
 * @since   2022-06-23*/

public class Arduino implements Simulator {
    private EKGObserver observer;
    private SerialPort sp = null;
    private BufferedReader in = null;

    public Arduino(String portName) {
        sp = SerialPort.getCommPort(portName);
        sp.setComPortParameters(38400, 8, 1, 0);//Set params.
        in = new BufferedReader(new InputStreamReader(sp.getInputStream()));
    }
    @Override
    public void record() {
        sp.openPort();

        System.out.println("PORT OPEN");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if (observer != null) {
                            if (sp.bytesAvailable() > 0) {
                                var input = in.readLine();
                                int ekgData = 0;
                                if (input.length() > 0) {
                                    ekgData = parseInt(input);
                                }
                                observer.handle(new EkgDTO(ekgData, new Timestamp(System.currentTimeMillis())));
                            }
                        }
                        try {
                            Thread.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public void setObserver(EKGObserver observer) {
        this.observer = observer;
    }

    public void close() {
        sp.closePort();
    }
}
