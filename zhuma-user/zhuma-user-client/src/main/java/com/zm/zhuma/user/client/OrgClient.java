package com.zm.zhuma.user.client;

import com.zm.zhuma.commons.constants.ServerConstants;
import com.zm.zhuma.commons.model.bo.Node;
import com.zm.zhuma.commons.service.RestfulCrudService;
import com.zm.zhuma.user.api.OrgService;
import com.zm.zhuma.user.model.po.Org;
import com.zm.zhuma.user.model.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(value = ServerConstants.USER, path = "orgs")
public interface OrgClient extends RestfulCrudService<Org, Long> {

    @GetMapping("tree/{treeId}")
    Node<Org> getTreeNode(@PathVariable("treeId") Long treeId);

}
