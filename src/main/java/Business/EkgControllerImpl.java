package Business;
import Data.*;
import com.fazecast.jSerialComm.SerialPort;
import java.util.LinkedList;
import java.util.List;

/*@author  Michael Parker, s204814
 * @version 1.0
 * @since   2022-06-23*/

public class EkgControllerImpl implements EkgController, EKGObserver {
    Simulator ekgDataRecorder = new Arduino("COM5");
    private EKGObserver observer;
    private EkgDAO ekgDAO = new SQLImplementation();

    public LinkedList<EkgData> queue = new LinkedList<>();
    public Object emptyLock = new Object();
    int capacity = 400;

    private String currentCPR;

    @Override
    public void startRecording(){
        ekgDataRecorder.record();
        ekgDataRecorder.setObserver(this);
    }

    @Override
    public void registerObserver(EKGObserver ekgObserver){this.observer = ekgObserver;}

    @Override
    public void setCurrentCpr(String cpr) { this.currentCPR = cpr; }

    @Override
    public void handle(EkgData ekgData){
        if(observer != null){
            observer.handle(ekgData);
            ekgData.setID(currentCPR);


            //System.out.println("Got some data!");
            enqueue(ekgData);
            //System.out.println("Queue size: " + queue.size());
        }
        //ekgDAO.save(ekgData);
    }

    public EkgControllerImpl() {
        ekgDataRecorder.setObserver(this);
        Consumer consumer = new Consumer(queue, emptyLock);
        new Thread(consumer).start();
    }


    public void enqueue(EkgData ekgData){
        synchronized (queue){
            if (queue.size()<capacity){
                queue.add(ekgData);
//                notify();
            }
            synchronized (emptyLock) {
                emptyLock.notify();
            }
        }
    }

    public static void main(String[] args) {
        new EkgControllerImpl();
    }
    public void save(List<EkgData> Queue){
        //Simulate a slow save
        try {
            Thread.sleep(500);
            System.out.println("Data Saved: " + Queue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
