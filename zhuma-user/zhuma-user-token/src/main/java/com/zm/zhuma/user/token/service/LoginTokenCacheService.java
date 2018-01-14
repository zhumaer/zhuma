package com.zm.zhuma.user.token.service;


import com.zm.zhuma.user.model.bo.LoginToken;

/**
 * @desc 用户登录TOKEN缓存服务
 *
 * @author zhumaer@baidao.com
 * @since 6/20/2017 3:00 PM
 */
public interface LoginTokenCacheService {

	/**
	 *
	 * Add login token.
	 *
	 * @param loginToken the login token
	 * @return the login token
	 */
	LoginToken add(LoginToken loginToken);

	/**
	 *
	 * Delete by id.
	 *
	 * @param id the id
	 */
	void deleteById(String id);

	/**
	 *
	 * Get by id login token.
	 *
	 * @param id the id
	 * @return the login token
	 */

	LoginToken getById(String id);

	/**
	 *
	 * Ttl long.
	 *
	 * @param id the id
	 * @return the long
	 */
	long ttl(String id);

}
