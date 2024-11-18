package com.zekiyetekin.surveyhub.service;

import com.zekiyetekin.surveyhub.entity.ResponseModel;
import com.zekiyetekin.surveyhub.entity.User;

public interface UserService {


    ResponseModel<User> update(User user);
}
