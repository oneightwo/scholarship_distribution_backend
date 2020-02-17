package com.oneightwo.scholarship_distribution.security.service;

import com.oneightwo.scholarship_distribution.security.model.User;
import com.oneightwo.scholarship_distribution.security.model.UserDetailsImpl;
import com.oneightwo.scholarship_distribution.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return new UserDetailsImpl(user.orElseThrow(() ->
                new UsernameNotFoundException("UserName " + username + " not found")));
    }
}
