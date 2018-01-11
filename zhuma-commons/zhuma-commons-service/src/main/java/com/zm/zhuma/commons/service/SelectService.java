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


import com.zm.zhuma.commons.model.qo.PageQO;
import com.zm.zhuma.commons.model.vo.PageVO;

import java.util.List;


/**
 * @desc 基础查看服务
 *
 * @author zhuamer
 * @since 10/18/2017 18:31 PM
 */
public interface SelectService<E, PK> {

    /**
     * 根据主键查询
     * @param pk 主键
     * @return 查询结果,无结果时返回{@code null}
     */
    E selectByPk(PK pk);

    /**
     * 根据多个主键查询
     * @param pks 主键集合
     * @return 查询结果,如果无结果返回空集合
     */
    List<E> selectByPks(Iterable<PK> pks);

    /**
     * 查询所有结果
     * @return 所有结果,如果无结果则返回空集合
     */
    List<E> selectAll();

    /**
     * 查询所有结果
     * @return 获取分页结果
     */
    PageVO<E> selectPage(PageQO<?> pageQO);

}
