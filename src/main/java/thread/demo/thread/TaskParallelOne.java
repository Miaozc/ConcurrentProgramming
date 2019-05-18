package thread.demo.thread;

import thread.demo.AbstractTaskParallel;
import thread.demo.IParallelProcess;
import thread.demo.Task;
import thread.demo.TimeLog;

import java.util.concurrent.TimeUnit;

/**
 * 第一个并行任务
 * Created by miaozc on 2019-5-18.
 */
public class TaskParallelOne extends AbstractTaskParallel {


    public TaskParallelOne(IParallelProcess nextProcess) {
        super(nextProcess);
    }

    public void runTask(Task task) {
        try {
            TimeUnit.MILLISECONDS.sleep(TimeLog.sleepNum);
            TimeLog.printTime();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
