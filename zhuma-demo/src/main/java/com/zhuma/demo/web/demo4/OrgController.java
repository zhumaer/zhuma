package com.zhuma.demo.web.demo4;


import com.zm.zhuma.commons.model.bo.Node;
import com.zm.zhuma.commons.web.annotations.ResponseResult;
import com.zm.zhuma.user.client.OrgClient;
import com.zm.zhuma.user.model.po.Org;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @desc 用户管理控制器
 * 
 * @author zhumaer
 * @since 6/20/2017 16:37 PM
 */
@ResponseResult
@RestController("demo4OrgController")
@RequestMapping("demo4/orgs")
public class OrgController {

    @Autowired
    private OrgClient orgClient;

    @PostMapping
    Org add(@RequestBody Org org) {
        Org dbOrg = orgClient.add(org);
        return dbOrg;
    }

    @PatchMapping
    Org patch(@PathVariable("id") Long id, @RequestBody Org org) {
        Org dbOrg = orgClient.updateByIdSelective(id, org);
        return dbOrg;
    }

    @GetMapping("tree/{treeId}")
    Node<Org> getTreeNode(@PathVariable("treeId") Long treeId) {
        Node<Org> tree = orgClient.getTreeNode(treeId);
        return tree;
    }

}
