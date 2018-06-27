package com.zm.zhuma.commons.lock.service.impl;

import com.zm.zhuma.commons.lock.model.EasyLockInfo;
import com.zm.zhuma.commons.lock.service.Lock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @desc 可重入读锁
 *
 * @author zhumaer
 * @since 6/27/2018 11:29 PM
 */
public class ReentrantLock implements Lock {

    private RLock rLock;

    private EasyLockInfo easyLockInfo;

    private RedissonClient redissonClient;

    public ReentrantLock(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public boolean acquire() {
        try {
            rLock=redissonClient.getLock(easyLockInfo.getName());
            return rLock.tryLock(easyLockInfo.getWaitTime(), easyLockInfo.getLeaseTime(), TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            return false;
        }
    }

    @Override
    public void release() {
        if(rLock.isHeldByCurrentThread()){
            rLock.unlockAsync();
        }
    }

    @Override
    public Lock setLockInfo(EasyLockInfo easyLockInfo) {
        this.easyLockInfo = easyLockInfo;
        return this;
    }

}
