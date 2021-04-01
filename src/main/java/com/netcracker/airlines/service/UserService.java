package com.netcracker.airlines.service;

import com.netcracker.airlines.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void save(UserDto userDto);

}
