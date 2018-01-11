package com.zhuma.demo.comm.service.impl;

import java.util.List;

import com.zhuma.demo.comm.model.bo.Node;
import com.zhuma.demo.comm.model.po.TreePO;
import com.zhuma.demo.comm.service.TreeCrudService;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class TreeCurdServiceImpl<E extends TreePO<PK>, PK> extends MySqlCrudServiceImpl<E, PK> implements TreeCrudService<E, PK> {

	@Override
	public List<E> selectChildren(PK parentId) {
		Assert.notNull(parentId, "parentId is null");

		try {
			E e = poType.newInstance();
			e.setParentId(parentId);
			return crudMapper.select(e);
		} catch (InstantiationException | IllegalAccessException e) {
			log.error("selectChildren occurs error, caused by: ", e);
			throw new RuntimeException("selectChildren occurs error", e);
		}
	}

	@Override
	public List<Node<E>> selectChildNodes(PK parentId) {
		return this.selectChildNodes(parentId, null);
	}

	@Override
	public List<Node<E>> selectChildNodes(PK parentId, Integer level) {
		Assert.notNull(parentId, "parentId is null");

		level = level == null || level <= 0 ? 10 : level;

		List<Node<E>> nodeList = Lists.newArrayList();

		List<PK> parentIds = Lists.newArrayList();
		parentIds.add(parentId);

		int currentLevel = 0;
		while (currentLevel < level) {
			//组织子节点
		}

		return nodeList;
	}
}
