package com.zhuma.demo.comm.model.bo;

import com.zhuma.demo.comm.model.po.TreePO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @desc 树节点
 *
 * @author zhuamer
 * @since 19/12/2017 9:54 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Node<E extends TreePO> {

	private E parent;

	private List<Node<E>> children;
}
