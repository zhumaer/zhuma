package com.zm.zhuma.user.service.mapper;

import com.zm.zhuma.commons.dao.CrudMapper;
import com.zm.zhuma.user.model.po.LoginCredential;
import com.zm.zhuma.user.model.po.User;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginCredentialMapper extends CrudMapper<LoginCredential> {
}
