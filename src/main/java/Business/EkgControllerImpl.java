package Business;
import Data.*;

import java.util.LinkedList;

public class EkgControllerImpl implements EkgController, EKGObserver {
    Simulator ekgDataRecorder = new EKGSimulator();
    private EKGObserver observer;
    private EkgDAO ekgDAO = new SQLImplementation();

    public LinkedList<EkgData> queue = new LinkedList<>();
    public Object emptyLock = new Object();
    Simulator ekgSim = new EKGSimulator();
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
        }
        System.out.println("Got some data!");
        enqueue(ekgData);
        System.out.println("Queue size: " + queue.size());

        ekgDAO.save(ekgData);
    }

    public EkgControllerImpl() {
        ekgSim.setObserver(this);
        ekgSim.record();
        Consumer consumer = new Consumer(queue, emptyLock);
        new Thread(consumer).start();
    }


    public void enqueue(EkgData ekgData){
        synchronized (queue){
            if (queue.size()<400){
                queue.add(ekgData);
            }
            synchronized (emptyLock) {
                emptyLock.notify();
            }
        }
    }

    public static void main(String[] args) {
        new EkgControllerImpl();
    }
}
