package org.sec.application.redis;

import redis.clients.jedis.Jedis;

/**
 * @author Secret
 * @since 4/27/2016.
 */
public class RedisTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        jedis.set("admin", "123456");
        String value = jedis.get("admin");
        System.out.println(value);
    }

}
