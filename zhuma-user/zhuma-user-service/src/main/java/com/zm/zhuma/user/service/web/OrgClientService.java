package com.zm.zhuma.user.service.web;

import com.zm.zhuma.commons.model.bo.Node;
import com.zm.zhuma.commons.service.impl.RestfulCrudServiceImpl;
import com.zm.zhuma.user.api.OrgService;
import com.zm.zhuma.user.client.OrgClient;
import com.zm.zhuma.user.model.po.Org;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 组织架构服务实现
 * @author zhumaer
 * @since 2018-5-22 10:58:51
 */
@Slf4j
@RestController("orgClientService")
@RequestMapping("/orgs")
public class OrgClientService extends RestfulCrudServiceImpl<Org, Long> implements OrgClient {

    @Autowired
    private OrgService orgService;

    @Override
    public Node<Org> getTreeNode(@PathVariable("treeId") Long treeId) {
        return orgService.selectNodeByParentId(treeId);
    }

}
