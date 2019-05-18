package thread.demo;

/**
 * 并发执行接口
 * Created by miaozc on 2019-5-18.
 */
public interface IParallelProcess {

    void Process(Task task);

    void shutdown();

    void shutdownAll();

    void startAll();

    void joinAll();

}
