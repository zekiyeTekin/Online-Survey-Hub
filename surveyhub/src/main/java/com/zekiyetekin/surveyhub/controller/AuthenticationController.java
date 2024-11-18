package com.zekiyetekin.surveyhub.controller;

import com.zekiyetekin.surveyhub.entity.AuthenticationResponse;
import com.zekiyetekin.surveyhub.entity.ResponseModel;
import com.zekiyetekin.surveyhub.entity.User;
import com.zekiyetekin.surveyhub.enumuration.responsemodel.ResponseMessageEnum;
import com.zekiyetekin.surveyhub.enumuration.responsemodel.ResponseStatusEnum;
import com.zekiyetekin.surveyhub.service.implementation.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseModel<AuthenticationResponse> register(
            @RequestBody User request
            ){
        return new ResponseModel<>(ResponseStatusEnum.ACCEPTED.getCode(), ResponseStatusEnum.ACCEPTED.getMessage(), true, ResponseMessageEnum.REGISTERED_SUCCESSFULLY, authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseModel<AuthenticationResponse> login(
            @RequestBody User request
    ){
        return new ResponseModel<>(ResponseStatusEnum.ACCEPTED.getCode(), ResponseStatusEnum.ACCEPTED.getMessage(), true, ResponseMessageEnum.LOGIN_SUCCESSFULLY, authenticationService.authenticationResponse(request));
    }


}
