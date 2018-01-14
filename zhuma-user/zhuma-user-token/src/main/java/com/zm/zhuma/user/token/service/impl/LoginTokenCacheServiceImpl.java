package com.zm.zhuma.user.token.service.impl;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import com.zm.zhuma.commons.enums.CacheKeyEnum;
import com.zm.zhuma.user.model.bo.LoginToken;
import com.zm.zhuma.user.token.helper.LoginTokenHelper;
import com.zm.zhuma.user.token.service.LoginTokenCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Slf4j
@Service
public class LoginTokenCacheServiceImpl implements LoginTokenCacheService {

	@Resource(name = "redisTemplate")
	private ValueOperations<String, LoginToken> loginTokenValueOps;

	private String getLoginTokenCacheKey(String token) {
		return CacheKeyEnum.VALUE_LOGIN_TOKENS.formatKey(token);
	}

	@Override
	public LoginToken add(LoginToken loginToken) {
		Assert.notNull(loginToken, "loginToken is not null");
		Assert.notNull(loginToken.getLoginUser(), "loginToken.getLoginUser() is not null");

		loginToken.setId(LoginTokenHelper.generateId(loginToken.getLoginUser().getLoginAccount(), loginToken.getIp(), loginToken.getPlatform(), loginToken.getLoginTime(), loginToken.getTtl()));
		loginTokenValueOps.set(this.getLoginTokenCacheKey(loginToken.getId()), loginToken, CacheKeyEnum.VALUE_LOGIN_TOKENS.sec(), TimeUnit.SECONDS);
		return loginToken;
	}

	@Override
	public void deleteById(String id) {
		Assert.notNull(id, "id is not null");
		LoginToken loginToken = this.getById(id);
		loginTokenValueOps.getOperations().delete(this.getLoginTokenCacheKey(id));
	}

	@Override
	public LoginToken getById(String id) {
		Assert.notNull(id, "id is not null");

		return loginTokenValueOps.get(this.getLoginTokenCacheKey(id));
	}

	@Override
	public long ttl(String id) {
		Assert.notNull(id, "id is not null");

		return loginTokenValueOps.getOperations().getExpire(this.getLoginTokenCacheKey(id), TimeUnit.SECONDS);
	}

}
