package com.zm.zhuma.commons.service.impl;

import com.zm.zhuma.commons.model.po.PO;
import com.zm.zhuma.commons.service.RestfulCrudService;
import com.zm.zhuma.commons.validator.CreateGroup;
import com.zm.zhuma.commons.validator.UpdateGroup;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Set;


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
