package com.sachin;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissonTest implements Runnable {

    private static int count=10000;

    private static RedissonClient redissonClient;
    private static  void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis//127.0.0.1:6379").setDatabase(10);

        redissonClient = Redisson.create(config);
    }
    @Override
    public void run() {

        RLock anyLock = redissonClient.getLock("anyLock");
        anyLock.lock();
        count--;
        System.out.println(count);
        anyLock.unlock();

    }
}
