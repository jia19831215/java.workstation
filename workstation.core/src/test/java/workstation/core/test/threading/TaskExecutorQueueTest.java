package workstation.core.test.threading;

import org.junit.Test;
import workstation.core.threading.TaskExecutorQueue;
import workstation.core.threading.TaskExecutorQueuePool;

import java.io.IOException;

/**
 * Created by Administrator on 2016/11/16.
 */
public class TaskExecutorQueueTest {

    @Test
    public void testTaskExecutorQueuePool() throws IOException {

        TaskExecutorQueue<String> queue = TaskExecutorQueuePool.getInstance("Name");

        queue.registerProcess(v -> System.out.println("MSG：" + v));

        queue.start();
    }
}
