package com.zhuma.demo.comm.model.vo;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.zhuma.demo.util.BeanUtil;

/**
 * @desc 分页VO对象

 * @author jingkun.wang@baidao.com
 * @since 7/6/2017 2:48 PM
 */
public class PageVO<T> implements Serializable {

	private static final long serialVersionUID = -4426958360243585882L;

	//当前页
	private int pageNum;

	//每页的数量
	private int pageSize;

	//总记录数
	private long total;

	//总页数
	private int pages;

	//结果集
	private List<T> list;

	public PageVO() {
	}

	public PageVO(List<T> poList) {
		BeanUtils.copyProperties(new PageInfo<>(poList), this);
	}

	public static <T> PageVO<T> build(List<T> poList) {
		return new PageVO<T>(poList);
	}

	public static <T> PageVO<T> build(Page<T> page) {
		PageVO<T> pageVO = new PageVO<T>();
		BeanUtils.copyProperties(page.toPageInfo(), pageVO);
		return pageVO;
	}

	/**
	 * @desc 构建一个分页VO对象
	 * 试用场景为：从数据库取出的PO列表不做任何处理，转化为VO列表返回
	 *
	 * @param poList 数据库查出来的分页数据列表
	 * @param voClazz 要转为的VO类
	 */
	public static <T, E> PageVO<T> build(List<E> poList, Class<T> voClazz) {
		PageInfo<E> pageInfo = new PageInfo<>(poList);

		PageVO<T> page = new PageVO<>();
		BeanUtil.copyProperties(pageInfo, page, "list");

		try {
			List<T> rows = Lists.newArrayList();
			if (poList != null) {
				for (E e : poList) {//TODO 此处应该扩展为可通过自定义方法复制对象+注解
					T t = voClazz.newInstance();
					BeanUtil.copyProperties(e, t);
					rows.add(t);
				}
			}
			page.setList(rows);
		} catch (IllegalAccessException | InstantiationException e) {
			throw new RuntimeException(e);
		}

		return page;
	}

	public static <T, E> PageVO<T> build(PageInfo<E> pageInfo, Class<T> voZ) {

		PageVO<T> page = new PageVO<>();
		BeanUtils.copyProperties(pageInfo, page, "list");

		try {
			List<T> VOs = Lists.newArrayList();
			List<E> POs = pageInfo.getList();
			if (!CollectionUtils.isEmpty(POs)) {
				for (E e : POs) {//TODO 此处应该扩展为可通过自定义方法复制对象+注解
					T t = voZ.newInstance();
					BeanUtils.copyProperties(e, t);
					VOs.add(t);
				}
			}
			page.setList(VOs);
		} catch (IllegalAccessException | InstantiationException e) {
			throw new RuntimeException(e);
		}

		return page;
	}

	public static <T, E> PageVO<T> build(PageInfo<E> pageInfo) {
		PageVO<T> page = new PageVO<>();
		BeanUtils.copyProperties(pageInfo, page);
		return page;
	}

	public static <T, E> PageVO<T> build(PageInfo<E> pageInfo, List<T> voList) {
		PageVO<T> page = new PageVO<>();
		BeanUtil.copyProperties(pageInfo, page, "list");
		page.setList(voList == null ? Lists.newArrayList() : voList);
		return page;
	}

	/**
	 * @desc 构建一个分页VO对象
	 * 试用场景为：将处理好的VO列表封装返回
	 *
	 * @param poPage 数据库查出来的分页数据
	 * @param voList vo数据列表
	 */
	public static <T, E> PageVO<T> build(Page<E> poPage, List<T> voList) {
		PageVO<T> page = new PageVO<>();
		BeanUtil.copyProperties(poPage, page, "list");
		page.setList(voList == null ? Lists.newArrayList() : voList);
		return page;
	}

	public static int getPages(int total, int pageSize) {
		return total % pageSize == 0 ? (total / pageSize) : (total / pageSize + 1);
	}


	public int getPageNum() {
		return pageNum;
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

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
