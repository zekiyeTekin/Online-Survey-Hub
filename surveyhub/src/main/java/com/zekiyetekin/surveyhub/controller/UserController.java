package com.zekiyetekin.surveyhub.controller;

import com.zekiyetekin.surveyhub.entity.ResponseModel;
import com.zekiyetekin.surveyhub.entity.User;
import com.zekiyetekin.surveyhub.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {


   private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PutMapping("/update")
    public ResponseModel<User> update(@RequestBody User user){
        return userService.update(user);


    }
}
