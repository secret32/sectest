package org.sec.application.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zhouwei
 * @since 2017/5/26
 */
public class MyProducer {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        String connName = "localhost";
        String queueName = "hello";
        Connection connection = null;
        Channel channel = null;
        try {
            connection = connectionFactory.newConnection(connName);
            channel = connection.createChannel();
            channel.queueDeclare(queueName, false, false, false, null);
            StringBuilder msg = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                msg.append("hello, beijing[").append(i).append("]");
                channel.basicPublish("", queueName, null, msg.toString().getBytes());
                msg.delete(0, msg.length());
            }
        } finally {
            if (channel != null) channel.close();
            if (connection != null) connection.close();
        }
    }

}
