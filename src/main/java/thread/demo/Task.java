package thread.demo;

/**
 * Created by miaozc on 2019-5-18.
 */
public class Task {

    public Task(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
