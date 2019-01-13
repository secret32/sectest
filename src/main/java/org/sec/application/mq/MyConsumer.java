package org.sec.application.mq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author zhouwei
 * @since 2017/5/26
 */
public class MyConsumer {

    public static void main(String[] args) throws InterruptedException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        String connName = "localhost";
        String queueName = "hello";

        int threadCount = 3;
        final AtomicBoolean running = new AtomicBoolean(true);
        final CountDownLatch latch = new CountDownLatch(threadCount);
        ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount; i++) {
            threadPool.execute(() -> {
                Connection connection = null;
                try {
                    latch.await();
                    String pName = Thread.currentThread().getName();
                    connection = connectionFactory.newConnection(connName);
                    final Channel channel = connection.createChannel();
                    channel.queueDeclare(queueName, false, false, false, null);

                    Consumer consumer = new DefaultConsumer(channel) {
                        @Override
                        public void handleDelivery(String consumerTag, Envelope envelope,
                                                   AMQP.BasicProperties properties, byte[] body) throws IOException {
                            try {
                                String msg = new String(body, StandardCharsets.UTF_8);
                                System.out.println(pName + "\t\t" + msg);
                            } finally {
                                channel.basicAck(envelope.getDeliveryTag(), false);
                            }
                        }
                    };
                    while (running.get()) {
                        try {
                            channel.basicConsume(queueName, consumer);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Thread.sleep(2000);
                    }
                    channel.close();
                    System.out.println(pName + " end");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (connection != null) connection.close();
                    } catch (Exception ignore) {
                    }
                }
            });
            latch.countDown();
        }

        Thread.sleep(30 * 1000);
        running.set(false);

        threadPool.shutdown();

    }

}
