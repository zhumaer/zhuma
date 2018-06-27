package com.zm.zhuma.commons.lock.service;


import com.zm.zhuma.commons.lock.model.EasyLockInfo;

/**
 * @desc 锁上层接口
 *
 * @author zhumaer
 * @since 6/27/2018 11:29 PM
 */
public interface Lock {

    Lock setLockInfo(EasyLockInfo easyLockInfo);

    boolean acquire();

    void release();
}
