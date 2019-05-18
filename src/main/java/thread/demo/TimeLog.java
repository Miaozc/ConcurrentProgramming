package thread.demo;

/**
 * 记录执行时间
 * Created by miaozc on 2019-5-18.
 */
public class TimeLog {
    public static int sleepNum = 20;
    public static volatile Long startTime = null;
    public static volatile Long endTime = null;

    public static synchronized void printTime(){
        Long time = System.currentTimeMillis();
        if(startTime==null){
            startTime = time;
        }
        endTime = time;
        long time2 = endTime-startTime;
        System.out.println(time2);
    }
}
