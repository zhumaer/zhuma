package com.zhuma.demo.service.impl;

import com.zhuma.demo.comm.service.impl.MySqlCrudServiceImpl;
import com.zhuma.demo.model.po.User;
import com.zhuma.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends MySqlCrudServiceImpl<User, String> implements UserService {

}
