package com.zhuma.demo.comm.mapper;

import com.zhuma.demo.comm.model.po.BasePO;

/**
 * @desc 基础增删改查功能mapper
 * 
 * @author jingkun.wang@baidao.com
 * @since 10/18/2017 18:31 PM
 */
public interface BaseMapper<T extends BasePO> extends BaseInsertMapper<T>,
														BaseDeleteMapper<T>,
														BaseUpdateMapper<T>,
														BaseSelectMapper<T> {
}
