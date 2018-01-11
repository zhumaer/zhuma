package com.zhuma.demo.comm.mapper;

import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.base.insert.InsertSelectiveMapper;

/**
 * @desc 基础插入功能mapper
 * 
 * @author zhumaer
 * @since 10/18/2017 18:31 PM
 */
public interface InsertMapper<T> extends Marker,
		tk.mybatis.mapper.common.base.insert.InsertMapper<T>,
												InsertSelectiveMapper<T>,
												MySqlMapper<T>{
}
