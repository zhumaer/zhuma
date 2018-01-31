package com.zhuma.demo.comm.service;

/**
 * @desc 通用服务类
 *
 * @author zhuamer
 * @since 10/18/2017 18:31 PM
 */
public interface CrudService<E, PK> extends
		InsertService<E, PK>,
        UpdateService<E,PK>,
        DeleteService<PK>,
		SelectService<E, PK> {
}
