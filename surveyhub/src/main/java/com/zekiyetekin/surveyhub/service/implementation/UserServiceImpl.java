package com.zekiyetekin.surveyhub.service.implementation;

import com.zekiyetekin.surveyhub.entity.ResponseModel;
import com.zekiyetekin.surveyhub.entity.User;
import com.zekiyetekin.surveyhub.enumuration.responsemodel.ResponseMessageEnum;
import com.zekiyetekin.surveyhub.enumuration.responsemodel.ResponseStatusEnum;
import com.zekiyetekin.surveyhub.repository.UserRepository;
import com.zekiyetekin.surveyhub.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public ResponseModel<User> update(User user){

        Optional<User> optionalUser = userRepository.findById(user.getId());
        if(optionalUser.isPresent()){
            User updatedUser = new User();
            updatedUser.setPassword(user.getPassword());
            updatedUser.setAge(user.getAge());
            updatedUser.setJob(user.getJob());
            updatedUser.setName(user.getName());
            userRepository.save(updatedUser);

            return new ResponseModel<>(ResponseStatusEnum.OK.getCode(), ResponseStatusEnum.OK.getMessage(), true, ResponseMessageEnum.UPDATED_SUCCESSFULLY_DONE, updatedUser);
        }
        return new ResponseModel<>(ResponseStatusEnum.NOT_FOUND.getCode(), ResponseStatusEnum.NOT_FOUND.getMessage(), false, ResponseMessageEnum.UPDATED_ERROR, user);

    }
}
