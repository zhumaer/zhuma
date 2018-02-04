package com.zhuma.demo.service.impl;

import com.zhuma.demo.service.UserService;
import com.zm.zhuma.commons.service.impl.MySqlCrudServiceImpl;
import com.zm.zhuma.user.model.po.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends MySqlCrudServiceImpl<User, String> implements UserService {

}
