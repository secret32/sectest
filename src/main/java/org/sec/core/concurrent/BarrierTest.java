package org.sec.core.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 栅栏，等到线程到齐达到parties数量开始执行
 * @author zhouwei
 * @since 5/24/2017
 */
public class BarrierTest {

    public static void main(String[] args) {
        final CyclicBarrier barrier = new CyclicBarrier(5, () -> {
            System.out.println("test");
        });
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            threadPool.execute(() -> {
                try {
                    barrier.await();
                    System.out.println(Thread.currentThread().getName()
                            + " " + System.currentTimeMillis());
                    Thread.sleep(5000);
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
    }

}
