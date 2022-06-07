package Business;

import java.util.LinkedList;

public class Producer implements Runnable {
    //public LinkedList<Integer> queue;

    @Override
    public void run() {
        LinkedList<Integer> queue = new LinkedList<>();
        while (true) {
            synchronized (queue) {

            }

        }

    }
}
