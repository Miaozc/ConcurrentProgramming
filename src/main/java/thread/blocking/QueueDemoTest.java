package thread.blocking;

/**
 * Created by miaozc on 2019-9-5.
 */
public class QueueDemoTest {
    public static void main(String[] args) {
        BlockingQueueDemo blockingQueueDemo = new BlockingQueueDemo(5);
        for (int i=0;i<30;i++){
            int finalI = i;
            new Thread(()->blockingQueueDemo.put("第"+ finalI +"个")).start();
        }
        for (int i=0;i<50;i++){
            new Thread(()->blockingQueueDemo.take()).start();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
