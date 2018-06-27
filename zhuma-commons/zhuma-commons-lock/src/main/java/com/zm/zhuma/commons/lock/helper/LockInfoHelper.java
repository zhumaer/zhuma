package com.zm.zhuma.commons.lock.helper;

import com.zm.zhuma.commons.lock.annotation.EasyLock;
import com.zm.zhuma.commons.lock.config.RedissonProperties;
import com.zm.zhuma.commons.lock.model.EasyLockInfo;
import com.zm.zhuma.commons.lock.model.LockType;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @desc 锁基础信息处理器
 *
 * @author zhumaer
 * @since 6/27/2018 11:29 PM
 */
public class LockInfoHelper {

    public static final String LOCK_NAME_PREFIX = "lock";
    public static final String LOCK_NAME_SEPARATOR = ".";

    @Autowired
    private RedissonProperties redissonProperties;

    @Autowired
    private BusinessKeyHelper businessKeyProvider;

    public EasyLockInfo get(ProceedingJoinPoint joinPoint, EasyLock lock) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        LockType type= lock.lockType();
        String businessKeyName=businessKeyProvider.getKeyName(joinPoint,lock);
        String lockName = LOCK_NAME_PREFIX+LOCK_NAME_SEPARATOR+getName(lock.name(), signature)+businessKeyName;
        long waitTime = getWaitTime(lock);
        long leaseTime = getLeaseTime(lock);
        return new EasyLockInfo(type,lockName,waitTime,leaseTime);
    }

    private String getName(String annotationName, MethodSignature signature) {
        if (annotationName.isEmpty()) {
            return String.format("%s.%s", signature.getDeclaringTypeName(), signature.getMethod().getName());
        } else {
            return annotationName;
        }
    }


    private long getWaitTime(EasyLock lock) {
        return lock.waitTime() == Long.MIN_VALUE ?
                redissonProperties.getWaitTime() : lock.waitTime();
    }

    private long getLeaseTime(EasyLock lock) {
        return lock.leaseTime() == Long.MIN_VALUE ?
                redissonProperties.getLeaseTime() : lock.leaseTime();
    }

}
