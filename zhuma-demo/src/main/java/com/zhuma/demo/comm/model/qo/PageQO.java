package com.zhuma.demo.comm.model.qo;

import com.zhuma.demo.model.po.User;

/**
 * @desc 分页查询对象

 * @author jingkun.wang@baidao.com
 * @since 7/6/2017 2:48 PM
 */
public class PageQO<T> {

	private int pageNum = 1;

	private int pageSize = 10;

	private String orderBy;//排序 例：create_time desc

	private T queryModel;

	public int getPageNum() {
		return pageNum;
	}

	public PageQO() {
		super();
	}

	public PageQO(int pageNum, int pageSize) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public PageQO(int pageNum, int pageSize, String orderBy) {
		this(pageNum, pageSize);
		this.orderBy = orderBy;
	}

	public PageQO(int pageNum, int pageSize, T queryModel) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.queryModel = queryModel;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public T getQueryModel() {
		return queryModel;
	}

	public void setQueryModel(T queryModel) {
		this.queryModel = queryModel;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

}
