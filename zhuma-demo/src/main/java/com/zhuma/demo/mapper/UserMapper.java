package com.zhuma.demo.mapper;

import com.zhuma.demo.comm.mapper.CrudMapper;
import com.zhuma.demo.model.po.User;
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
