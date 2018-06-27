package com.zm.zhuma.commons.lock.handler;

import com.zm.zhuma.commons.lock.annotation.EasyLock;
import com.zm.zhuma.commons.lock.service.Lock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import com.zm.zhuma.commons.lock.service.impl.LockFactory;
import org.springframework.stereotype.Component;

/**
 * @desc 锁注解切面处理
 *
 * @author zhumaer
 * @since 6/27/2018 11:29 PM
 */
@Aspect
@Component
public class EasyLockAspectHandler {

    @Autowired
    LockFactory lockFactory;

    @Around(value = "@annotation(easyLock)")
    public Object around(ProceedingJoinPoint joinPoint, EasyLock easyLock) throws Throwable {
        Lock lock = lockFactory.getLock(joinPoint, easyLock);
        boolean currentThreadLock = false;
        try {
            currentThreadLock = lock.acquire();
            return joinPoint.proceed();
        } finally {
            if (currentThreadLock) {
                lock.release();
            }
        }
    }
}
