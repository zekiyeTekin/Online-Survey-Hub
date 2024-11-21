package com.zekiyetekin.surveyhub.controller;

import com.zekiyetekin.surveyhub.dto.UserDto;
import com.zekiyetekin.surveyhub.entity.ResponseModel;
import com.zekiyetekin.surveyhub.entity.User;
import com.zekiyetekin.surveyhub.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


   private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PutMapping("/update")
    public ResponseModel<UserDto> update(@RequestBody UserDto userDto){
        return userService.update(userDto);
    }

    @GetMapping("/by")
    ResponseModel<User> getUserById(@RequestParam Integer id){
        return userService.getUserById(id);
    }
}
