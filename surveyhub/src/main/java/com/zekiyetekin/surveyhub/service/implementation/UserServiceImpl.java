package com.zekiyetekin.surveyhub.service.implementation;

import com.zekiyetekin.surveyhub.dto.UserDto;
import com.zekiyetekin.surveyhub.entity.ResponseModel;
import com.zekiyetekin.surveyhub.entity.User;
import com.zekiyetekin.surveyhub.enumuration.responsemodel.ResponseMessageEnum;
import com.zekiyetekin.surveyhub.enumuration.responsemodel.ResponseStatusEnum;
import com.zekiyetekin.surveyhub.mapper.UserMapper;
import com.zekiyetekin.surveyhub.repository.UserRepository;
import com.zekiyetekin.surveyhub.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public ResponseModel<UserDto> update(UserDto userDto){

        User optionalUser = userRepository.findById(userDto.getId()).orElse(null);
        if(optionalUser != null ){
            optionalUser.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            optionalUser.setAge(userDto.getAge());
            optionalUser.setJob(userDto.getJob());
            optionalUser.setName(userDto.getName());
            userRepository.save(optionalUser);

            return new ResponseModel<>(ResponseStatusEnum.OK.getCode(), ResponseStatusEnum.OK.getMessage(), true, ResponseMessageEnum.UPDATED_SUCCESSFULLY_DONE, userMapper.toDto(optionalUser));
        }
        return new ResponseModel<>(ResponseStatusEnum.NOT_FOUND.getCode(), ResponseStatusEnum.NOT_FOUND.getMessage(), false, ResponseMessageEnum.UPDATED_ERROR, userDto);
    }

    public  ResponseModel<User> getUserById(Integer id){
        User findedUser = userRepository.findById(id).orElse(null);
        if (findedUser == null){
            return new ResponseModel<>(ResponseStatusEnum.NOT_FOUND.getCode(), ResponseStatusEnum.NOT_FOUND.getMessage(), false,ResponseMessageEnum.DATA_NOT_FOUND, null);
        }
        return new ResponseModel<>(ResponseStatusEnum.OK.getCode(), ResponseStatusEnum.OK.getMessage(), true, ResponseMessageEnum.SUCCESSFULLY_DONE, findedUser );
    }
}
