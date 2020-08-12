package com.zero.demo.controller;

import com.zero.demo.dto.User;
import com.zero.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author admin
 * @date 2020/8/4-13:52
 */
@Slf4j
@RestController
@RequestMapping("/userSign")
public class UserSignController {
    @Autowired
    private UserService userService;

    @PostMapping("/addSignUser")
    public void addSignUser(@RequestBody @Valid User user) {
        // 配置全局异常处理 ExceptionControllerAdvice
         userService.addUser2(user);
    }

    // ResponseControllerAdvice
    @GetMapping("/getSignUser")
    public User getSignUser() {
        if(log.isInfoEnabled()) log.info("*** getSignUser ****");
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
