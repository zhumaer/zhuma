package com.zhuma.demo.web.demo2;
import com.zhuma.demo.service.user.UserService;
import com.zm.zhuma.commons.model.qo.PageQO;
import com.zm.zhuma.commons.model.vo.PageVO;
import com.zm.zhuma.commons.web.annotations.ResponseResult;
import com.zm.zhuma.user.model.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @desc Spring Boot项目通用功能之《通用Service第一讲》 DEMO
 * 
 * @author zhumaer
 * @since 1/31/2018 23:57 PM
 */
@ResponseResult
@RestController("demo2UserController")
@RequestMapping("demo2/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@Validated @RequestBody User user) {
        String userId = userService.insert(user);
        if (userId != null) {
            return userService.selectByPk(userId);
        }
        return null;
    }

    @GetMapping
    public PageVO<User> getList(PageQO pageQO, User userQO) {
        pageQO.setCondition(userQO);
        return userService.selectPage(pageQO);
    }

}
