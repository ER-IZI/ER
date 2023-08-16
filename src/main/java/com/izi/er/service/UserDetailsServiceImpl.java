package com.izi.er.service;

import com.izi.er.model.UserDetailsImpl;
import com.izi.er.model.User;
import com.izi.er.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username);
        if(userEntity != null) {
            return new UserDetailsImpl(userEntity);
        }
        throw new UsernameNotFoundException("Username Not Found");
    }
}
