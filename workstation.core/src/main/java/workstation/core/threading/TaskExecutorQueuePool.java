package workstation.core.threading;

import workstation.core.exceptions.SystemException;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/16.
 */
public class TaskExecutorQueuePool {

    private static Hashtable<String, TaskExecutorQueue> QUEUES = new Hashtable<String, TaskExecutorQueue>();
    private static Object LOCK = new Object();
    private static TaskExecutorQueue DEFAULT_QUEUE;

    public static <TTask> TaskExecutorQueue<TTask> getInstance(String queueName) {
        if (queueName != null) {
            if (!QUEUES.containsKey(queueName)) {
                synchronized (LOCK) {
                    if (!QUEUES.containsKey(queueName)) {
                        QUEUES.put(queueName, new TaskExecutorQueue<TTask>(queueName));
                    }
                }
            }

            return QUEUES.get(queueName);
        } else {
            if (DEFAULT_QUEUE == null) {
                synchronized (LOCK) {
                    if (DEFAULT_QUEUE == null) {
                        DEFAULT_QUEUE = new TaskExecutorQueue<TTask>(null);
                    }
                }
            }

            return DEFAULT_QUEUE;
        }
    }

    public static void dispose() {
        for (Map.Entry<String, TaskExecutorQueue> task : QUEUES.entrySet()) {
            try {
                task.getValue().close();
            } catch (Exception ce) {
                throw new SystemException("Task stop error", ce);
            }
        }

        if (DEFAULT_QUEUE != null) {
            synchronized (LOCK) {
                if (DEFAULT_QUEUE != null) {
                    try {
                        DEFAULT_QUEUE.close();
                    } catch (Exception ce) {
                        throw new SystemException("Default task close error", ce);
                    }
                }
            }
        }
    }
}
