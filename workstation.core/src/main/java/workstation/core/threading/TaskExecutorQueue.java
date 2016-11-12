package workstation.core.threading;

import workstation.core.exceptions.SystemException;
import workstation.core.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Administrator on 2016/11/10.
 */
public class TaskExecutorQueue<TTask> implements AutoCloseable {

    protected ExecutorService executorService = Executors.newCachedThreadPool();
    protected List<Consumer<TTask>> process = new ArrayList<Consumer<TTask>>();
    protected String queueName = null;
    protected BlockingQueue queue = new LinkedBlockingQueue<TTask>();
    protected Function<BlockingQueue<TTask>, Boolean> stop;
    protected TaskStopFlag flag = new TaskStopFlag();

    public TaskExecutorQueue(String queueName) {
        if (queueName == null) {
            this.queueName = StringUtil.newGuid();
        }
    }

    /**
     * 注册处理函数
     * @param process 处理消息函数
     * @return
     */
    public TaskExecutorQueue<TTask> registerProcess(Consumer<TTask> process) {
        this.process.add(process);
        return this;
    }

    /**
     * 注册暂停条件
     * @param stop 暂停条件函数
     * @return
     */
    public TaskExecutorQueue<TTask> registerStop(Function<BlockingQueue<TTask>, Boolean> stop) {
        this.stop = stop;
        return this;
    }

    /**
     * 添加消息
     * @param task 消息任务
     * @return
     */
    public TaskExecutorQueue<TTask> push(TTask task) {
        try {
            this.queue.put(task);
        } catch (Throwable ce) {
            throw new SystemException("TaskExecutorQueue push error", ce);
        }

        return this;
    }

    /**
     * 开始运行
     * @return
     */
    public TaskExecutorQueue<TTask> start() {
        if (this.stop == null)
            this.stop = q -> true;

        this.flag.setStop(false);

        Consumer<List<Consumer<TTask>>> action = p -> {
            while (true) {
                if (this.flag.isStop()) {
                    break;
                }

                try {
                    TTask item = (TTask) this.queue.take();

                    p.forEach(v -> v.accept(item));

                } catch (Throwable ce) {
                    throw new SystemException("TaskExecutorQueue process error", ce);
                }
            }
        };

        this.executorService.execute(new Runnable() {

            @Override
            public void run() {
                action.accept(process);
            }
        });

        return this;
    }

    /**
     * 停止
     * @throws Exception
     */
    public void stop() throws Exception {
        this.close();
    }

    @Override
    public void close() throws Exception {
        this.flag.setStop(true);
        this.executorService.shutdown();
    }
}
