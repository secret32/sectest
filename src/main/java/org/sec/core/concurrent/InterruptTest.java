package org.sec.core.concurrent;

/**
 * @author zhouwei
 * @since 2017/5/22
 */
public class InterruptTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            try {
                System.out.println("sleeping");
                Thread.sleep(3000);
                System.out.println("wake up");
            } catch (InterruptedException e) {
                System.out.println("interrupt");
                Thread.currentThread().interrupt();
            }
            System.out.println("end");
        });
        t.start();
        t.interrupt();
        // t.join(5);
    }

}
