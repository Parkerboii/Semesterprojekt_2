package Business;

import Data.EkgData;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Consumer implements Runnable {


    private final LinkedList<EkgData> queue;
    private Object empty;

    public Consumer(LinkedList<EkgData> queue, Object emptyLock){
        this.queue = queue;
        this.empty = emptyLock;
    }

    @Override
    public void run() {
        while (true) {
          //  synchronized (empty){
          //      while(queue.isEmpty()) {
//                    try {
//                        //empty.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }

            //    }
                synchronized (queue) {
                    if (!queue.isEmpty()) {
                        LinkedList<EkgData> saveList = new LinkedList<EkgData>();
                        saveList.addAll(queue);
                        queue.clear();
                        System.out.println("Saving some data! : " + saveList.toString());
                        //TODO: Save the data!
                    }
                }
                try {
                    //Simulating a LOONG save
                    Thread.sleep(1600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Removed som snazzy data!");
            }

      //  }
    }
}

