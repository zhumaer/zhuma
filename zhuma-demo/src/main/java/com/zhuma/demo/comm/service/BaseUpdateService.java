package com.zhuma.demo.comm.service;

import com.zhuma.demo.comm.model.po.BasePO;

/**
 * @desc 基础更新服务
 * 
 * @author jingkun.wang@baidao.com
 * @since 10/18/2017 18:31 PM
 */
public interface BaseUpdateService<T extends BasePO> {

	int updateByPrimaryKey(T record);

	int updateByPrimaryKeySelective(T record);

}
