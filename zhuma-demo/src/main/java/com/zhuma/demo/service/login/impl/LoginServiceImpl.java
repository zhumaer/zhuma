package com.zhuma.demo.service.login.impl;

import com.zhuma.demo.helper.PasswordHelper;
import com.zhuma.demo.model.qo.login.LoginQO;
import com.zhuma.demo.model.vo.login.LoginVO;
import com.zhuma.demo.service.login.LoginService;
import com.zm.zhuma.commons.enums.CacheKeyEnum;
import com.zm.zhuma.commons.enums.ResultCode;
import com.zm.zhuma.commons.exceptions.BusinessException;
import com.zm.zhuma.commons.utils.BeanUtil;
import com.zm.zhuma.commons.utils.IpUtil;
import com.zm.zhuma.commons.utils.RequestContextUtil;
import com.zm.zhuma.commons.web.constants.HeaderConstants;
import com.zm.zhuma.user.client.LoginCredentialClient;
import com.zm.zhuma.user.client.UserClient;
import com.zm.zhuma.user.model.bo.LoginToken;
import com.zm.zhuma.user.model.bo.LoginUser;
import com.zm.zhuma.user.model.po.LoginCredential;
import com.zm.zhuma.user.model.po.User;
import com.zm.zhuma.user.token.helper.LoginTokenHelper;
import com.zm.zhuma.user.token.service.LoginTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @desc 登录服务实现
 *
 * @author zhumaer
 * @since 3/3/2018 22:39 PM
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginTokenService loginTokenService;

	@Autowired
	private LoginCredentialClient loginCredentialClient;

	@Autowired
	private UserClient userClient;

	@Override
	public LoginVO login(LoginQO loginQO) {
		//TODO 增加校验逻辑

		List<LoginCredential> loginCredentialList = loginCredentialClient.getLoginCredentialList(loginQO.getLoginAccount(), loginQO.getType());
		if (loginCredentialList.size() == 0) {
			log.info("login account is nonexistent, account:{}", loginQO.getLoginAccount());
			throw new BusinessException(ResultCode.USER_LOGIN_ERROR);
		}

		//验证密码是否正确
		LoginCredential firstLoginCredential = loginCredentialList.get(0);
		if (!loginQO.getPwd().equals(PasswordHelper.encodeBySalt(loginQO.getPwd(), firstLoginCredential.getRandomSalt()))) {
			log.info("login account' password is error");
			throw new BusinessException(ResultCode.USER_LOGIN_ERROR);
		}

		User user = userClient.getById(firstLoginCredential.getUserId());
		if (user == null) {
			log.info("login user is null");
			throw new BusinessException(ResultCode.USER_LOGIN_ERROR);
		}

		LoginToken loginToken = this.saveLoginToken(user, firstLoginCredential);

		LoginVO loginVO = new LoginVO();
		BeanUtil.copyProperties(loginToken, loginVO);
		loginVO.setToken(loginToken.getId());
		LoginUser loginUser = new LoginUser();
		BeanUtil.copyProperties(user, loginUser);
		loginVO.setUser(loginUser);
		return loginVO;
	}

	private LoginToken saveLoginToken(User user, LoginCredential loginCredential) {
		Date currentDate = new Date();
		LoginUser loginUser = new LoginUser();
		BeanUtil.copyProperties(user, loginUser);

		HttpServletRequest request = RequestContextUtil.getRequest();

		LoginToken loginToken = LoginToken.builder()
				.createTime(currentDate)
				.ip(IpUtil.getRealIp(request))
				.platform(request.getHeader(HeaderConstants.CALL_SOURCE))
				.ttl(CacheKeyEnum.VALUE_LOGIN_TOKENS.sec().longValue())
				.loginCredential(loginCredential)
				.loginUser(loginUser)
				.build();

		loginToken = loginTokenService.add(loginToken);
		LoginTokenHelper.addLoginTokenIdToCookie(loginToken.getId(), CacheKeyEnum.VALUE_LOGIN_TOKENS.sec());
		return loginToken;
	}

	@Override
	public void logout() {
		LoginToken loginToken = LoginTokenHelper.getLoginTokenFromRequest();
		if (loginToken == null) {
			throw new BusinessException(ResultCode.USER_NOT_LOGGED_IN);
		}

		loginTokenService.deleteById(loginToken.getId());
		LoginTokenHelper.delLoginTokenIdFromCookie();
	}
}
