package com.zhuma.demo.comm.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhuma.demo.comm.mapper.BaseMapper;
import com.zhuma.demo.comm.model.po.BasePO;
import com.zhuma.demo.comm.model.qo.PageQO;
import com.zhuma.demo.comm.model.vo.PageVO;
import com.zhuma.demo.comm.service.BaseService;

public class BaseServiceImpl<T extends BasePO> implements BaseService<T> {

	@Autowired
	protected BaseMapper<T> baseMapper;

	@Override
	public int insert(T record) {
		Assert.notNull(record, "record is not null");

		if (record.getCreateTime() == null) {
			Date currentDate = new Date();
			record.setCreateTime(currentDate);
			record.setUpdateTime(currentDate);
		}
		return baseMapper.insert(record);
	}

	@Override
	public int updateByPrimaryKey(T record) {
		Assert.notNull(record, "record is not null");

		if (record.getUpdateTime() == null) {
			record.setUpdateTime(new Date());
		}
		return baseMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(T record) {
		Assert.notNull(record, "record is not null");

		if (record.getUpdateTime() == null) {
			record.setUpdateTime(new Date());
		}
		return baseMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public T selectByPrimaryKey(Object primaryKey) {
		return baseMapper.selectByPrimaryKey(primaryKey);
	}

	@Override
	public PageVO<T> selectPageVO(PageQO<T> pageQO) {
		Page<T> page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize(), pageQO.getOrderBy());
		baseMapper.selectByCondition(pageQO.getQueryModel());
		return PageVO.build(page);
	}

}
