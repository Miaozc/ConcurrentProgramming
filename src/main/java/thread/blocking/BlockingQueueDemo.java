package thread.blocking;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by miaozc on 2019-9-5.
 */
public class BlockingQueueDemo {

    /**
     * 队列最大长度
     */
    private final int maxSize;
    /**
     * 队列当前长度
     */
    private volatile AtomicInteger count = new AtomicInteger(0);
    /**
     * 头节点
     */
    private volatile Node head;
    /**
     * 尾节点
     */
    private volatile Node tail;
    private final ReentrantLock  reentrantLock = new ReentrantLock();
    private final Condition putCondition = reentrantLock.newCondition();
    private final Condition takeCondition = reentrantLock.newCondition();
    private volatile AtomicInteger putCount = new AtomicInteger(0);
    private volatile AtomicInteger takeCount = new AtomicInteger(0);
    static final class Node{
        private Object object;
        private Node next;
        public Node(Object object) {
            this.object = object;
        }
    }
    private void linkLast(Node node){
        if(tail!=null){
            tail.next = node;
        }
        tail = node;
        if(head == null){
            head = node;
        }
        count.getAndIncrement();
    }
    private Object removeFirst() throws Exception {
        if (head==null){
            throw new Exception("head 异常");
        }
        Node rtn = head;
        head = head.next;
        count.getAndDecrement();
        return rtn;
    }

    public BlockingQueueDemo(int maxSize) {
        this.maxSize = maxSize;
    }

    public void put(Object arg){
        reentrantLock.lock();
        try{
            if(count.get()>=maxSize){
                putCount.getAndIncrement();
                putCondition.await();
            }
            Node node = new Node(arg);
            linkLast(node);
            if (takeCount.get()>0){
                takeCount.getAndDecrement();
            }
            System.out.println("已添加"+arg.toString()+"  count= "+count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            reentrantLock.unlock();
        }
    }
    public Object take(){
        reentrantLock.lock();
        try{
            if (head==null){
                try {
                    takeCount.getAndIncrement();
                    takeCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Object object = removeFirst();
            if (putCount.get()>0){
                putCondition.signal();
            }
            System.out.println("取出"+object.toString()+"  count= "+count);
            return object;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            reentrantLock.unlock();
        }
        return null;
    }
}
