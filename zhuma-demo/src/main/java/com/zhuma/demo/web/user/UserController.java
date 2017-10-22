package com.zhuma.demo.web.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhuma.demo.exception.BusinessException;
import com.zhuma.demo.model.po.User;

/**
 * @desc 用户管理控制器
 * 
 * @author zhumaer
 * @since 6/20/2017 16:37 PM
 */
@RestController
@RequestMapping("/users")
public class UserController {

//	@Autowired
//    private final UserService userService;


//    @GetMapping
//    public PageInfo<User> getUserList(@RequestParam(name="pageNum", defaultValue="1") Integer pageNum, 
//                                        @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
//                                        @RequestParam(name="queryUser") User queryUser) {
//        return userService.pageList(queryUser, pageNum, pageSize);
//    }

    @GetMapping("/{userId}")
    User getUser(@PathVariable("userId") Long userId) {
//        return userService.getUserById(userId);
    	throw new BusinessException();
    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public User addUser(@Valid @RequestBody User user) {
//        return userService.register(user);
//    }
//
//    @PutMapping
//    public User updateUser(@RequestBody User user) {
//        return userService.updateDbAndCache(user);
//    }
//
//    @DeleteMapping("/{userId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    void deleteUser(@PathVariable("userId") Long userId) {
//        userService.deleteUserById(userId);
//    }

}
