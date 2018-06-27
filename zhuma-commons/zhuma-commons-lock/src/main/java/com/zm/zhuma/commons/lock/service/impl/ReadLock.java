package com.zm.zhuma.commons.lock.service.impl;

import com.zm.zhuma.commons.lock.model.EasyLockInfo;
import com.zm.zhuma.commons.lock.service.Lock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @desc 读锁
 *
 * @author zhumaer
 * @since 6/27/2018 11:29 PM
 */
public class ReadLock implements Lock {

    private RReadWriteLock rLock;

    private EasyLockInfo easyLockInfo;

    private RedissonClient redissonClient;

    public ReadLock(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public boolean acquire() {
        try {
            rLock=redissonClient.getReadWriteLock(easyLockInfo.getName());
            return rLock.readLock().tryLock(easyLockInfo.getWaitTime(), easyLockInfo.getLeaseTime(), TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            return false;
        }
    }

    @Override
    public void release() {
        if(rLock.readLock().isHeldByCurrentThread()){
            rLock.readLock().unlockAsync();
        }
    }

    @Override
    public Lock setLockInfo(EasyLockInfo easyLockInfo) {
        this.easyLockInfo = easyLockInfo;
        return this;
    }

}
