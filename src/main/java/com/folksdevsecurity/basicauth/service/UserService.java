package com.folksdevsecurity.basicauth.service;

import com.folksdevsecurity.basicauth.dto.CreateUserRequest;
import com.folksdevsecurity.basicauth.model.User;
import com.folksdevsecurity.basicauth.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public Optional<User> getByUsername(String username){
        return this.userRepository.findByUsername(username);
    }
    public User createUser(CreateUserRequest createUserRequest){
        User newUser= User.builder()
                .name(createUserRequest.name())
                .username(createUserRequest.username())
                .password(passwordEncoder.encode(createUserRequest.password()))
                .authorities(createUserRequest.authorities())
                .isEnabled(true)
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .accountNonLocked(true)
                .build();
        return this.userRepository.save(newUser);
    }



}
