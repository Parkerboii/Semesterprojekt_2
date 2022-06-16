package Data;
import java.io.IOException;
import java.sql.Timestamp;
import java.io.*;

import Business.EKGObserver;
import com.fazecast.jSerialComm.SerialPort;

public class Arduino /*implements Simulator*/ {
    private EKGObserver observer;
            //TODO: port skal virke
        private SerialPort sp = null;
        private PrintWriter out = null;
        private BufferedReader in = null;

        public Arduino(String portName) {

            sp = SerialPort.getCommPort(portName);
            try {
                sp.openPort();//Open serial port
                sp.setComPortParameters(9600, 8, 1, 0);//Set params.
                sp.setFlowControl(SerialPort.FLOW_CONTROL_DISABLED);
                in = new BufferedReader(new InputStreamReader(sp.getInputStream()));
                out = new PrintWriter(sp.getOutputStream(),true);
                Thread.sleep(2000);

            } catch (InterruptedException ex) {
                System.out.println("Wait Interrupted: " + ex);
            }

        }

        public void send(String meddelse) {
            out.println(meddelse);
            out.flush();
        }

        public String receive() {
            String result = null;
            try {
                while (sp.bytesAvailable() < 1) {
                    java.util.concurrent.TimeUnit.MILLISECONDS.sleep(500);
                    if (observer != null) {
                        observer.handle(new EkgDTO(Math.random(),  new Timestamp(System.currentTimeMillis())));

                    }
                }
                result = in.readLine();
            } catch (InterruptedException e) {
                System.out.println("Wait Interrupted: " + e);
            } catch (IOException ex) {
                System.out.println("Serial Port Exception: " + ex);

            }
            return result;
        }

      /*  public String comm(String message) {
            send(message);
            return receive();
        }*/

        public void close() {
            try {

                out.close();
                in.close();
                sp.closePort();
            }
            catch (IOException ex) {
                System.out.println("Serial Port Exception: " + ex);
            }
        }
        public static void main(String[] args) {
            Arduino hardware = new Arduino("COM5");
            hardware.receive();
           // hardware.send("a50");
            System.out.println("Result: "+hardware.receive());
        }
/*
    @Override
    public void record() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true) {
                        Thread.sleep(500);
                        if (observer != null) {
                            observer.handle(new EkgDTO(Math.random(),  new Timestamp(System.currentTimeMillis())));

                        }

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    @Override
    public void setObserver(EKGObserver observer) {
        this.observer = observer; }*/
    }
