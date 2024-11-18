package com.zekiyetekin.surveyhub.service.implementation;

import com.zekiyetekin.surveyhub.entity.ResponseModel;
import com.zekiyetekin.surveyhub.entity.User;
import com.zekiyetekin.surveyhub.enumuration.responsemodel.ResponseMessageEnum;
import com.zekiyetekin.surveyhub.enumuration.responsemodel.ResponseStatusEnum;
import com.zekiyetekin.surveyhub.repository.UserRepository;
import com.zekiyetekin.surveyhub.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public ResponseModel<User> update(User user){

        User optionalUser = userRepository.findById(user.getId()).orElse(null);
        if(optionalUser != null ){
            optionalUser.setPassword(user.getPassword());
            optionalUser.setAge(user.getAge());
            optionalUser.setJob(user.getJob());
            optionalUser.setName(user.getName());
            userRepository.save(optionalUser);

            return new ResponseModel<>(ResponseStatusEnum.OK.getCode(), ResponseStatusEnum.OK.getMessage(), true, ResponseMessageEnum.UPDATED_SUCCESSFULLY_DONE, optionalUser);
        }
        return new ResponseModel<>(ResponseStatusEnum.NOT_FOUND.getCode(), ResponseStatusEnum.NOT_FOUND.getMessage(), false, ResponseMessageEnum.UPDATED_ERROR, user);
    }

    public  ResponseModel<User> getUserById(Integer id){
        User findedUser = userRepository.findById(id).orElse(null);
        if (findedUser == null){
            return new ResponseModel<>(ResponseStatusEnum.NOT_FOUND.getCode(), ResponseStatusEnum.NOT_FOUND.getMessage(), false,ResponseMessageEnum.DATA_NOT_FOUND, null);
        }
        return new ResponseModel<>(ResponseStatusEnum.OK.getCode(), ResponseStatusEnum.OK.getMessage(), true, ResponseMessageEnum.SUCCESSFULLY_DONE, findedUser );


    }
}
