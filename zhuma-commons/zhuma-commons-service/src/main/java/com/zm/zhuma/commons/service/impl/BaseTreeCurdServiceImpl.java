package com.zm.zhuma.commons.service.impl;

import com.google.common.collect.Lists;
import com.zm.zhuma.commons.model.bo.Node;
import com.zm.zhuma.commons.model.po.TreePO;
import com.zm.zhuma.commons.service.TreeCrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.List;

@Slf4j
public abstract class BaseTreeCurdServiceImpl<E extends TreePO<PK>, PK> extends BaseMySqlCrudServiceImpl<E, PK> implements TreeCrudService<E, PK> {

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
	public Node<E> selectNodeByParentId(PK parentId) {
		Assert.notNull(parentId, "parentId is null");

		Node<E> tree = new Node<>();
		E parent = super.selectByPk(parentId);
		if (parent != null) {
			Node<E> eNode = wrapNode(parent);
			tree = buildTree(eNode);
		}

		return tree;
	}

	private Node<E> buildTree(Node<E> eNode) {
		List<Node<E>> descendantNodes = getDescendantNodes(eNode.getParent().getId());
		List<Node<E>> children = eNode.getChildren();
		children.addAll(descendantNodes);
		eNode.setChildren(children);
		for (Node<E> node : descendantNodes) {
			buildTree(node);
		}

		return eNode;
	}

	private List<Node<E>> getDescendantNodes(PK id) {
		List<E> eList = this.selectChildren(id);

		List<Node<E>> list = Lists.newLinkedList();
		for (E parent : eList) {
			Node<E> node = wrapNode(parent);
			list.add(node);
		}

		return list;
	}

	private Node<E> wrapNode(E parent) {
		Node<E> node = new Node<>();
		node.setParent(parent);
		return node;
	}
}
