package com.netcracker.airlines.service.serviceImpl;

import com.netcracker.airlines.dto.ProfileDto;
import com.netcracker.airlines.dto.UserDto;
import com.netcracker.airlines.entities.User;
import com.netcracker.airlines.entities.UserProfile;
import com.netcracker.airlines.entities.enums.Role;
import com.netcracker.airlines.exception.DuplicateUserEmailException;
import com.netcracker.airlines.repository.UserProfileRepo;
import com.netcracker.airlines.repository.UserRepo;
import com.netcracker.airlines.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    private final UserProfileRepo userProfileRepo;

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
        UserProfile userProfile = new UserProfile(user, userDto.getName(), userDto.getSurname());
        userProfile.setUsePersonalData(false);
        user.setRole(Role.USER);
        userRepo.save(user);
        userProfileRepo.save(userProfile);
    }

    @Override
    public void edit(Long id, UserDto userDto) {
        User user = userRepo.getOne(id);
        if (!userDto.getEmail().equals(user.getEmail()) && userRepo.existsByEmail(userDto.getEmail())){
            throw new DuplicateUserEmailException(userDto.getEmail());
        }
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserProfile userProfile = getUserProfile(user);
        userProfile.setName(userDto.getName());
        userProfile.setSurname(userDto.getSurname());
        userRepo.save(user);
        userProfileRepo.save(userProfile);
    }

    @Override
    public void addMoney(Integer money, User user) {
        UserProfile userProfile = userProfileRepo.findByUser(user);
        userProfile.setMoney(userProfile.getMoney() + money);
        userProfileRepo.save(userProfile);
    }

    @Override
    public UserProfile getUserProfile(User user) {
        return userProfileRepo.findByUser(user);
    }

    @Override
    public void addProfileData(Long user, ProfileDto profileDto) {
        User current = userRepo.getOne(user);
        UserProfile userProfile = getUserProfile(current);
        userProfile.setDateOfBirth(LocalDate.parse(profileDto.getDateOfBirth(), DateTimeFormatter.ofPattern("d/MM/yyyy")));
        userProfile.setPassportData(profileDto.getPassportData());
        userProfile.setUsePersonalData(profileDto.getUsePersonalData());
        userProfileRepo.save(userProfile);
    }
}
