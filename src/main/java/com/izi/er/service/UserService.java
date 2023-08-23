package com.izi.er.service;

import com.izi.er.repository.UserRepository;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public String encodeTest(String rawPassword) {
        String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.println("raw: "+rawPassword);
        System.out.println("enc: "+encodedPassword);
        System.out.println("same?: "+passwordEncoder.matches(rawPassword, encodedPassword));
        System.out.println("same?: "+passwordEncoder.matches("other string", encodedPassword));

        return encodedPassword;
    }
}
