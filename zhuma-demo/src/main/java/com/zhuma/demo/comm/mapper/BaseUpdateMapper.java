package com.zhuma.demo.comm.mapper;

import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeySelectiveMapper;
import tk.mybatis.mapper.common.condition.UpdateByConditionMapper;
import tk.mybatis.mapper.common.condition.UpdateByConditionSelectiveMapper;

import com.zhuma.demo.comm.model.po.BasePO;

/**
 * @desc 基础更新功能mapper
 * 
 * @author jingkun.wang@baidao.com
 * @since 10/18/2017 18:31 PM
 */
public interface BaseUpdateMapper<T extends BasePO> extends Marker,
												UpdateByPrimaryKeyMapper<T>,
												UpdateByPrimaryKeySelectiveMapper<T>,
												UpdateByConditionMapper<T>,
												UpdateByConditionSelectiveMapper<T> {
}
