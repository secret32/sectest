package org.sec.core.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 计数信号量
 * @author zhouwei
 * @since 5/24/2017
 */
public class SemaphoreTest {

//    public static void test() {
//        Map<String, String> map = new HashMap<>();
//        String v = map.put("test", "test1");
//        System.out.println(v);
//        v = map.put("test", "test2");
//        System.out.println(v);
//    }

    public static void main(String[] args) {
        // test();
        // 许可数为5的信号量
        final Semaphore semaphore = new Semaphore(5);
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            threadPool.execute(() -> {
                try {
                    // 获取到信号量的线程才能运行，其余的阻塞
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    // 释放信号量
                    semaphore.release();
                }
            });
        }
        threadPool.shutdown();
    }

}
