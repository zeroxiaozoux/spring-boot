package com.zero.demo.service.impl;

import com.zero.demo.dto.User;
import com.zero.demo.service.UserService;
import org.springframework.core.style.ToStringCreator;
import org.springframework.core.style.ToStringStyler;
import org.springframework.stereotype.Service;

/**
 * @author admin
 * @date 2020/8/4-13:59
 */
@Service

public class UserServiceImpl implements UserService {

    @Override
    public String addUser(User user) {
         return "success";
    }

    @Override
    public void addUser2(User user) {
        System.out.println(user.toString());
    }
}
