package com.zm.zhuma.commons.service;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @desc restfult风格的crud服务
 *
 * @author zhumaer
 * @since 10/18/2017 18:31 PM
 */
public interface RestfulCrudService<E, PK> {

	@PostMapping
	E add(@RequestBody E e);

	@DeleteMapping("{id}")
	void deleteById(PK id);

	@PatchMapping("{id}")
	E updateByIdSelective(@PathVariable("id") PK id, @RequestBody E record);

	@GetMapping("{id}")
	E getById(@PathVariable("id") PK id);

	@GetMapping(params = { "id" })
	List<E> getByIds(@RequestParam("id") Set<PK> ids);

}
