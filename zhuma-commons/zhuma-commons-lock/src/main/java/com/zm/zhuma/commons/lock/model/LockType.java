package com.zm.zhuma.commons.lock.model;

/**
 * @desc 锁类型
 *
 * @author zhumaer
 * @since 6/27/2018 11:29 PM
 */
public enum LockType {
    /**
     * 可重入锁
     */
    Reentrant,
    /**
     * 公平锁
     */
    Fair,
    /**
     * 读锁
     */
    Read,
    /**
     * 写锁
     */
    Write
}
