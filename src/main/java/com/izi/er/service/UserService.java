package com.izi.er.service;

import com.izi.er.model.User;
import com.izi.er.model.type.RoleType;
import com.izi.er.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public void signup(User user) {
        user.setPassword(passwordEncode(user.getPassword()));
        saveUser(user);
    }
    private String passwordEncode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
    @Transactional
    private void saveUser(User user) {
        userRepository.save(user);
    }
}
