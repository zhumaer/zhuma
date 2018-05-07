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

package com.zm.zhuma.commons.service;


import com.zm.zhuma.commons.model.bo.Node;
import com.zm.zhuma.commons.model.po.TreePO;

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
     * 获取当前节点下树数据
     *
     * @param parentId 父节点ID
     * @return 树信息
     */
    Node<E> selectNodeByParentId(PK parentId);

}
