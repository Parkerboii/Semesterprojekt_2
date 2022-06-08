package Business;

import java.sql.Timestamp;
import java.util.Queue;

public class Consumer implements Runnable {
    private Queue<Integer> queue;

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while(queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int EKG = queue.remove();

                    notify() ;

                }
                }

            }
        }
    }

