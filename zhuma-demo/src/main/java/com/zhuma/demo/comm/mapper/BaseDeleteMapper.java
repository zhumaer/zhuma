package com.zhuma.demo.comm.mapper;

import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.base.delete.DeleteByPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.delete.DeleteMapper;
import tk.mybatis.mapper.common.condition.DeleteByConditionMapper;
import tk.mybatis.mapper.common.ids.DeleteByIdsMapper;

import com.zhuma.demo.comm.model.po.BasePO;

/**
 * @desc 基础删除功能mapper
 * 
 * @author jingkun.wang@baidao.com
 * @since 10/18/2017 18:31 PM
 */
public interface BaseDeleteMapper<T extends BasePO> extends Marker,
												DeleteMapper<T>,
												DeleteByPrimaryKeyMapper<T>,
												DeleteByConditionMapper<T>,
												DeleteByIdsMapper<T> {
}
