package com.zekiyetekin.surveyhub.service.implementation;

import com.zekiyetekin.surveyhub.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        return userRepository.findByMail(mail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
