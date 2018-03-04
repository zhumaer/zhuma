package com.zhuma.demo.model.vo.login;

import com.zm.zhuma.user.model.bo.LoginUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @desc 登录VO
 *
 * @author zhumaer
 * @since 3/3/2018 22:39 PM
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO {

    private String token;

    /**
     * 过期时间
     */
    private Long ttl;

    /**
     * 登陆IP
     */
    private String ip;

    /**
     * 登陆平台
     */
    private String platform;

    /**
     * 登陆时间
     */
    private Date loginTime;

    /**
     * 用户信息
     */
    private LoginUser user;

}
