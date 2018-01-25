package com.zhuma.demo.comm.mapper;

import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.base.delete.DeleteByPrimaryKeyMapper;
import tk.mybatis.mapper.common.condition.DeleteByConditionMapper;
import tk.mybatis.mapper.common.ids.DeleteByIdsMapper;

/**
 * @desc 基础删除功能mapper
 * 
 * @author zhumaer
 * @since 10/18/2017 18:31 PM
 */
public interface DeleteMapper<T> extends Marker,
        tk.mybatis.mapper.common.base.delete.DeleteMapper<T>,
												DeleteByPrimaryKeyMapper<T>,
												DeleteByConditionMapper<T>,
												DeleteByIdsMapper<T> {
}
