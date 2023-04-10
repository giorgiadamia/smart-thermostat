package com.example.smartthermostat.service;

import com.example.smartthermostat.model.domain.Role;
import com.example.smartthermostat.model.domain.User;
import com.example.smartthermostat.model.dto.UserDto;
import com.example.smartthermostat.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User does not exist");
        }
        return user;
    }

    public boolean create(User user) {
        User userFromDatabase = userRepository.findByUsername(user.getUsername());

        if (userFromDatabase != null) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(Role.USER));

        userRepository.save(user);
        return true;
    }

    public UserDto show(User user) {
        User userFromDatabase = userRepository.findByUsername(user.getUsername());
        UserDto userDto = new UserDto();
        userDto.setUsername(userFromDatabase.getUsername());
        userDto.setThermostats(userFromDatabase.getThermostats());
        return userDto;
    }
}
