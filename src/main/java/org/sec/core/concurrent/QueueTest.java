package org.sec.core.concurrent;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhouwei
 * @since 2017/5/22
 */
public class QueueTest {

    final private static BlockingQueue<Object> queue = new ArrayBlockingQueue<>(100);
                                                    // new LinkedBlockingQueue<>(100);

    private static class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Object command = queue.take();
                    System.out.println(command);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    private static class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                boolean notFull = queue.offer(new Object());
                if (!notFull) {
                    System.err.println("queue is full" + queue.size());
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BasicThreadFactory factory = new BasicThreadFactory.Builder()
                .namingPattern("test-thread-%d").daemon(true).priority(Thread.NORM_PRIORITY).build();
        int poolSize = 10;
        ExecutorService threadPool = Executors.newFixedThreadPool(poolSize, factory);
        for (int i = 0; i < poolSize; i++) {
            if (i < 3)
                threadPool.execute(new Consumer());
            else
                threadPool.execute(new Producer());
        }
        Thread.sleep(3 * 1000);
        threadPool.shutdown();
    }

}


