package com.zhuma.demo.comm.mapper;

/**
 * @desc 基础增删改查功能mapper
 * 
 * @author zhumaer
 * @since 10/18/2017 18:31 PM
 */
public interface CrudMapper<T> extends
		InsertMapper<T>,
		DeleteMapper<T>,
		UpdateMapper<T>,
		SelectMapper<T> {
}
