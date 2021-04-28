package com.netcracker.airlines.service.serviceImpl;

import com.netcracker.airlines.dto.UserDto;
import com.netcracker.airlines.entities.User;
import com.netcracker.airlines.entities.enums.Role;
import com.netcracker.airlines.exception.DuplicateUserEmailException;
import com.netcracker.airlines.repository.UserRepo;
import com.netcracker.airlines.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email not found" + email));
    }

    @Override
    public void save(UserDto userDto) {
        if (userRepo.existsByEmail(userDto.getEmail())){
            throw new DuplicateUserEmailException(userDto.getEmail());
        }
        User user = new User(userDto.getName(), userDto.getSurname(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.USER);
        userRepo.save(user);
    }

    @Override
    public void addMoney(Integer money, User user) {
        user.setMoney(user.getMoney() + money);
        userRepo.save(user);
    }
}
