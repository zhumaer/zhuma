package com.zhuma.demo.comm.service;

/**
 * @desc 基础删除服务
 *
 * @author zhuamer
 * @since 10/18/2017 18:31 PM
 */
public interface DeleteService<PK> {

    /**
     * 根据主键删除记录
     *
     * @param pk 主键
     * @return 影响记录数
     */
    int deleteByPk(PK pk);

    /**
     * 根据主键删除记录
     *
     * @param pks 主键集合
     * @return 影响记录数
     */
    int deleteByPks(Iterable<PK> pks);
}
