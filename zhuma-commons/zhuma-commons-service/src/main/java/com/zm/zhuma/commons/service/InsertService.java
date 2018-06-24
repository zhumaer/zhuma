package com.zm.zhuma.commons.service;

/**
 * @desc 基础插入服务
 *
 * @author zhuamer
 * @since 10/18/2017 18:31 PM
 */
public interface InsertService<E, PK> {

    /**
     * 添加一条数据
     *
     * @param record 要添加的数据
     * @return 添加后生成的主键
     */
    PK insert(E record);
}
