package Business;

import Data.EkgDAO;
import Data.EkgData;
import Data.SQLImplementation;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Consumer implements Runnable {


    private final LinkedList<EkgData> queue;
    private Object empty;
    private EkgDAO ekgDAO = new SQLImplementation();

    public Consumer(LinkedList<EkgData> queue, Object emptyLock) {
        this.queue = queue;
        this.empty = emptyLock;
    }

    @Override
    public void run() {
        while (true) {
//            synchronized (empty) {
//                while (queue.isEmpty()) {
//                    try {
//                        empty.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                }
            LinkedList<EkgData> saveList = new LinkedList<EkgData>();
                synchronized (queue) {
                    if (!queue.isEmpty()) {
                        saveList.addAll(queue);
                        queue.clear();
//                        System.out.println("Saving some data! : " + saveList.toString());
//                        System.out.println("Removed som snazzy data!");
                    }
//                    if (queue.isEmpty()){
//                        try{
//                            empty.wait();
//                        }
//                        catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
                }

                try {
                    ekgDAO.save(saveList);//Simulating a LOONG save
                    Thread.sleep(1600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//        }
    }
}

