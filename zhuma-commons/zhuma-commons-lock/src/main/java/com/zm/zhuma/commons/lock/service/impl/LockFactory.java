package com.zm.zhuma.commons.lock.service.impl;

import com.zm.zhuma.commons.lock.annotation.EasyLock;
import com.zm.zhuma.commons.lock.model.EasyLockInfo;
import com.zm.zhuma.commons.lock.model.LockType;
import com.zm.zhuma.commons.lock.service.Lock;
import com.zm.zhuma.commons.lock.helper.LockInfoHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @desc 锁工厂
 *
 * @author zhumaer
 * @since 6/27/2018 11:29 PM
 */
public class LockFactory  {
    Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private LockInfoHelper lockInfoProvider;

    private static final Map<LockType, Lock> lockMap= new HashMap<>();

    @PostConstruct
    public void init(){
        lockMap.put(LockType.Reentrant, new ReentrantLock(redissonClient));
        lockMap.put(LockType.Fair, new FairLock(redissonClient));
        lockMap.put(LockType.Read, new ReadLock(redissonClient));
        lockMap.put(LockType.Write, new WriteLock(redissonClient));
    }

    public Lock getLock(ProceedingJoinPoint joinPoint, EasyLock easyLock){
        EasyLockInfo easyLockInfo = lockInfoProvider.get(joinPoint, easyLock);
        return lockMap.get(easyLockInfo.getType()).setLockInfo(easyLockInfo);
    }

}
