package Business;

import Data.EKGSimulator;
import Data.EkgData;
import Data.Simulator;

import java.util.LinkedList;

//Some central business controller controller ;)
public class SyncQueue implements EKGObserver {
    public LinkedList<EkgData> queue = new LinkedList<>();
    public Object emptyLock = new Object();
    Simulator ekgSim = new EKGSimulator();
    int capacity = 400;

    public SyncQueue() {
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

    @Override
    public void handle(EkgData ekgData) {
        System.out.println("Got some data!");
        enqueue(ekgData);
        System.out.println("Queue size: " + queue.size());
        //SHOW data on screen!
    }

    public static void main(String[] args) {
        new SyncQueue();
    }
}
