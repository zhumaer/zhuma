/*
 * Copyright 2016 http://www.hswebframework.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.zhuma.demo.comm.service;

import com.zhuma.demo.comm.model.bo.Node;
import com.zhuma.demo.comm.model.po.TreePO;

import java.util.List;


/**
 * @desc 树结构查看服务
 *
 * @author zhumaer
 * @since 10/18/2017 18:31 PM
 */
public interface TreeSelectService<E extends TreePO, PK> {

    /**
     * 根据父节点id获取子节点数据
     *
     * @param parentId 父节点ID
     * @return 子节点数据
     */
    List<E> selectChildren(PK parentId);

    /**
     * 根据父节点id,获取所有子节点的数据,包含子节点的子节点
     *
     * @param parentId 父节点ID
     * @return 所有子节点的数据
     */
    List<Node<E>> selectChildNodes(PK parentId);

    /**
     * 根据父节点id,获取所有子节点的数据,包含子节点的子节点
     *
     * @param parentId 父节点ID
     * @param level 向下获取的层级，为NULL代表获所有
     * @return 按层级获取子节点的数据
     */
    List<Node<E>> selectChildNodes(PK parentId, Integer level);

    /**
     * @desc 获取node信息
     * @author zhubing.ji@get88.cn
     * @date 2017/12/25 下午4:27
     * @param parentId parentId
     * @return NODE
     */
    Node<E> selectNodeByParentId(PK parentId);

}
