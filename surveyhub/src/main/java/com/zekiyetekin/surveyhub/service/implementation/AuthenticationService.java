package com.zekiyetekin.surveyhub.service.implementation;

import com.zekiyetekin.surveyhub.entity.AuthenticationResponse;
import com.zekiyetekin.surveyhub.entity.User;
import com.zekiyetekin.surveyhub.enumuration.role.RoleEnum;
import com.zekiyetekin.surveyhub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Value("http://localhost:5000/activate-account")
    private String activationUrl;

    public AuthenticationResponse register(User request) {
        User user = new User();
        user.setName(request.getName());
        user.setMail(request.getMail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(RoleEnum.USER);
        user.setAge(request.getAge());
        user.setJob(request.getJob());
        user.setActive(true);

        user = userRepository.save(user);
        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticationResponse(User request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getMail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByMail(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }
}
