package com.zekiyetekin.surveyhub.service;

import com.zekiyetekin.surveyhub.dto.UserDto;
import com.zekiyetekin.surveyhub.entity.ResponseModel;
import com.zekiyetekin.surveyhub.entity.User;

public interface UserService {


    ResponseModel<UserDto> update(UserDto userDto);

    ResponseModel<User> getUserById(Integer id);
}
