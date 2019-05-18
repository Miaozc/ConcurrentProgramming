package thread.demo;

import thread.demo.thread.*;

/**
 * Created by miaozc on 2019-5-18.
 */
public class ThreadDemo {
    public static int taskNum = 50;

    /**
     * 测试多线程并行
     */
    private static void testThread(){
        AbstractTaskParallel taskParallelFive = new TaskParallelFive(null);
        AbstractTaskParallel taskParallelFour = new TaskParallelFour(taskParallelFive);
        AbstractTaskParallel taskParallelThree = new TaskParallelThree(taskParallelFour);
        AbstractTaskParallel taskParallelTwo = new TaskParallelTwo(taskParallelThree);
        AbstractTaskParallel taskParallelOne = new TaskParallelOne(taskParallelTwo);
        for(int i=0;i<taskNum;i++){
            taskParallelOne.Process(new Task(i+""));
        }
        taskParallelOne.startAll();
        taskParallelOne.shutdownAll();
    }

    /**
     * 测试主线程串行
     */
    private static void testUnThread(){
        AbstractTaskParallel taskParallelFive = new TaskParallelFive(null);
        AbstractTaskParallel taskParallelFour = new TaskParallelFour(null);
        AbstractTaskParallel taskParallelThree = new TaskParallelThree(null);
        AbstractTaskParallel taskParallelTwo = new TaskParallelTwo(null);
        AbstractTaskParallel taskParallelOne = new TaskParallelOne(null);
        for(int i=0;i<taskNum;i++){
            taskParallelFive.runTask(new Task(i+""));
            taskParallelFour.runTask(new Task(i+""));
            taskParallelThree.runTask(new Task(i+""));
            taskParallelTwo.runTask(new Task(i+""));
            taskParallelOne.runTask(new Task(i+""));

        }
    }

    public static void main(String[] args) {

        //testThread();
        testUnThread();
    }


}
