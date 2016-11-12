package workstation.core.threading;

/**
 * Created by Administrator on 2016/11/10.
 */
public class TaskStopFlag {
    private boolean isStop = false;

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }
}