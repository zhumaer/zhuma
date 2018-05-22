package com.zm.zhuma.user.service.impl;

import com.zm.zhuma.commons.service.impl.BaseSortTreeCrudServiceImpl;
import com.zm.zhuma.user.api.OrgService;
import com.zm.zhuma.user.model.po.Org;
import org.springframework.stereotype.Service;

/**
 * 组织架构服务实现
 * @author zhumaer
 * @since 2018-5-22 10:58:51
 */
@Service
public class OrgServiceImpl extends BaseSortTreeCrudServiceImpl<Org, Long> implements OrgService {
}
