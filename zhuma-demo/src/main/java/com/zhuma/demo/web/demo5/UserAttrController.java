package com.zhuma.demo.web.demo5;


import com.zm.zhuma.commons.attributes.model.AttributesChange;
import com.zm.zhuma.commons.attributes.service.AttributeService;
import com.zm.zhuma.commons.model.bo.Node;
import com.zm.zhuma.commons.web.annotations.ResponseResult;
import com.zm.zhuma.user.client.OrgClient;
import com.zm.zhuma.user.model.po.Org;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @desc 用户管理控制器
 * 
 * @author zhumaer
 * @since 7/16/2018 16:37 PM
 */
@ResponseResult
@RestController("demo4UserAttrController")
@RequestMapping("demo4/users/{userId}/attrs")
public class UserAttrController {

    @Autowired
    private AttributeService<String> userAttributeService;

    @PostMapping
    AttributesChange<String> add(@PathVariable("userId") String userId,@RequestBody Map<String, Object> attrMap) {
        return userAttributeService.addAttributes(userId, attrMap);
    }

    @GetMapping
    Map<String, Object>  get(@PathVariable("userId") String userId) {
        return userAttributeService.getAttributes(userId);
    }

    @PatchMapping
    AttributesChange<String> patch(@PathVariable("userId") String userId,@RequestBody Map<String, Object> attrMap) {
        return userAttributeService.setAttributes(userId, attrMap);
    }

    @DeleteMapping
    AttributesChange<String> delete(@PathVariable("userId") String userId) {
        return userAttributeService.deleteAttributes(userId);
    }
}
