package com.zhuma.demo.comm.service;

import com.zhuma.demo.comm.model.po.TreePO;

/**
 * @desc 树结构crud服务
 *
 * @author zhumaer
 * @since 10/18/2017 18:31 PM
 */
public interface TreeCrudService<E extends TreePO, PK> extends
		CrudService<E, PK>,
		TreeSelectService<E, PK> {
}
