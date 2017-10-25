package com.zhuma.demo.comm.mapper;

import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.base.select.ExistsWithPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.select.SelectAllMapper;
import tk.mybatis.mapper.common.base.select.SelectByPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.select.SelectCountMapper;
import tk.mybatis.mapper.common.base.select.SelectMapper;
import tk.mybatis.mapper.common.base.select.SelectOneMapper;
import tk.mybatis.mapper.common.condition.SelectByConditionMapper;
import tk.mybatis.mapper.common.condition.SelectCountByConditionMapper;
import tk.mybatis.mapper.common.ids.SelectByIdsMapper;

import com.zhuma.demo.comm.model.po.BasePO;

/**
 * @desc 基础选择功能mapper
 * 
 * @author jingkun.wang@baidao.com
 * @since 10/18/2017 18:31 PM
 */
public interface BaseSelectMapper<T extends BasePO> extends Marker,
												SelectOneMapper<T>,
												SelectMapper<T>,
												SelectAllMapper<T>,
												SelectCountMapper<T>,
												SelectByPrimaryKeyMapper<T>,
												ExistsWithPrimaryKeyMapper<T>,
												SelectByIdsMapper<T>,
												SelectByConditionMapper<T>,
												SelectCountByConditionMapper<T> {
}
