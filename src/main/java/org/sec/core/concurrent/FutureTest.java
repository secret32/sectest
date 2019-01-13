package org.sec.core.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author zhouwei
 * @since 2017/5/23
 */
public class FutureTest {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<Long> future = threadPool.submit(() -> System.currentTimeMillis() * 2);
        try {
            long result = future.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        FutureTask<Integer> futureTask = new FutureTask<>(() -> Long.valueOf(System.currentTimeMillis() % 5).intValue());
        threadPool.execute(futureTask);
        try {
            int i = futureTask.get();
            System.out.println(i);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        threadPool.shutdown();
    }

}
