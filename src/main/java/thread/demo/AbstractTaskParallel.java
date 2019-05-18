package thread.demo;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by miaozc on 2019-5-18.
 */
public abstract class AbstractTaskParallel extends Thread implements IParallelProcess {
    //阻塞队列
    private LinkedBlockingQueue<Task> tasks=new LinkedBlockingQueue<Task>();

    private IParallelProcess nextProcess;

    private volatile boolean isFinish = false;

    public AbstractTaskParallel(IParallelProcess nextProcess){
        this.nextProcess = nextProcess;
    }
    @Override
    public void run() {
        try {
            while (!isFinish||tasks.size()>0){
                Task task = tasks.take();
                runTask(task);
                if(nextProcess!=null){
                    nextProcess.Process(task);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理任务的业务逻辑
     * @param task
     */
    public abstract void runTask(Task task );

    public void Process(Task task) {
        tasks.add(task);
    }

    public void shutdown() {
        isFinish = true;

    }

    public void shutdownAll() {
        this.shutdown();
        if (nextProcess!=null){
            nextProcess.shutdownAll();
        }
    }

    public void startAll() {
        this.start();
        if (nextProcess!=null){
            nextProcess.startAll();
        }
    }
    public void joinAll() {
        try {
            this.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (nextProcess!=null){
            nextProcess.joinAll();
        }
    }
}
