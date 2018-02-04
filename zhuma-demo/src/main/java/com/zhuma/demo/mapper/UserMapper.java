package com.zhuma.demo.mapper;

import com.zm.zhuma.commons.dao.CrudMapper;
import com.zm.zhuma.user.model.po.User;
import org.springframework.stereotype.Repository;

/**
 * @desc 用户Mapper
 *
 * @author zhumaer
 * @since 25/1/2018 22:39 PM
 */
@Repository
public interface UserMapper extends CrudMapper<User> {
}
