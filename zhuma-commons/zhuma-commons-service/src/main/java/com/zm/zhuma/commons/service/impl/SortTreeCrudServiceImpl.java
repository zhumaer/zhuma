package com.zm.zhuma.commons.service.impl;

import com.google.common.collect.Lists;
import com.zm.zhuma.commons.model.bo.Node;
import com.zm.zhuma.commons.model.po.SortTreePO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public abstract class SortTreeCrudServiceImpl<E extends SortTreePO<PK>, PK> extends TreeCurdServiceImpl<E, PK> {

	@Override
	public List<E> selectChildren(PK parentId) {
		List<E> children = super.selectChildren(parentId);

		return children.stream().sorted().collect(Collectors.toList());
	}

	@Override
	public List<Node<E>> selectChildNodes(PK parentId) {
		List<Node<E>> childNodes = super.selectChildNodes(parentId);
		//TODO 排序
		return childNodes;
	}

	@Override
	public List<Node<E>> selectChildNodes(PK parentId, Integer level) {
		List<Node<E>> childNodes = super.selectChildNodes(parentId, level);
		//TODO 排序
		return childNodes;
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
		if (!CollectionUtils.isEmpty(descendantNodes)) {
			List<Node<E>> childen = eNode.getChildren();
			childen.addAll(descendantNodes);
			eNode.setChildren(childen);
			for (Node<E> node : descendantNodes) {
				buildTree(node);
			}
		}
		return eNode;
	}

	private List<Node<E>> getDescendantNodes(PK id) {
		try {
			E queryModel = poType.newInstance();
			queryModel.setParentId(id);
			List<E> eList = crudMapper.select(queryModel);
			List<E> sortList = eList.stream().sorted((r1, r2) -> {
				if (r1.getSort() == null) {
					return 1;
				}
				if (r2.getSort() == null) {
					return -1;
				}
				return r1.getSort().compareTo(r2.getSort());
			}).collect(Collectors.toList());
			List<Node<E>> list = Lists.newLinkedList();
			if (!CollectionUtils.isEmpty(sortList)) {
				for (E parent : sortList) {
					Node<E> node = wrapNode(parent);
					list.add(node);
				}
			}
			return list;
		} catch (Exception e) {
			log.error("when build tree gets errors", e);
		}
		return null;
	}

	private Node<E> wrapNode(E parent) {
		Node<E> node = new Node<>();
		node.setParent(parent);
		return node;
	}
}
