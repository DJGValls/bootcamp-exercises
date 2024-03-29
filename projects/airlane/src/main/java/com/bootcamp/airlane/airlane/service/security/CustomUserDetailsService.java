package com.bootcamp.airlane.airlane.service.security;

import com.bootcamp.airlane.airlane.config.CustomUserDetails;
import com.bootcamp.airlane.airlane.model.security.User;
import com.bootcamp.airlane.airlane.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user =  userRepository.findByUsername(username);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }

        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setUser(user.get());

        return userDetails;
    }
}
