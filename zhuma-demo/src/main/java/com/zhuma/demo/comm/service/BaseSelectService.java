package com.zhuma.demo.comm.service;

import com.zhuma.demo.comm.model.po.BasePO;
import com.zhuma.demo.comm.model.qo.PageQO;
import com.zhuma.demo.comm.model.vo.PageVO;

/**
 * @desc 基础查看服务
 * 
 * @author jingkun.wang@baidao.com
 * @since 10/18/2017 18:31 PM
 */
public interface BaseSelectService<T extends BasePO> {

	T selectByPrimaryKey(Object primaryKey);

	PageVO<T> selectPageVO(PageQO<T> pageQO);

}
