package org.sec.application.other;

/**
 * 面试题：主线程和子线程交替执行50次
 *
 * @author zhouwei
 * @since 12/4/2018
 */
public class ThreadEx {

    public static void main(String[] args) {
        final int count = 50;
        Service service = new Service();
        Thread thread = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                service.sub();
            }
        });
        thread.start();
        for (int i = 0; i < count; i++) {
            service.main();
        }
    }

}

class Service {

    final private Object lock = new Object();
    private volatile int state = 2;

    final public void main() {
        synchronized (lock) {
            if (state != 1) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getId() + "\t" + i);
            }
            state = 2;
            lock.notify();
        }
    }

    final public void sub() {
        synchronized (lock) {
            if (state != 2) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getId() + "\t" + i);
            }
            state = 1;
            lock.notify();
        }
    }

}
