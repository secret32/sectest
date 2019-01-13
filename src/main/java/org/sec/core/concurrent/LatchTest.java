package org.sec.core.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁。倒计数器，倒计数到0时等待计数器的线程开始一起执行。
 * @author zhouwei
 * @since 2017/5/22
 */
public class LatchTest {

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 10;
        final CountDownLatch startLatch = new CountDownLatch(1);
        final CountDownLatch stopLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            Thread t = new Thread(new ExtThread(startLatch, stopLatch));
            t.setName(String.format("t-%d", i));
            t.start();
        }
        startLatch.countDown();
        System.out.println("main waiting...");
        stopLatch.await();
        System.out.println("main exited");
    }

    private static class ExtThread implements Runnable {
        private CountDownLatch startLatch, stopLatch;
        ExtThread(CountDownLatch startLatch, CountDownLatch stopLatch) {
            this.startLatch = startLatch;
            this.stopLatch = stopLatch;
        }
        @Override
        public void run() {
            String pName = Thread.currentThread().getName();
            try {
                System.out.println(pName + " waiting...");
                startLatch.await();
                System.out.println(pName + "\t" + System.currentTimeMillis());
                stopLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(pName + " exited");
            }
        }
    }

}
