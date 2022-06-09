package Business;

import Data.EKGSimulator;
import Data.Sims;

import java.util.LinkedList;

public class Producer implements Runnable {
    //public LinkedList<Integer> queue;
    Sims ekgData = new EKGSimulator();
    int capacity = 400;

    @Override
    public void run() {
        LinkedList<Sims> queue = new LinkedList<>();
        while (true) {
            synchronized (queue) {
                while(queue.size() >= capacity){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                notify();
                queue.add(ekgData);

            }

        }

    }
}
