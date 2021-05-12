package com.netcracker.airlines.service;

import com.netcracker.airlines.dto.ProfileDto;
import com.netcracker.airlines.dto.UserDto;
import com.netcracker.airlines.entities.User;
import com.netcracker.airlines.entities.UserProfile;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    void save(UserDto userDto);

    void edit(Long id, UserDto userDto);

    void addMoney(Integer money, User user);

    UserProfile getUserProfile(User user);

    void addProfileData(Long user, ProfileDto profileDto);
}
