package Business;

import java.util.LinkedList;

public class Producer implements Runnable {
    //public LinkedList<Integer> queue;
    int ekgData;
    int capacity = 400;

    @Override
    public void run() {
        LinkedList<Integer> queue = new LinkedList<>();
        while (true) {
            synchronized (queue) {
                while(queue.size() >= capacity){
                    try {
                        notify();
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.add(ekgData);


            }

        }

    }
}
