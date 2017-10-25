package com.zhuma.demo.comm.service;

import com.zhuma.demo.comm.model.po.BasePO;

/**
 * @desc 基础服务类
 * 
 * @author jingkun.wang@baidao.com
 * @since 10/18/2017 18:31 PM
 */
public interface BaseService<T extends BasePO> extends BaseInsertService<T>,
														BaseDeleteService<T>,
														BaseUpdateService<T>,
														BaseSelectService<T> {

}
