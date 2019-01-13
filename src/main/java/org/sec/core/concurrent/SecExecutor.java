package org.sec.core.concurrent;

import java.util.concurrent.*;

/**
 * Created by secret on 14-6-30.
 */
public class SecExecutor {

    private ThreadPoolExecutor executor;

    private SecExecutor(ThreadPoolExecutor executor) {
        this.executor = executor;
    }

    public static SecExecutor init(int size) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(size, size * 2, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(size), new ThreadPoolExecutor.AbortPolicy());
        return new SecExecutor(executor);
    }

    public void execute(Runnable runnable) {
        executor.execute(runnable);
    }

    public void destroy() {
        if (executor != null && !executor.isShutdown())
            executor.shutdown();
    }

}
