package com.zhuma.demo.comm.mapper;

import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;
import tk.mybatis.mapper.common.base.insert.InsertSelectiveMapper;

import com.zhuma.demo.comm.model.po.BasePO;

/**
 * @desc 基础插入功能mapper
 * 
 * @author jingkun.wang@baidao.com
 * @since 10/18/2017 18:31 PM
 */
public interface BaseInsertMapper<T extends BasePO> extends Marker,
												InsertMapper<T>,
												InsertSelectiveMapper<T>,
												MySqlMapper<T>{
}
