package com.netcracker.airlines.service.serviceImpl;

import com.netcracker.airlines.entities.User;
import com.netcracker.airlines.entities.enums.Role;
import com.netcracker.airlines.exception.DuplicateUserEmailException;
import com.netcracker.airlines.repository.UserRepo;
import com.netcracker.airlines.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email not found" + email));
    }

    @Override
    public void save(User user) {
        if (userRepo.existsByEmail(user.getEmail())){
            throw new DuplicateUserEmailException(user.getEmail());
        }
        user.setRole(Role.USER);
        userRepo.save(user);
    }
}
