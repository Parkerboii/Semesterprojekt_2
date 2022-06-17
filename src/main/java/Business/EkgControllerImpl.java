package Business;
import Data.*;
import com.fazecast.jSerialComm.SerialPort;
import java.util.LinkedList;

import java.util.List;

public class EkgControllerImpl implements EkgController, EKGObserver {
    Simulator ekgDataRecorder = new Arduino();
    private EKGObserver observer;
    private EkgDAO ekgDAO = new SQLImplementation();

    public LinkedList<EkgData> queue = new LinkedList<>();
    public Object emptyLock = new Object();
    Simulator ekgSim = new Arduino();
    int capacity = 400;

    @Override
    public void startRecording(){
        ekgDataRecorder.record();
        ekgDataRecorder.setObserver(this);
    }

    @Override
    public void registerObserver(EKGObserver ekgObserver){this.observer = ekgObserver;}

    @Override
    public void handle(EkgData ekgData){
        if(observer != null){
            observer.handle(ekgData);

            System.out.println("Got some data!");
            enqueue(ekgData);
            System.out.println("Queue size: " + queue.size());
        }
        ekgDAO.save(ekgData);
    }

    public EkgControllerImpl() {
        ekgDataRecorder.setObserver(this);
        ekgDataRecorder.record();
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
