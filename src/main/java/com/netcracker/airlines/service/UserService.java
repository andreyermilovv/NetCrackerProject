package com.netcracker.airlines.service;

import com.netcracker.airlines.dto.UserDto;
import com.netcracker.airlines.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void save(UserDto userDto);

    void addMoney(Integer money, User user);
}
