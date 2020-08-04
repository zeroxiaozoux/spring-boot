package com.zero.demo.controller;

import com.zero.demo.dto.User;
import com.zero.demo.service.UserService;
import com.zero.demo.util.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author admin
 * @date 2020/8/4-13:52
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public String addUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if(log.isInfoEnabled()) log.info("*** addUser ****");
        // 如果有参数校验失败，会将错误信息封装成对象组装在BindingResult里
        for (ObjectError error : bindingResult.getAllErrors()) {
            return error.getDefaultMessage();
        }
        return userService.addUser(user);
    }

    @PostMapping("/addUser2")
    public String addUser2(@RequestBody @Valid User user) {
        // 配置全局异常处理 ExceptionControllerAdvice
        return userService.addUser(user);
    }
   // ExceptionControllerAdvice 枚举
    @GetMapping("/getUser")
    public ResultVO<User> getUser() {
        if(log.isInfoEnabled()) log.info("*** getUser ****");
        User user = new User();
        user.setId(1L);
        user.setAge(1);
        user.setSex("1");
        user.setEmail("123");

        return new ResultVO<>(user);
    }

    // ResponseControllerAdvice
    @GetMapping("/getUser2")
    public User getUser2() {
        if(log.isInfoEnabled()) log.info("*** getUser2 ****");
        User user = new User();
        user.setId(1L);
        user.setAge(10);
        user.setName("zero");
        user.setSex("男");
        user.setEmail("123@qq.com");
        // 直接返回的User类型，并没有用ResultVO进行包装
        return user;
    }
}
