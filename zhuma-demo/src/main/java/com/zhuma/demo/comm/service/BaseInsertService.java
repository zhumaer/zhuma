package com.zhuma.demo.comm.service;

import com.zhuma.demo.comm.model.po.BasePO;

/**
 * @desc 基础插入服务
 * 
 * @author jingkun.wang@baidao.com
 * @since 10/18/2017 18:31 PM
 */
public interface BaseInsertService<T extends BasePO> {

	int insert(T record);

}
