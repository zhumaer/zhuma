package com.zhuma.demo.comm.mapper;

import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.base.select.*;
import tk.mybatis.mapper.common.condition.SelectByConditionMapper;
import tk.mybatis.mapper.common.condition.SelectCountByConditionMapper;
import tk.mybatis.mapper.common.example.SelectByExampleMapper;
import tk.mybatis.mapper.common.ids.SelectByIdsMapper;

/**
 * @desc 基础选择功能mapper
 *
 * @author zhumaer
 * @since 10/18/2017 18:31 PM
 */
public interface SelectMapper<T> extends Marker,
		SelectOneMapper<T>,
        tk.mybatis.mapper.common.base.select.SelectMapper<T>,
		SelectAllMapper<T>,
		SelectCountMapper<T>,
		SelectByPrimaryKeyMapper<T>,
		ExistsWithPrimaryKeyMapper<T>,
		SelectByIdsMapper<T>,
		SelectByConditionMapper<T>,
		SelectCountByConditionMapper<T>,
		SelectByExampleMapper<T> {
}
