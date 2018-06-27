package com.zm.zhuma.commons.lock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @desc 锁基本信息
 *
 * @author zhumaer
 * @since 6/27/2018 11:29 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EasyLockInfo {

    private LockType type;

    private String name;

    private long waitTime;

    private long leaseTime;

}
