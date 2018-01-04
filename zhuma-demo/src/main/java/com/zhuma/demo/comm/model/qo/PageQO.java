package com.zhuma.demo.comm.model.qo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @desc 分页查询对象

 * @author jingkun.wang@baidao.com
 * @since 7/6/2017 2:48 PM
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageQO<T> {

	private int pageNum = 1;

	private int pageSize = 10;

	private String orderBy;//排序 例：create_time desc

	private T condition;

}
