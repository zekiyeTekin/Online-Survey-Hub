package com.zekiyetekin.surveyhub.mapper;

import com.zekiyetekin.surveyhub.dto.UserDto;
import com.zekiyetekin.surveyhub.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class UserMapper {

    public UserDto toDto(User user){
        return new UserDto.Builder()
                .id(user.getId())
                .name(user.getName())
                .job(user.getJob())
                .age(user.getAge())
                .mail(user.getMail())
                .password(user.getPassword())
                .isActive(user.isActive())
                .role(user.getRole())
                .build();
    }

    public List<UserDto> convertList(List<User> userList){
        List<UserDto> userDtoList = new ArrayList<>();

        for (User user : userList){
            userDtoList.add(toDto(user));
        }
        return userDtoList;
    }


}
