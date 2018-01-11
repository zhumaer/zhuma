package com.zhuma.demo.comm.service.impl;

import java.util.List;
import java.util.Set;

import com.zhuma.demo.comm.model.po.PO;
import com.zhuma.demo.comm.service.RestfulCrudService;
import com.zhuma.demo.validator.CreateGroup;
import com.zhuma.demo.validator.UpdateGroup;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


public class RestfulCrudServiceImpl<E extends PO<PK>, PK> extends MySqlCrudServiceImpl<E, PK> implements RestfulCrudService<E, PK> {

	@ResponseStatus(HttpStatus.CREATED)
	@Override
	public E add(@Validated(CreateGroup.class) @RequestBody E record) {
		PK id = super.insert(record);
		if (id != null) {
			return super.selectByPk(id);
		}
		return null;
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Override
	public void deleteById(@PathVariable("id") PK id) {
		super.deleteByPk(id);
	}

	@Override
	public E updateByIdSelective(@Validated(UpdateGroup.class) @PathVariable("id") PK id, @RequestBody E record) {
		super.updateByPkSelective(id, record);
		return super.selectByPk(id);
	}

	@Override
	public E getById(@PathVariable("id") PK id) {
		return super.selectByPk(id);
	}

	@Override
	public List<E> getByIds(@RequestParam("id") Set<PK> ids) {
		return super.selectByPks(ids);
	}

}
